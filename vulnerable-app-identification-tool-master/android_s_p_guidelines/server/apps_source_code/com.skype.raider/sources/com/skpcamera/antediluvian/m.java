package com.skpcamera.antediluvian;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;

public final class m {
    private EGLDisplay a;
    private EGLContext b;
    private EGLConfig c;
    private int d;

    public m() {
        this(0);
    }

    public m(int flags) {
        this.a = EGL14.EGL_NO_DISPLAY;
        this.b = EGL14.EGL_NO_CONTEXT;
        this.c = null;
        this.d = -1;
        if (this.a != EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("EGL already set up");
        }
        EGLContext sharedContext = EGL14.EGL_NO_CONTEXT;
        this.a = EGL14.eglGetDisplay(0);
        if (this.a == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        int[] version = new int[2];
        if (EGL14.eglInitialize(this.a, version, 0, version, 1)) {
            EGLConfig config;
            EGLContext context;
            if ((flags & 2) != 0) {
                config = a(flags, 3);
                if (config != null) {
                    context = EGL14.eglCreateContext(this.a, config, sharedContext, new int[]{12440, 3, 12344}, 0);
                    if (EGL14.eglGetError() == 12288) {
                        this.c = config;
                        this.b = context;
                        this.d = 3;
                    }
                }
            }
            if (this.b == EGL14.EGL_NO_CONTEXT) {
                config = a(flags, 2);
                if (config == null) {
                    throw new RuntimeException("Unable to find a suitable EGLConfig");
                }
                context = EGL14.eglCreateContext(this.a, config, sharedContext, new int[]{12440, 2, 12344}, 0);
                a("eglCreateContext");
                this.c = config;
                this.b = context;
                this.d = 2;
            }
            int[] values = new int[1];
            EGL14.eglQueryContext(this.a, this.b, 12440, values, 0);
            new StringBuilder("EGLContext created, client version ").append(values[0]);
            return;
        }
        this.a = null;
        throw new RuntimeException("unable to initialize EGL14");
    }

    private EGLConfig a(int flags, int version) {
        int renderableType = 4;
        if (version >= 3) {
            renderableType = 68;
        }
        int[] attribList = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, renderableType, 12344, 0, 12344};
        if ((flags & 1) != 0) {
            attribList[10] = 12610;
            attribList[11] = 1;
        }
        EGLConfig[] configs = new EGLConfig[1];
        if (EGL14.eglChooseConfig(this.a, attribList, 0, configs, 0, 1, new int[1], 0)) {
            return configs[0];
        }
        new StringBuilder("unable to find RGB8888 / ").append(version).append(" EGLConfig");
        return null;
    }

    public final void a() {
        if (this.a != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglMakeCurrent(this.a, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            EGL14.eglDestroyContext(this.a, this.b);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.a);
        }
        this.a = EGL14.EGL_NO_DISPLAY;
        this.b = EGL14.EGL_NO_CONTEXT;
        this.c = null;
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.a != EGL14.EGL_NO_DISPLAY) {
                a();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public final void a(EGLSurface eglSurface) {
        EGL14.eglDestroySurface(this.a, eglSurface);
    }

    public final EGLSurface a(Object surface) {
        if ((surface instanceof Surface) || (surface instanceof SurfaceTexture)) {
            EGLSurface eglSurface = EGL14.eglCreateWindowSurface(this.a, this.c, surface, new int[]{12344}, 0);
            a("eglCreateWindowSurface");
            if (eglSurface != null) {
                return eglSurface;
            }
            throw new RuntimeException("surface was null");
        }
        throw new RuntimeException("invalid surface: " + surface);
    }

    public final void b(EGLSurface eglSurface) {
        EGLDisplay eGLDisplay = EGL14.EGL_NO_DISPLAY;
        if (!EGL14.eglMakeCurrent(this.a, eglSurface, eglSurface, this.b)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public final boolean c(EGLSurface eglSurface) {
        return EGL14.eglSwapBuffers(this.a, eglSurface);
    }

    public final void a(EGLSurface eglSurface, long nsecs) {
        EGLExt.eglPresentationTimeANDROID(this.a, eglSurface, nsecs);
    }

    public final boolean d(EGLSurface eglSurface) {
        return this.b.equals(EGL14.eglGetCurrentContext()) && eglSurface.equals(EGL14.eglGetCurrentSurface(12377));
    }

    public final int a(EGLSurface eglSurface, int what) {
        int[] value = new int[1];
        EGL14.eglQuerySurface(this.a, eglSurface, what, value, 0);
        return value[0];
    }

    private static void a(String msg) {
        int error = EGL14.eglGetError();
        if (error != 12288) {
            throw new RuntimeException(msg + ": EGL error: 0x" + Integer.toHexString(error));
        }
    }
}
