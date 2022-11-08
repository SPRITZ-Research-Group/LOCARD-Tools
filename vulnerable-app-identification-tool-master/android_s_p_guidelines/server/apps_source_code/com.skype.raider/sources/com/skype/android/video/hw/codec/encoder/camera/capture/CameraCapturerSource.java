package com.skype.android.video.hw.codec.encoder.camera.capture;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraCapturerSource implements CapturerSource {
    private Camera camera;
    private int cameraId = -1;

    private static class FpsRange {
        public final int max;
        public final int min;

        public FpsRange(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public String toString() {
            return (((float) this.min) / 1000.0f) + "-" + (((float) this.max) / 1000.0f);
        }
    }

    public long getNativeCtx() {
        return 0;
    }

    public void unlock() {
    }

    public CameraCapturerSource(int cameraId) throws CapturerException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Constructing for camera #" + cameraId);
        }
        this.cameraId = cameraId;
        this.camera = acquireCamera(cameraId);
    }

    public void start() throws CapturerException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Starting camera #" + this.cameraId);
        }
        try {
            this.camera.startPreview();
        } catch (RuntimeException e) {
            throw new CapturerException("Failed to start preview from the camera #" + this.cameraId, e);
        }
    }

    public void stop() {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Stopping camera #" + this.cameraId);
        }
        this.camera.stopPreview();
    }

    public void close() {
        if (this.camera != null) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Closing");
            }
            stop();
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Releasing camera #" + this.cameraId);
            }
            this.camera.release();
            this.camera = null;
            this.cameraId = -1;
        }
    }

    public void setSurfaceTexture(SurfaceTexture surfaceTexture) throws CapturerException {
        try {
            this.camera.setPreviewTexture(surfaceTexture);
        } catch (IOException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, getClass().getSimpleName() + ": Failed to set preview texture for the camera #" + this.cameraId, e);
            }
            throw new CapturerException("Failed to set preview texture for the camera #" + this.cameraId, e);
        }
    }

    public void setResolution(Resolution resolution) throws CapturerException {
        configure(resolution, 0);
    }

    public void setFramerate(int framerate) throws CapturerException {
        configure(null, framerate);
    }

    public void configure(Resolution resolution, int framerate) throws CapturerException {
        Parameters cameraParams = this.camera.getParameters();
        if (resolution != null) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Setting camera #" + this.cameraId + " resolution " + resolution);
            }
            cameraParams.setPreviewSize(resolution.getWidth(), resolution.getHeight());
        }
        if (framerate > 0) {
            FpsRange fpsRange = findFpsRange(cameraParams.getSupportedPreviewFpsRange(), framerate);
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Setting camera #" + this.cameraId + " framerate " + fpsRange + " fps");
            }
            cameraParams.setPreviewFpsRange(fpsRange.min, fpsRange.max);
        }
        try {
            this.camera.setParameters(cameraParams);
        } catch (RuntimeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, getClass().getSimpleName() + ": Failed to set parameters for the camera #" + this.cameraId, e);
            }
            throw new CapturerException("Failed to set parameters for the camera #" + this.cameraId, e);
        }
    }

    private static Camera acquireCamera(int cameraId) throws CapturerException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, CameraCapturerSource.class.getSimpleName() + ": Opening camera #" + cameraId);
        }
        try {
            Camera camera = Camera.open(cameraId);
            if (camera != null) {
                return camera;
            }
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, CameraCapturerSource.class.getSimpleName() + ": Failed to open camera #" + cameraId);
            }
            throw new CapturerException("Failed to open camera #" + cameraId);
        } catch (RuntimeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, CameraCapturerSource.class.getSimpleName() + ": Failed to open camera #" + cameraId, e);
            }
            throw new CapturerException("Failed to open camera #" + cameraId, e);
        }
    }

    private static FpsRange findFpsRange(List<int[]> supportedFpsRanges, int framerate) throws CapturerException {
        List<int[]> filteredFpsRanges = new ArrayList();
        for (int[] fpsRange : supportedFpsRanges) {
            if (fpsRange[0] <= framerate && fpsRange[1] >= framerate) {
                filteredFpsRanges.add(fpsRange);
            }
        }
        if (filteredFpsRanges.isEmpty()) {
            throw new CapturerException("Framerate " + (((float) framerate) / 1000.0f) + " is not contained withing ranges " + supportedFpsRanges);
        }
        Collections.sort(filteredFpsRanges, new Comparator<int[]>() {
            public final int compare(int[] left, int[] right) {
                int leftSize = getSize(left);
                int rightSize = getSize(right);
                if (leftSize < rightSize) {
                    return -1;
                }
                return leftSize > rightSize ? 1 : 0;
            }

            private int getSize(int[] fpsRange) {
                return fpsRange[1] - fpsRange[0];
            }
        });
        FpsRange range = new FpsRange(((int[]) filteredFpsRanges.get(0))[0], ((int[]) filteredFpsRanges.get(0))[1]);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, CameraCapturerSource.class.getSimpleName() + ": Desired framerate " + (((float) framerate) / 1000.0f) + " is found within range " + range + " fps");
        }
        return range;
    }
}
