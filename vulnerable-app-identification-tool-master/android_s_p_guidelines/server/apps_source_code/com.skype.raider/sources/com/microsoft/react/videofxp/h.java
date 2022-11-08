package com.microsoft.react.videofxp;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.view.Surface;
import com.facebook.common.logging.FLog;

final class h implements OnFrameAvailableListener {
    private EGLDisplay a = EGL14.EGL_NO_DISPLAY;
    private EGLContext b = EGL14.EGL_NO_CONTEXT;
    private EGLSurface c = EGL14.EGL_NO_SURFACE;
    private SurfaceTexture d;
    private Surface e;
    private Object f = new Object();
    private boolean g;
    private k h;

    public h(int lensMode, float lensIntensity, Bitmap overlay) {
        this.h = new k(lensMode, lensIntensity, overlay);
        this.h.d();
        FLog.i("VideoFXPOutputSurface", "textureID=" + this.h.a());
        this.d = new SurfaceTexture(this.h.a());
        this.d.setOnFrameAvailableListener(this);
        this.e = new Surface(this.d);
    }

    public final void a() {
        if (this.a != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglDestroySurface(this.a, this.c);
            EGL14.eglDestroyContext(this.a, this.b);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.a);
        }
        this.e.release();
        this.a = EGL14.EGL_NO_DISPLAY;
        this.b = EGL14.EGL_NO_CONTEXT;
        this.c = EGL14.EGL_NO_SURFACE;
        this.h = null;
        this.e = null;
        this.d = null;
    }

    public final Surface b() {
        return this.e;
    }

    public final void c() {
        synchronized (this.f) {
            do {
                if (this.g) {
                    this.g = false;
                } else {
                    try {
                        this.f.wait(10000);
                        FLog.i("VideoFXPOutputSurface", "wait timed out with " + this.g);
                    } catch (InterruptedException ie) {
                        throw new RuntimeException(ie);
                    }
                }
            } while (this.g);
            throw new RuntimeException("Surface frame wait timed out");
        }
        k.b("before updateTexImage");
        this.d.updateTexImage();
    }

    public final void d() {
        this.h.a(this.d);
    }

    public final void onFrameAvailable(SurfaceTexture st) {
        FLog.i("VideoFXPOutputSurface", "new frame available");
        synchronized (this.f) {
            if (this.g) {
                throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
            }
            this.g = true;
            this.f.notifyAll();
        }
    }
}
