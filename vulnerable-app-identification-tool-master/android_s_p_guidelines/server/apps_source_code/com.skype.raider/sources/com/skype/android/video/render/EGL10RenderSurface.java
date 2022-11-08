package com.skype.android.video.render;

import android.graphics.SurfaceTexture;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

class EGL10RenderSurface implements EGLRenderSurface {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private EGL10 egl;
    private EGLContext eglContext = EGL10.EGL_NO_CONTEXT;
    private EGLDisplay eglDisplay = EGL10.EGL_NO_DISPLAY;
    private EGLSurface eglSurface = EGL10.EGL_NO_SURFACE;

    EGL10RenderSurface() {
    }

    public void create(SurfaceTexture surfaceTexture) {
        this.egl = (EGL10) EGLContext.getEGL();
        this.eglDisplay = this.egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.eglDisplay == EGL10.EGL_NO_DISPLAY) {
            throw new EGLException("eglGetDisplay EGL10.EGL_NO_DISPLAY");
        }
        if (this.egl.eglInitialize(this.eglDisplay, new int[2])) {
            EGLConfig[] configs = new EGLConfig[1];
            if (!this.egl.eglChooseConfig(this.eglDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12352, 4, 12344}, configs, 1, new int[1])) {
                EGLException.check("eglChooseConfig");
            }
            this.eglContext = this.egl.eglCreateContext(this.eglDisplay, configs[0], EGL10.EGL_NO_CONTEXT, new int[]{EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
            check(this.egl, "eglCreateContext");
            this.eglSurface = this.egl.eglCreateWindowSurface(this.eglDisplay, configs[0], surfaceTexture, new int[]{12344});
            check(this.egl, "eglCreateWindowSurface");
            return;
        }
        throw new EGLException("eglInitialize");
    }

    public void makeCurrent(boolean current) {
        if (!this.egl.eglMakeCurrent(this.eglDisplay, current ? this.eglSurface : EGL10.EGL_NO_SURFACE, current ? this.eglSurface : EGL10.EGL_NO_SURFACE, current ? this.eglContext : EGL10.EGL_NO_CONTEXT)) {
            throw new EGLException("eglMakeCurrent");
        }
    }

    public void swapBuffers() {
        if (!this.egl.eglSwapBuffers(this.eglDisplay, this.eglSurface)) {
            throw new EGLException("eglSwapBuffers");
        }
    }

    public void destroy(boolean releaseThread) {
        if (this.eglDisplay != EGL10.EGL_NO_DISPLAY) {
            try {
                makeCurrent(false);
            } finally {
                this.egl.eglDestroySurface(this.eglDisplay, this.eglSurface);
                this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);
                this.egl.eglTerminate(this.eglDisplay);
                this.eglDisplay = EGL10.EGL_NO_DISPLAY;
                this.eglSurface = EGL10.EGL_NO_SURFACE;
                this.eglContext = EGL10.EGL_NO_CONTEXT;
            }
        }
    }

    private static void check(EGL10 egl, String message) {
        int error = egl.eglGetError();
        if (error != 12288) {
            throw new EGLException(error, message);
        }
    }
}
