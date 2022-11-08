package com.skype.android.video.hw.codec.encoder.camera.capture;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.camera.gl.GLException;
import com.skype.android.video.hw.codec.encoder.camera.gl.VirtualSourceGenerator;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.Log;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class VirtualCaptureSource implements CapturerSource {
    private ScheduledFuture<?> activeTask;
    private final Object activeTaskMutex = new Object();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private ByteBuffer frameData;
    private int frameSize;
    private int framerate;
    private long nativeObj;
    private final Object nativeObjMutex = new Object();
    private final Runnable runner = new Runner();
    private Resolution sourceResolution;
    private Surface surface;
    private final Object surfaceMutex = new Object();
    private SurfaceTexture surfaceTexture;
    private volatile long timestamp;
    private final boolean useRunner;
    private VirtualSourceGenerator virtualSource;

    private class Runner implements Runnable {
        private Runner() {
        }

        public void run() {
            synchronized (VirtualCaptureSource.this.nativeObjMutex) {
                VirtualCaptureSource.this.timestamp = VirtualCaptureSource.this.getFrame(VirtualCaptureSource.this.nativeObj);
            }
            try {
                VirtualCaptureSource.this.writeFrame();
            } catch (GLException e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, "Exception caught!", e);
                }
            }
        }
    }

    private native long createNative(boolean z);

    private native void deleteNative(long j);

    private native long getFrame(long j);

    private native void setResolution(long j, int i, int i2, ByteBuffer byteBuffer);

    private native void unlock(long j);

    public VirtualCaptureSource(boolean userunner) throws GLException {
        this.useRunner = userunner;
        this.nativeObj = createNative(this.useRunner);
        Log.i(Commons.TAG, getClass().getSimpleName() + ": Created. nativeObj=0x" + Long.toHexString(this.nativeObj));
        this.virtualSource = new VirtualSourceGenerator();
        this.timestamp = 10000000;
    }

    public void close() {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Closing. nativeObj=0x" + Long.toHexString(this.nativeObj));
        }
        synchronized (this.activeTaskMutex) {
            if (this.activeTask != null) {
                stop();
            }
        }
        setSurfaceTexture(null);
        synchronized (this.nativeObjMutex) {
            if (this.nativeObj != 0) {
                deleteNative(this.nativeObj);
                this.nativeObj = 0;
            }
        }
    }

    public void start() throws CapturerException {
        Log.i(Commons.TAG, getClass().getSimpleName() + ": Starting. nativeObj=0x" + Long.toHexString(this.nativeObj));
        try {
            this.virtualSource.setup();
        } catch (GLException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Exception caught!", e);
            }
        }
        if (this.useRunner) {
            startRunner();
        }
    }

    public void stop() {
        Log.i(Commons.TAG, getClass().getSimpleName() + ": Stopping. nativeObj=0x" + Long.toHexString(this.nativeObj));
        if (this.useRunner) {
            stopRunner();
        }
        try {
            this.virtualSource.close();
        } catch (GLException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Exception caught!", e);
            }
        }
    }

    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        synchronized (this.surfaceMutex) {
            if (surfaceTexture != null) {
                if (this.surface != null) {
                    this.surface.release();
                    this.surface = null;
                }
                if (this.surfaceTexture != null) {
                    this.surfaceTexture.release();
                }
                this.surfaceTexture = surfaceTexture;
                if (surfaceTexture != null) {
                    this.surface = new Surface(this.surfaceTexture);
                }
                surfaceTexture.setDefaultBufferSize(this.sourceResolution.getWidth(), this.sourceResolution.getHeight());
                this.virtualSource.setResolution(this.sourceResolution);
                this.virtualSource.setSurface(this.surface);
            }
        }
    }

    public void setResolution(Resolution resolution) throws CapturerException {
        synchronized (this.surfaceMutex) {
            this.sourceResolution = resolution;
            this.frameSize = (this.sourceResolution.getWidth() * 4) * this.sourceResolution.getHeight();
            this.frameData = ByteBuffer.allocateDirect(this.frameSize);
        }
        synchronized (this.nativeObjMutex) {
            setResolution(this.nativeObj, resolution.getWidth(), resolution.getHeight(), this.frameData);
            this.virtualSource.setResolution(resolution);
            if (this.surfaceTexture != null) {
                this.surfaceTexture.setDefaultBufferSize((this.sourceResolution.getWidth() + 15) & 65520, (this.sourceResolution.getHeight() + 15) & 65520);
            }
        }
    }

    public void setFramerate(int framerate) throws CapturerException {
        this.framerate = framerate;
        synchronized (this.activeTaskMutex) {
            if (this.activeTask != null) {
                stop();
                start();
            }
        }
    }

    public void configure(Resolution resolution, int framerate) throws CapturerException {
        setResolution(resolution);
        setFramerate(framerate);
    }

    public long getNativeCtx() {
        return this.nativeObj;
    }

    public void unlock() {
        unlock(this.nativeObj);
    }

    private void startRunner() {
        synchronized (this.activeTaskMutex) {
            if (this.activeTask != null) {
                throw new IllegalStateException("already running");
            }
            long periodUs = (long) (1000000000 / this.framerate);
            this.activeTask = this.executor.scheduleAtFixedRate(this.runner, 0, periodUs, TimeUnit.MICROSECONDS);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Runner task scheduled with period " + (((float) periodUs) / 1000.0f) + " ms");
            }
        }
    }

    private void stopRunner() {
        synchronized (this.activeTaskMutex) {
            if (this.activeTask == null) {
                throw new IllegalStateException("not running");
            }
            if (this.activeTask.cancel(false)) {
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, getClass().getSimpleName() + ": Runner task canceled");
                }
                this.activeTask = null;
            } else if (Log.isLoggable(Commons.TAG, 5)) {
                Log.w(Commons.TAG, getClass().getSimpleName() + ": Could not cancel active task");
            }
        }
    }

    private void writeFrame() throws GLException {
        synchronized (this.surfaceMutex) {
            try {
                this.virtualSource.renderOneFrame(this.frameData, this.timestamp);
            } catch (Throwable e) {
                Log.e(Commons.TAG, "", e);
            }
        }
    }

    public void frameAvailableCallback() {
        synchronized (this.nativeObjMutex) {
            this.timestamp = getFrame(this.nativeObj);
        }
        try {
            writeFrame();
        } catch (GLException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Exception caught!", e);
            }
        }
    }

    public void resolutionChangedCallback(int width, int height) {
        synchronized (this.nativeObjMutex) {
            this.sourceResolution = new Resolution(width, height);
            try {
                setResolution(this.sourceResolution);
            } catch (CapturerException e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, "Exception caught!", e);
                }
            }
        }
    }
}
