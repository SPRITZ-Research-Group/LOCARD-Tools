package com.skype.android.video.render;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;

@TargetApi(17)
class EGL14RenderSurface implements EGLRenderSurface {
    private EGLContext eglContext = EGL14.EGL_NO_CONTEXT;
    private EGLDisplay eglDisplay = EGL14.EGL_NO_DISPLAY;
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;

    EGL14RenderSurface() {
    }

    public void create(SurfaceTexture surfaceTexture) {
        this.eglDisplay = EGL14.eglGetDisplay(0);
        if (this.eglDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new EGLException("eglGetDisplay EGL14.EGL_NO_DISPLAY");
        }
        int[] version = new int[2];
        if (EGL14.eglInitialize(this.eglDisplay, version, 0, version, 1)) {
            EGLConfig[] configs = new EGLConfig[1];
            if (!EGL14.eglChooseConfig(this.eglDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12352, 4, 12344}, 0, configs, 0, 1, new int[1], 0)) {
                EGLException.check("eglChooseConfig");
            }
            this.eglContext = EGL14.eglCreateContext(this.eglDisplay, configs[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
            EGLException.check("eglCreateContext");
            this.eglSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, configs[0], surfaceTexture, new int[]{12344}, 0);
            EGLException.check("eglCreateWindowSurface");
            return;
        }
        throw new EGLException("eglInitialize");
    }

    public void makeCurrent(boolean current) {
        if (!EGL14.eglMakeCurrent(this.eglDisplay, current ? this.eglSurface : EGL14.EGL_NO_SURFACE, current ? this.eglSurface : EGL14.EGL_NO_SURFACE, current ? this.eglContext : EGL14.EGL_NO_CONTEXT)) {
            throw new EGLException("eglMakeCurrent");
        }
    }

    public void swapBuffers() {
        if (!EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface)) {
            throw new EGLException("eglSwapBuffers");
        }
    }

    public void destroy(boolean releaseThread) {
        if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) {
            try {
                makeCurrent(false);
            } finally {
                EGL14.eglDestroySurface(this.eglDisplay, this.eglSurface);
                EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
                EGL14.eglTerminate(this.eglDisplay);
                if (releaseThread) {
                    EGL14.eglReleaseThread();
                }
                this.eglDisplay = EGL14.EGL_NO_DISPLAY;
                this.eglSurface = EGL14.EGL_NO_SURFACE;
                this.eglContext = EGL14.EGL_NO_CONTEXT;
            }
        }
    }
}
