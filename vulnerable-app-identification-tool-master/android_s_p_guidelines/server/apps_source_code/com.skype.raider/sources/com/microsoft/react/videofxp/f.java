package com.microsoft.react.videofxp;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;
import com.facebook.common.logging.FLog;

final class f {
    private EGLDisplay a = EGL14.EGL_NO_DISPLAY;
    private EGLContext b = EGL14.EGL_NO_CONTEXT;
    private EGLSurface c = EGL14.EGL_NO_SURFACE;
    private EGLConfig[] d = new EGLConfig[1];
    private Surface e;
    private int f;
    private int g;

    public f(Surface surface) {
        if (surface == null) {
            throw new NullPointerException();
        }
        this.e = surface;
        this.a = EGL14.eglGetDisplay(0);
        if (this.a == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (EGL14.eglInitialize(this.a, iArr, 0, iArr, 1)) {
            int[] iArr2 = new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 12344};
            int[] iArr3 = new int[1];
            if (EGL14.eglChooseConfig(this.a, iArr2, 0, this.d, 0, this.d.length, iArr3, 0)) {
                this.b = EGL14.eglCreateContext(this.a, this.d[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
                a("eglCreateContext");
                if (this.b == null) {
                    throw new RuntimeException("null context");
                }
                this.c = EGL14.eglCreateWindowSurface(this.a, this.d[0], this.e, new int[]{12344}, 0);
                a("eglCreateWindowSurface");
                if (this.c == null) {
                    throw new RuntimeException("surface was null");
                }
                this.f = d();
                this.g = e();
                FLog.i("VideoFXPInputSurface", "InputSurface dimensions are " + this.f + " x " + this.g);
                return;
            }
            throw new RuntimeException("unable to find RGB888+recordable ES2 EGL config");
        }
        this.a = null;
        throw new RuntimeException("unable to initialize EGL14");
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
        this.e = null;
    }

    public final void b() {
        if (!EGL14.eglMakeCurrent(this.a, this.c, this.c, this.b)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public final boolean c() {
        return EGL14.eglSwapBuffers(this.a, this.c);
    }

    public final int d() {
        int[] value = new int[1];
        EGL14.eglQuerySurface(this.a, this.c, 12375, value, 0);
        return value[0];
    }

    public final int e() {
        int[] value = new int[1];
        EGL14.eglQuerySurface(this.a, this.c, 12374, value, 0);
        return value[0];
    }

    public final void a(long nsecs) {
        EGLExt.eglPresentationTimeANDROID(this.a, this.c, nsecs);
    }

    private static void a(String msg) {
        int error = EGL14.eglGetError();
        if (error != 12288) {
            throw new RuntimeException(msg + ": EGL error: 0x" + Integer.toHexString(error));
        }
    }
}
