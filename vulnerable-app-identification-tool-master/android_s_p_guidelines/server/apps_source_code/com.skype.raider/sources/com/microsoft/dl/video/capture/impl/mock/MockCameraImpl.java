package com.microsoft.dl.video.capture.impl.mock;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.Camera;
import com.microsoft.dl.video.capture.api.CameraCallback;
import com.microsoft.dl.video.capture.api.CameraCapabilities;
import com.microsoft.dl.video.capture.api.CameraParameters;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.FpsRange;
import com.microsoft.dl.video.capture.api.ImageFormat;
import com.microsoft.dl.video.utils.Resolution;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.concurrent.atomic.AtomicReference;

public class MockCameraImpl implements Camera, MockCamera {
    private final CameraCapabilities a;
    private final CameraParameters b = new CameraParameters();
    private final EventHandler c;
    private final Deque<byte[]> d = new ArrayDeque();
    private final AtomicReference<CameraCallback> e = new AtomicReference();
    private volatile boolean f = true;
    private volatile boolean g;
    private final Object h = new Object();
    private Object i;

    private static class EventHandler extends Handler {
        private final MockCameraImpl a;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MockCameraImpl.a(this.a, msg.obj, (long) msg.arg1);
                    return;
                default:
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Unknown message " + msg);
                        return;
                    }
                    return;
            }
        }

        public EventHandler(MockCameraImpl camera) throws CaptureException {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                throw new CaptureException("No looper created for this thread", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            }
            super(myLooper);
            this.a = camera;
        }
    }

    static /* synthetic */ boolean a(MockCameraImpl x0, byte[] x1, long x2) {
        Object a = x0.a(x1.length, x2);
        if (a == null) {
            if (Log.isLoggable(PackageInfo.TAG, 5)) {
                Log.w(PackageInfo.TAG, "Could not get the buffer for " + x1.length + " bytes in " + x2 + " ms. Skipping the frame.");
            }
            return false;
        }
        System.arraycopy(x1, 0, a, 0, Math.min(x1.length, a.length));
        CameraCallback cameraCallback = (CameraCallback) x0.e.get();
        if (x0.f && x0.g && cameraCallback != null) {
            cameraCallback.onCpuFrameCaptured(a, x0);
            return true;
        }
        if (Log.isLoggable(PackageInfo.TAG, 5)) {
            Log.w(PackageInfo.TAG, "The camera is not ready to process the frame. Skipping.");
        }
        return false;
    }

    public MockCameraImpl(CameraCapabilities capabilities) throws CaptureException {
        this.a = capabilities;
        this.c = new EventHandler(this);
        this.b.setImageFormat((ImageFormat) this.a.getSupportedImageFormats().iterator().next());
        this.b.setResolution((Resolution) this.a.getSupportedResolutions().iterator().next());
        this.b.setFpsRange((FpsRange) this.a.getSupportedFpsRanges().iterator().next());
    }

    public final CameraParameters getParameters() throws CaptureException {
        CameraParameters clone;
        synchronized (this.h) {
            if (this.f) {
                clone = this.b.clone();
            } else {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            }
        }
        return clone;
    }

    public final void setParameters(CameraParameters parameters) throws CaptureException {
        synchronized (this.h) {
            if (!this.f) {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (this.g) {
                throw new CaptureException("Can not set parameters during the capturing", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (!this.a.getSupportedImageFormats().contains(parameters.getImageFormat())) {
                throw new CaptureException("Image format " + parameters.getImageFormat() + " is not supported", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (!this.a.getSupportedResolutions().contains(parameters.getResolution())) {
                throw new CaptureException("Resolution " + parameters.getResolution() + " is not supported", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (this.a.getSupportedFpsRanges().contains(parameters.getFpsRange())) {
                this.b.setImageFormat(parameters.getImageFormat());
                this.b.setResolution(parameters.getResolution());
                this.b.setFpsRange(parameters.getFpsRange());
            } else {
                throw new CaptureException("FPS range " + parameters.getFpsRange() + " is not supported", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            }
        }
    }

    public final void setPreviewDisplay(Object display) throws CaptureException {
        synchronized (this.h) {
            if (!this.f) {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (this.g) {
                throw new CaptureException("Can not set preview display during the capturing", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else {
                this.i = display;
            }
        }
    }

    public final void setDisplayOrientation(int degrees) throws CaptureException {
        synchronized (this.h) {
            if (!this.f) {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (this.g) {
                throw new CaptureException("Can not set display orientation during the capturing", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            }
        }
    }

    public final void addCallbackBuffer(byte[] callbackBuffer) throws CaptureException {
        synchronized (this.d) {
            if (this.f) {
                this.d.addLast(callbackBuffer);
                this.d.notifyAll();
            } else {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            }
        }
    }

    public final void setCallback(CameraCallback cb) throws CaptureException {
        synchronized (this.h) {
            if (this.f) {
                if (cb != null) {
                    if (this.g) {
                        throw new CaptureException("Can not set callack during the capturing", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
                    }
                }
                if (cb == null) {
                    synchronized (this.d) {
                        this.d.clear();
                    }
                }
                this.e.set(cb);
            } else {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            }
        }
    }

    public final void startPreview() throws CaptureException {
        synchronized (this.h) {
            if (!this.f) {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (this.g) {
                throw new CaptureException("Preview was already started", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (this.i == null) {
                throw new CaptureException("No preview display set", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else {
                this.g = true;
            }
        }
    }

    public final void stopPreview() throws CaptureException {
        synchronized (this.h) {
            if (!this.f) {
                throw new CaptureException("The camera is not open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            } else if (this.g) {
                this.g = false;
            } else {
                throw new CaptureException("Preview was not started", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
            }
        }
    }

    public final void close() {
        synchronized (this.h) {
            if (this.f) {
                this.f = false;
            } else {
                throw new IllegalStateException("The camera is not open");
            }
        }
    }

    public final boolean pushFrame(byte[] data, int timeoutMs, int delayMs) {
        boolean sendMessageDelayed;
        synchronized (this.h) {
            if (this.g) {
                sendMessageDelayed = this.c.sendMessageDelayed(this.c.obtainMessage(0, timeoutMs, 0, data), (long) delayMs);
            } else {
                throw new IllegalStateException("Preview is not started");
            }
        }
        return sendMessageDelayed;
    }

    private byte[] a(int size, long timeoutUs) {
        if (this.e.get() == null) {
            return null;
        }
        synchronized (this.d) {
            if (this.d.isEmpty()) {
                try {
                    this.d.wait(timeoutUs);
                } catch (InterruptedException e) {
                    if (Log.isLoggable(PackageInfo.TAG, 5)) {
                        Log.w(PackageInfo.TAG, "Interrupted", e);
                    }
                    return null;
                }
            }
            try {
                byte[] buffer = (byte[]) this.d.pop();
                if (buffer == null) {
                    return null;
                } else if (buffer.length < size) {
                    throw new RuntimeException("Insuficient buffer size: " + buffer.length + " instead of " + size);
                } else {
                    return buffer;
                }
            } catch (EmptyStackException e2) {
                return null;
            }
        }
    }
}
