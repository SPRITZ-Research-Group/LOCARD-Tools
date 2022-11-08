package com.microsoft.react.videofxp;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;

@TargetApi(17)
final class c implements e {
    private EGLDisplay a = EGL14.EGL_NO_DISPLAY;
    private EGLContext b = EGL14.EGL_NO_CONTEXT;
    private EGLSurface c = EGL14.EGL_NO_SURFACE;

    c() {
    }

    public final void a(SurfaceTexture surfaceTexture) {
        this.a = EGL14.eglGetDisplay(0);
        if (this.a == EGL14.EGL_NO_DISPLAY) {
            throw new d("eglGetDisplay EGL14.EGL_NO_DISPLAY");
        }
        int[] version = new int[2];
        if (EGL14.eglInitialize(this.a, version, 0, version, 1)) {
            EGLConfig[] configs = new EGLConfig[1];
            if (!EGL14.eglChooseConfig(this.a, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12352, 4, 12344}, 0, configs, 0, 1, new int[1], 0)) {
                d.a("eglChooseConfig");
            }
            this.b = EGL14.eglCreateContext(this.a, configs[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
            d.a("eglCreateContext");
            this.c = EGL14.eglCreateWindowSurface(this.a, configs[0], surfaceTexture, new int[]{12344}, 0);
            d.a("eglCreateWindowSurface");
            return;
        }
        throw new d("eglInitialize");
    }

    public final void a(boolean current) {
        if (!EGL14.eglMakeCurrent(this.a, current ? this.c : EGL14.EGL_NO_SURFACE, current ? this.c : EGL14.EGL_NO_SURFACE, current ? this.b : EGL14.EGL_NO_CONTEXT)) {
            throw new d("eglMakeCurrent");
        }
    }

    public final void a() {
        if (!EGL14.eglSwapBuffers(this.a, this.c)) {
            throw new d("eglSwapBuffers");
        }
    }

    public final void b(boolean releaseThread) {
        if (this.a != EGL14.EGL_NO_DISPLAY) {
            try {
                a(false);
            } finally {
                EGL14.eglDestroySurface(this.a, this.c);
                EGL14.eglDestroyContext(this.a, this.b);
                EGL14.eglTerminate(this.a);
                if (releaseThread) {
                    EGL14.eglReleaseThread();
                }
                this.a = EGL14.EGL_NO_DISPLAY;
                this.c = EGL14.EGL_NO_SURFACE;
                this.b = EGL14.EGL_NO_CONTEXT;
            }
        }
    }
}
