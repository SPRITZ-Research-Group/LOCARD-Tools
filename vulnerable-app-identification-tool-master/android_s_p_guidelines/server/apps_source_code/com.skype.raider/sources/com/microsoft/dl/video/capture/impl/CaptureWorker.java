package com.microsoft.dl.video.capture.impl;

import android.graphics.SurfaceTexture;
import android.os.Looper;
import com.microsoft.dl.utils.Clock;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.utils.Systrace;
import com.microsoft.dl.utils.Systrace.Section;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.ErrorCodeException;
import com.microsoft.dl.video.Failure;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.Camera;
import com.microsoft.dl.video.capture.api.CameraCallback;
import com.microsoft.dl.video.capture.api.CameraManagerSingleton;
import com.microsoft.dl.video.capture.api.CameraParameters;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.utils.Resolution;
import java.io.Closeable;
import java.lang.reflect.Array;

public class CaptureWorker implements CameraCallback, Closeable, Runnable {
    private static long a = 500;
    private final String b;
    private final int c;
    private final int d;
    private final long e;
    private byte[][] f = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{1, 0}));
    private final boolean g;
    private final Object h = new Object();
    private OffscreenPreviewSurface i;
    private final Object j = new Object();
    private PassthroughPreviewSurface k;
    private final Object l = new Object();
    private Camera m;
    private boolean n;
    private Looper o;
    private volatile CaptureException p;
    private CameraParameters q;
    private Object r;
    private int s = -1;
    private Orientation t;
    private CallbackType u = CallbackType.CPU;
    private Object v = new Object();
    private int w = -1;
    private int x = -1;

    public enum CallbackType {
        CPU,
        GPU
    }

    private enum Orientation {
        LANDSCAPE_LEFT(false, false, false),
        PORTRAIT(false, true, true),
        LANDSCAPE_RIGHT(true, true, false),
        PORTRAIT_UPSIDEDOWN(true, false, true);
        
        private final boolean a;
        private final boolean b;
        private final boolean c;

        private Orientation(boolean isVertFlipped, boolean isHorizFlipped, boolean isTransposed) {
            this.a = isVertFlipped;
            this.b = isHorizFlipped;
            this.c = isTransposed;
        }

        public final boolean isVertFlipped() {
            return this.a;
        }

        public final boolean isHorizFlipped() {
            return this.b;
        }

        public final boolean isTransposed() {
            return this.c;
        }

        public final String toString() {
            return name() + " (" + (this.a ? "V" : "-") + (this.b ? "H" : "-") + (this.c ? "T" : "-") + ")";
        }
    }

    private static native void onCapturingFailed(long j, long j2);

    private static native void onCpuFrameCaptured(byte[] bArr, long j, int i, boolean z, boolean z2, boolean z3, long j2);

    private static native void onGpuFrameCaptured(int i, int i2, long j, int i3, boolean z, boolean z2, boolean z3, long j2);

    private static native void onGpuFrameDropped(long j);

    public CaptureWorker(int cameraId, int numBuffers, long nativeContext, boolean useAutoOffscreenPreviewSurface, String debugName) {
        this.b = debugName;
        this.c = cameraId;
        this.d = numBuffers;
        this.e = nativeContext;
        this.g = useAutoOffscreenPreviewSurface;
    }

    public final boolean isOpen(long timeoutMs) throws InterruptedException, CaptureException {
        boolean z;
        synchronized (this.l) {
            long expireTimeMs = System.currentTimeMillis() + timeoutMs;
            while (this.m == null) {
                if (this.p != null) {
                    CaptureException exception = this.p;
                    this.p = null;
                    throw exception;
                }
                long waitMs = expireTimeMs - System.currentTimeMillis();
                if (waitMs <= 0) {
                    z = false;
                    break;
                }
                this.l.wait(waitMs);
            }
            z = true;
        }
        return z;
    }

    public boolean shouldUpdateParameters(CameraParameters parameters, int modeId) throws CaptureException {
        if (parameters == null || modeId < 0) {
            throw new CaptureException("parameters must not be null and modeId must be non-negative", ErrorCode.ANDROID_CAPTURER_INVALID_CAMERA_PARAMETERS);
        } else if (parameters.equals(this.q) && modeId == this.x) {
            return false;
        } else {
            return true;
        }
    }

    public void setParameters(CameraParameters parameters, int modeId) throws CaptureException {
        if (shouldUpdateParameters(parameters, modeId)) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting capture worker(" + this.b + "): parameters=[" + parameters + "], modeId=" + modeId);
            }
            synchronized (this.l) {
                if (this.m == null) {
                    throw new CaptureException("camera is not open", ErrorCode.ANDROID_CAPTURER_CAMERA_NOT_OPEN);
                }
                this.m.setParameters(parameters);
            }
            this.q = parameters;
            this.x = modeId;
        }
    }

    public boolean shouldUpdateFramerate(int framerate) throws CaptureException {
        if (framerate < 0) {
            throw new CaptureException("framerate must be non-negative", ErrorCode.ANDROID_CAPTURER_INVALID_FRAME_RATE);
        } else if (framerate != this.w) {
            return true;
        } else {
            return false;
        }
    }

    public void setFramerate(int framerate) throws CaptureException {
        if (shouldUpdateFramerate(framerate)) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting capture worker(" + this.b + "): framerate=" + (((float) framerate) / 1000.0f) + " fps");
            }
            this.w = framerate;
        }
    }

    public boolean shouldUpdatePreviewDisplay(Object previewDisplay) throws GraphicsException {
        if (previewDisplay != (this.u == CallbackType.GPU ? b().getExternalPreviewDisplay() : this.r)) {
            return true;
        }
        return false;
    }

    public void setPreviewDisplay(Object previewDisplay) throws CaptureException, GraphicsException {
        if (shouldUpdatePreviewDisplay(previewDisplay)) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting capture worker(" + this.b + "): previewDisplay=[" + previewDisplay + "]");
            }
            synchronized (this.l) {
                if (this.m == null) {
                    throw new CaptureException("camera is not open", ErrorCode.ANDROID_CAPTURER_CAMERA_NOT_OPEN);
                }
                if (this.u == CallbackType.GPU) {
                    this.m.setPreviewDisplay(null);
                    PassthroughPreviewSurface passthroughPreviewSurface = b();
                    passthroughPreviewSurface.allocSurfaceTexture(previewDisplay);
                    previewDisplay = passthroughPreviewSurface.getSurfaceTexture();
                }
                this.m.setPreviewDisplay(previewDisplay);
            }
            this.r = previewDisplay;
        }
    }

    public boolean shouldUpdateOrientationAngle(int orientationAngle) throws CaptureException {
        if (orientationAngle < 0 || orientationAngle % 90 > 0) {
            throw new CaptureException("orientationAngle must be non-negative and divisible by 90", ErrorCode.ANDROID_CAPTURER_INVALID_ORIENTATION);
        } else if (orientationAngle != this.s) {
            return true;
        } else {
            return false;
        }
    }

    public void setOrientationAngle(int orientationAngle) throws CaptureException {
        if (shouldUpdateOrientationAngle(orientationAngle)) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting capture worker(" + this.b + "): orientationAngle=" + orientationAngle);
            }
            synchronized (this.l) {
                if (this.m == null) {
                    throw new CaptureException("camera is not open", ErrorCode.ANDROID_CAPTURER_CAMERA_NOT_OPEN);
                }
                this.m.setDisplayOrientation(orientationAngle);
            }
            this.s = orientationAngle;
            this.t = Orientation.values()[(orientationAngle % 360) / 90];
        }
    }

    public boolean shouldUpdateCallbackType(CallbackType callbackType) {
        if (callbackType != this.u) {
            return true;
        }
        return false;
    }

    public void setCallbackType(CallbackType callbackType) throws CaptureException, GraphicsException {
        if (shouldUpdateCallbackType(callbackType)) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting capture worker(" + this.b + "): callbackType=[" + callbackType.name() + "]");
            }
            if (callbackType == CallbackType.GPU) {
                synchronized (this.v) {
                    this.u = callbackType;
                }
                setPreviewDisplay(this.r);
                return;
            }
            synchronized (this.v) {
                this.u = callbackType;
            }
            PassthroughPreviewSurface passthroughPreviewSurface = b();
            Object externalPreviewDisplay = passthroughPreviewSurface.getExternalPreviewDisplay();
            passthroughPreviewSurface.releaseSurfaceTexture();
            setPreviewDisplay(externalPreviewDisplay);
        }
    }

    public boolean start() throws CaptureException, GraphicsException {
        int i = 0;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Starting capture worker(" + this.b + "): parameters=[" + this.q + "], modeId=" + this.x + ", framerate=" + (((float) this.w) / 1000.0f) + " fpsorientationAngle=" + this.s + ", previewDisplay=[" + this.r + "]");
        }
        if (this.q == null) {
            if (Log.isLoggable(PackageInfo.TAG, 5)) {
                Log.w(PackageInfo.TAG, "Capture worker was not started, parameters are not set (" + this.b + ")");
            }
            return false;
        } else if (this.x < 0) {
            if (Log.isLoggable(PackageInfo.TAG, 5)) {
                Log.w(PackageInfo.TAG, "Capture worker was not started, modeId is not set (" + this.b + ")");
            }
            return false;
        } else if (this.w < 0) {
            if (Log.isLoggable(PackageInfo.TAG, 5)) {
                Log.w(PackageInfo.TAG, "Capture worker was not started, framerate is not set (" + this.b + ")");
            }
            return false;
        } else {
            if (this.s < 0) {
                if (Log.isLoggable(PackageInfo.TAG, 5)) {
                    Log.w(PackageInfo.TAG, "OrientationAngle is not set, defaulting to 0 (" + this.b + ")");
                }
                setOrientationAngle(0);
            }
            if (this.r == null) {
                if (this.g) {
                    setPreviewDisplay(getOffscreenPreviewSurface());
                } else {
                    if (Log.isLoggable(PackageInfo.TAG, 5)) {
                        Log.w(PackageInfo.TAG, "Capture worker was not started, preview surface is not set (" + this.b + ")");
                    }
                    return false;
                }
            }
            int sampleSize = this.q.getImageFormat().getSampleSize(this.q.getResolution());
            if (sampleSize > this.f[0].length) {
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "Allocating " + this.d + " preview buffers for resolution " + this.q.getResolution() + ", sample size is " + (((float) sampleSize) / 1024.0f) + " kB (" + this.b + ")");
                }
                this.f = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.d, sampleSize});
            }
            try {
                if (isOpen(a)) {
                    if (Log.isLoggable(PackageInfo.TAG, 4)) {
                        Log.i(PackageInfo.TAG, "camera isOpen time outed (" + this.b + ")");
                    }
                    synchronized (this.l) {
                        if (this.m == null) {
                            throw new CaptureException("camera is not open", ErrorCode.ANDROID_CAPTURER_CAMERA_NOT_OPEN);
                        }
                        byte[][] bArr = this.f;
                        int length = bArr.length;
                        while (i < length) {
                            this.m.addCallbackBuffer(bArr[i]);
                            i++;
                        }
                        this.m.setCallback(this);
                        this.m.startPreview();
                        this.n = true;
                        if (Log.isLoggable(PackageInfo.TAG, 4)) {
                            Log.i(PackageInfo.TAG, "Capture worker started (" + this.b + ")");
                        }
                    }
                    return true;
                }
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Capture worker start failed (" + this.b + ")");
                }
                return false;
            } catch (Throwable e) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "camera is not open, throwing  InterruptedException");
                }
                throw new CaptureException(e, ErrorCode.ANDROID_CAMERA_OPEN_INTERRUPTED);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void stop() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Stopping capture worker (" + this.b + ")");
        }
        synchronized (this.l) {
            if (this.m != null) {
                this.n = false;
                this.m.setCallback(null);
                this.m.stopPreview();
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "Capture worker stopped (" + this.b + ")");
                }
            } else if (Log.isLoggable(PackageInfo.TAG, 5)) {
                Log.w(PackageInfo.TAG, "Capture worker has no open camera (" + this.b + ")");
            }
        }
    }

    public final void close() {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Closing capture worker (" + this.b + ")");
        }
        synchronized (this.l) {
            if (this.o != null) {
                this.o.quit();
                this.o = null;
            }
        }
    }

    public final void run() {
        Camera camera = null;
        CaptureException exception = null;
        try {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Entering thread proc (" + this.b + ")");
            }
            Looper.prepare();
            camera = CameraManagerSingleton.getInstance().openCamera(this.c);
        } catch (CaptureException e) {
            exception = e;
        } catch (Throwable e2) {
            exception = new CaptureException(e2, ErrorCode.ANDROID_CAMERA_OPEN_FAILED);
        }
        try {
            synchronized (this.l) {
                this.m = camera;
                this.p = exception;
                this.o = Looper.myLooper();
                this.l.notifyAll();
            }
            Looper.loop();
            try {
                synchronized (this.l) {
                    this.m = null;
                    if (camera != null) {
                        camera.close();
                    }
                    this.l.notifyAll();
                }
            } catch (ErrorCodeException e3) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e3);
                }
                onCapturingFailed(new Failure(e3).getNativeContext(), this.e);
            } catch (Throwable e22) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e22);
                }
                onCapturingFailed(new Failure(new CaptureException(e22, ErrorCode.ANDROID_CAMERA_CLOSE_FAILED)).getNativeContext(), this.e);
            }
            a();
            c();
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Leaving thread proc (" + this.b + ")");
            }
        } catch (Throwable e222) {
            try {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e222);
                }
                onCapturingFailed(new Failure(new CaptureException(e222, ErrorCode.ANDROID_UNCHECKED_EXCEPTION)).getNativeContext(), this.e);
                try {
                    synchronized (this.l) {
                        this.m = null;
                        if (camera != null) {
                            camera.close();
                        }
                        this.l.notifyAll();
                        a();
                        c();
                        if (Log.isLoggable(PackageInfo.TAG, 4)) {
                            Log.i(PackageInfo.TAG, "Leaving thread proc (" + this.b + ")");
                        }
                    }
                } catch (ErrorCodeException e32) {
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e32);
                    }
                    onCapturingFailed(new Failure(e32).getNativeContext(), this.e);
                } catch (Throwable e2222) {
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e2222);
                    }
                    onCapturingFailed(new Failure(new CaptureException(e2222, ErrorCode.ANDROID_CAMERA_CLOSE_FAILED)).getNativeContext(), this.e);
                }
            } catch (Throwable th) {
                try {
                    synchronized (this.l) {
                        this.m = null;
                        if (camera != null) {
                            camera.close();
                        }
                        this.l.notifyAll();
                        a();
                        c();
                        if (Log.isLoggable(PackageInfo.TAG, 4)) {
                            Log.i(PackageInfo.TAG, "Leaving thread proc (" + this.b + ")");
                        }
                    }
                } catch (ErrorCodeException e322) {
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e322);
                    }
                    onCapturingFailed(new Failure(e322).getNativeContext(), this.e);
                } catch (Throwable e22222) {
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e22222);
                    }
                    onCapturingFailed(new Failure(new CaptureException(e22222, ErrorCode.ANDROID_CAMERA_CLOSE_FAILED)).getNativeContext(), this.e);
                }
            }
        }
    }

    public final void onCpuFrameCaptured(byte[] data, Camera camera) {
        long ts = Clock.getPlatformTime();
        Systrace.begin(Section.CaptureVideo);
        try {
            synchronized (this.v) {
                if (this.u == CallbackType.CPU) {
                    onCpuFrameCaptured(data, ts, this.x, this.t.isVertFlipped(), this.t.isHorizFlipped(), this.t.isTransposed(), this.e);
                }
            }
            synchronized (this.l) {
                if (this.n) {
                    camera.addCallbackBuffer(data);
                }
            }
            Systrace.end();
        } catch (ErrorCodeException e) {
            try {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Could not return buffer to the camera (" + this.b + ")", e);
                }
                onCapturingFailed(new Failure(e).getNativeContext(), this.e);
            } finally {
                Systrace.end();
            }
        } catch (Throwable e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e2);
            }
            onCapturingFailed(new Failure(new CaptureException(e2, ErrorCode.ANDROID_CAMERA_RUNTIME_FAILURE)).getNativeContext(), this.e);
            Systrace.end();
        }
    }

    public void onGpuFrameCaptured(int textureTarget, int textureId) {
        long ts = Clock.getPlatformTime();
        synchronized (this.v) {
            if (this.u == CallbackType.GPU) {
                onGpuFrameCaptured(textureTarget, textureId, ts, this.x, this.t.isVertFlipped(), this.t.isHorizFlipped(), this.t.isTransposed(), this.e);
            }
        }
    }

    public void onGpuFrameDropped() {
        synchronized (this.v) {
            if (this.u == CallbackType.GPU) {
                onGpuFrameDropped(this.e);
            }
        }
    }

    public final void onError(ErrorCodeException e) {
        if (Log.isLoggable(PackageInfo.TAG, 6)) {
            Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e);
        }
        onCapturingFailed(new Failure(e).getNativeContext(), this.e);
        close();
    }

    public SurfaceTexture getOffscreenPreviewSurface() throws GraphicsException {
        SurfaceTexture surfaceTexture;
        synchronized (this.h) {
            if (this.i == null) {
                this.i = new OffscreenPreviewSurface(this);
            }
            this.i.allocSurfaceTexture(new Resolution(1, 1));
            surfaceTexture = this.i.getSurfaceTexture();
        }
        return surfaceTexture;
    }

    private void a() {
        try {
            OffscreenPreviewSurface surface;
            synchronized (this.h) {
                surface = this.i;
                this.i = null;
            }
            if (surface != null) {
                surface.close();
            }
        } catch (ErrorCodeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e);
            }
            onCapturingFailed(new Failure(e).getNativeContext(), this.e);
        } catch (Throwable e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e2);
            }
            onCapturingFailed(new Failure(new CaptureException(e2, ErrorCode.ANDROID_CAPTURER_OFFSCREEN_SURFACE_CLOSE_FAILED)).getNativeContext(), this.e);
        }
    }

    private PassthroughPreviewSurface b() throws GraphicsException {
        PassthroughPreviewSurface passthroughPreviewSurface;
        synchronized (this.j) {
            if (this.k == null) {
                this.k = new PassthroughPreviewSurface(this);
            }
            passthroughPreviewSurface = this.k;
        }
        return passthroughPreviewSurface;
    }

    private void c() {
        try {
            PassthroughPreviewSurface surface;
            synchronized (this.j) {
                surface = this.k;
                this.k = null;
            }
            if (surface != null) {
                surface.close();
            }
        } catch (ErrorCodeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e);
            }
            onCapturingFailed(new Failure(e).getNativeContext(), this.e);
        } catch (Throwable e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.b + ")", e2);
            }
            onCapturingFailed(new Failure(new CaptureException(e2, ErrorCode.ANDROID_CAPTURER_PASSTHROUGH_SURFACE_CLOSE_FAILED)).getNativeContext(), this.e);
        }
    }
}
