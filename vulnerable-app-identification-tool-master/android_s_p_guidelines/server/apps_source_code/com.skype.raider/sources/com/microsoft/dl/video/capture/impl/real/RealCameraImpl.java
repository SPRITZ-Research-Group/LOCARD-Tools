package com.microsoft.dl.video.capture.impl.real;

import android.graphics.SurfaceTexture;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.Camera;
import com.microsoft.dl.video.capture.api.CameraCallback;
import com.microsoft.dl.video.capture.api.CameraParameters;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.impl.real.impl.CameraCapabilitiesUtils;

public class RealCameraImpl implements Camera {
    private final int a;
    private final android.hardware.Camera b;

    private static class CallbackHandler implements ErrorCallback, PreviewCallback {
        private final CameraCallback a;
        private final Camera b;

        public CallbackHandler(CameraCallback cb, Camera camera) {
            this.a = cb;
            this.b = camera;
        }

        public void onPreviewFrame(byte[] data, android.hardware.Camera hwCamera) {
            this.a.onCpuFrameCaptured(data, this.b);
        }

        public void onError(int error, android.hardware.Camera hwCamera) {
            this.a.onError(new CaptureException("Camera error", ErrorCode.ANDROID_CAMERA_RUNTIME_FAILURE, String.valueOf(error)));
        }
    }

    public RealCameraImpl(int cameraId) throws CaptureException {
        this.a = cameraId;
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "Opening camera " + cameraId);
        }
        try {
            this.b = android.hardware.Camera.open(cameraId);
            if (this.b == null) {
                throw new CaptureException("Could not open camera " + cameraId, ErrorCode.ANDROID_CAMERA_OPEN_FAILED);
            } else if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Camera " + cameraId + " is opened");
            }
        } catch (Throwable e) {
            throw new CaptureException("Could not open camera " + cameraId, e, ErrorCode.ANDROID_CAMERA_OPEN_FAILED);
        }
    }

    public final void close() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "Closing camera " + this.a);
        }
        if (this.b != null) {
            try {
                this.b.release();
            } catch (Throwable e) {
                throw new CaptureException("Could not close camera " + this.a, e, ErrorCode.ANDROID_CAMERA_CLOSE_FAILED);
            }
        }
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "Camera " + this.a + " is closed");
        }
    }

    public final CameraParameters getParameters() throws CaptureException {
        int[] fpsRange = new int[2];
        try {
            Parameters hwParameters = this.b.getParameters();
            int imageFormat = hwParameters.getPreviewFormat();
            Size resolution = hwParameters.getPreviewSize();
            hwParameters.getPreviewFpsRange(fpsRange);
            String focusMode = hwParameters.getFocusMode();
            CameraParameters parameters = new CameraParameters();
            parameters.setImageFormat(CameraCapabilitiesUtils.mapImageFormat(imageFormat));
            parameters.setResolution(CameraCapabilitiesUtils.mapResolution(resolution));
            parameters.setFpsRange(CameraCapabilitiesUtils.mapFpsRange(fpsRange));
            parameters.setFocusMode(focusMode);
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Camera " + this.a + " returned " + parameters);
            }
            return parameters;
        } catch (Throwable e) {
            throw new CaptureException("Could not get parameters of the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_GET_PARAMETERS_FAILED);
        }
    }

    public final void setParameters(CameraParameters parameters) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Setting " + parameters + " to the camera " + this.a);
        }
        try {
            Parameters hwParameters = this.b.getParameters();
            hwParameters.setPreviewFormat(CameraCapabilitiesUtils.mapImageFormat(parameters.getImageFormat()));
            hwParameters.setPreviewSize(parameters.getResolution().getWidth(), parameters.getResolution().getHeight());
            if (CameraCapabilitiesUtils.isPictureSizeSupported(parameters.getResolution(), hwParameters)) {
                hwParameters.setPictureSize(parameters.getResolution().getWidth(), parameters.getResolution().getHeight());
            }
            hwParameters.setPreviewFpsRange(parameters.getFpsRange().getMin(), parameters.getFpsRange().getMax());
            if (parameters.getFocusMode() != null) {
                hwParameters.setFocusMode(parameters.getFocusMode());
            }
            this.b.setParameters(hwParameters);
        } catch (Throwable e) {
            throw new CaptureException("Could not set " + parameters + " to the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_SET_PARAMETERS_FAILED);
        }
    }

    public final void setPreviewDisplay(Object display) throws CaptureException {
        if (display == null) {
            try {
                this.b.setPreviewDisplay(null);
                this.b.setPreviewTexture(null);
            } catch (Throwable e) {
                throw new CaptureException("Could not set preview display to " + display + " for the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_SET_PREVIEW_DISPLAY_FAILED);
            } catch (Throwable e2) {
                throw new CaptureException("Could not set preview display to " + display + " for the camera " + this.a, e2, ErrorCode.ANDROID_CAMERA_SET_PREVIEW_DISPLAY_FAILED);
            }
        } else if (display instanceof SurfaceHolder) {
            this.b.setPreviewDisplay((SurfaceHolder) display);
        } else if (display instanceof SurfaceTexture) {
            this.b.setPreviewTexture((SurfaceTexture) display);
        } else {
            throw new CaptureException(display.getClass().getCanonicalName() + " is not supported", ErrorCode.ANDROID_CAMERA_UNSUPPORTED_PREVIEW_DISPLAY);
        }
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Preview display is set to " + display + " for the camera " + this.a);
        }
    }

    public final void setDisplayOrientation(int degrees) throws CaptureException {
        try {
            this.b.setDisplayOrientation(degrees);
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Display orientation is set to " + degrees + " degrees for the camera " + this.a);
            }
        } catch (Throwable e) {
            throw new CaptureException("Could not set display orientation to " + degrees + " degrees for the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_SET_PREVIEW_DISPLAY_ORIENTATION_FAILED);
        }
    }

    public final void addCallbackBuffer(byte[] callbackBuffer) throws CaptureException {
        try {
            this.b.addCallbackBuffer(callbackBuffer);
        } catch (Throwable e) {
            throw new CaptureException("Could not add callback buffer for the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_ADD_CALLBACK_BUFFER_FAILED);
        }
    }

    public final void setCallback(CameraCallback cb) throws CaptureException {
        CallbackHandler handler;
        if (cb != null) {
            try {
                handler = new CallbackHandler(cb, this);
            } catch (Throwable e) {
                throw new CaptureException("Could not set preview callback for the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_SET_CALLBACK_FAILED);
            }
        }
        handler = null;
        this.b.setPreviewCallbackWithBuffer(handler);
        this.b.setErrorCallback(handler);
    }

    public final void startPreview() throws CaptureException {
        try {
            this.b.startPreview();
        } catch (Throwable e) {
            throw new CaptureException("Could not start preview from the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_START_PREVIEW_FAILED);
        }
    }

    public final void stopPreview() throws CaptureException {
        try {
            this.b.stopPreview();
        } catch (Throwable e) {
            throw new CaptureException("Could not stop preview from the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_STOP_PREVIEW_FAILED);
        }
    }
}
