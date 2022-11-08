package com.microsoft.dl.video.capture.impl;

import android.graphics.SurfaceTexture;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CameraCallback;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.graphics.egl.EGLException;
import com.microsoft.dl.video.graphics.egl.TargetSurfaceContext;
import com.microsoft.dl.video.graphics.gles.GLTexture;
import com.microsoft.dl.video.graphics.gles.GLTexture.Target;

public abstract class AbstractPreviewSurface {
    private final CameraCallback a;
    private final TargetSurfaceContext b;
    private GLTexture c;
    private SurfaceTexture d;

    private static final class DummyPreviewSurfaceTexture extends SurfaceTexture {
        public DummyPreviewSurfaceTexture(int texName) {
            super(texName);
        }
    }

    private static class OnFrameAvailableListener implements android.graphics.SurfaceTexture.OnFrameAvailableListener {
        private final AbstractPreviewSurface a;
        private final CameraCallback b;
        private final TargetSurfaceContext c;
        private final GLTexture d;

        public OnFrameAvailableListener(AbstractPreviewSurface previewSurface, CameraCallback callback, TargetSurfaceContext surface, GLTexture texture) {
            this.a = previewSurface;
            this.b = callback;
            this.c = surface;
            this.d = texture;
        }

        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            try {
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "Got a frame at " + surfaceTexture);
                }
                synchronized (this.c) {
                    if (this.a.d == null) {
                        this.b.onGpuFrameDropped();
                        return;
                    }
                    this.c.makeCurrent(true);
                    surfaceTexture.updateTexImage();
                    this.b.onGpuFrameCaptured(this.d.getTarget().getCode(), this.d.getName());
                    this.c.swapBuffers();
                    this.c.makeCurrent(false);
                }
            } catch (GraphicsException e) {
                this.b.onError(e);
            } catch (Throwable e2) {
                this.b.onError(new EGLException(e2, ErrorCode.ANDROID_EGL_RUNTIME_FAILURE));
            } catch (Throwable th) {
                this.c.makeCurrent(false);
            }
        }
    }

    public AbstractPreviewSurface(CameraCallback callback, TargetSurfaceContext surface) throws EGLException {
        this.a = callback;
        this.b = surface;
    }

    public void close() throws EGLException {
        synchronized (this.b) {
            c();
            this.b.close();
        }
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.d;
    }

    protected TargetSurfaceContext a() {
        return this.b;
    }

    protected final void b() throws GraphicsException {
        try {
            this.b.makeCurrent(true);
            this.c = new GLTexture(Target.TEXTURE_EXTERNAL_OES);
            this.d = new DummyPreviewSurfaceTexture(this.c.getName());
            this.d.setOnFrameAvailableListener(new OnFrameAvailableListener(this, this.a, this.b, this.c));
            this.b.makeCurrent(false);
        } catch (GraphicsException e) {
            c();
            throw e;
        } catch (Throwable th) {
            this.b.makeCurrent(false);
        }
    }

    protected final void c() {
        if (this.d != null) {
            this.d.release();
            this.d = null;
        }
        if (this.c != null) {
            this.c.close();
            this.c = null;
        }
    }
}
