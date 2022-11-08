package com.microsoft.dl.video.graphics.egl;

import com.microsoft.dl.DiagUtils;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.graphics.egl.EGLUtils.ConfigAttribute;
import com.microsoft.dl.video.graphics.egl.EGLUtils.ContextAttribute;
import com.microsoft.dl.video.graphics.egl.EGLUtils.ContextAttributesBuilder;
import com.microsoft.dl.video.graphics.egl.EGLUtils.DisplayAttribute;
import com.microsoft.dl.video.graphics.egl.EGLUtils.SurfaceAttribute;
import com.microsoft.dl.video.utils.Resolution;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

abstract class AbstractTargetSurfaceContext implements TargetSurfaceContext {
    protected static final EGL10 a = ((EGL10) EGLContext.getEGL());
    protected final String b = DiagUtils.getObjName(this);
    protected EGLDisplay c = EGL10.EGL_NO_DISPLAY;
    protected EGLContext d = EGL10.EGL_NO_CONTEXT;
    protected EGLSurface e = EGL10.EGL_NO_SURFACE;
    protected EGLConfig f;
    protected Resolution g;

    public AbstractTargetSurfaceContext(int[] configAttributes) throws EGLException {
        int[] contextAttributes = new ContextAttributesBuilder().contextClientVersion(2).build();
        EGLDisplay eglGetDisplay = a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (eglGetDisplay == null || eglGetDisplay == EGL10.EGL_NO_DISPLAY) {
            throw new EGLException("EGL.eglGetDisplay() has failed", a.eglGetError(), ErrorCode.ANDROID_EGL_GET_DISPLAY_FAILED);
        }
        EGLException.checkAfter("EGL.eglGetDisplay()", ErrorCode.ANDROID_EGL_GET_DISPLAY_FAILED);
        if (a.eglInitialize(eglGetDisplay, new int[2])) {
            EGLException.checkAfter("EGL.eglInitialize()", ErrorCode.ANDROID_EGL_INITIALIZE_FAILED);
            this.c = eglGetDisplay;
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, this.b + " display created: [0x" + Integer.toHexString(System.identityHashCode(this.c)) + "] " + b());
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr = new int[1];
            if (a.eglChooseConfig(this.c, configAttributes, eGLConfigArr, 1, iArr)) {
                EGLException.checkAfter("EGL.eglChooseConfig()", ErrorCode.ANDROID_EGL_CHOOSE_CONFIG_FAILED);
                if (iArr[0] <= 0) {
                    throw new EGLException("EGL.eglChooseConfig() returned 0 configs", a.eglGetError(), ErrorCode.ANDROID_EGL_NO_CONFIGS);
                }
                this.f = eGLConfigArr[0];
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, this.b + " config created: [0x" + Integer.toHexString(System.identityHashCode(this.f)) + "] " + c());
                }
                EGLContext eglCreateContext = a.eglCreateContext(this.c, this.f, EGL10.EGL_NO_CONTEXT, contextAttributes);
                if (eglCreateContext == null || eglCreateContext == EGL10.EGL_NO_CONTEXT) {
                    throw new EGLException("EGL.eglCreateContext() has failed", a.eglGetError(), ErrorCode.ANDROID_EGL_CREATE_CONTEXT_FAILED);
                }
                EGLException.checkAfter("EGL .eglCreateContext()", ErrorCode.ANDROID_EGL_CREATE_CONTEXT_FAILED);
                this.d = eglCreateContext;
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, this.b + " context created: [0x" + Integer.toHexString(System.identityHashCode(this.d)) + "] " + d());
                }
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, this.b + " created");
                    return;
                }
                return;
            }
            throw new EGLException("EGL.eglChooseConfig() has failed", a.eglGetError(), ErrorCode.ANDROID_EGL_CHOOSE_CONFIG_FAILED);
        }
        throw new EGLException("EGL.eglInitialize() has failed", a.eglGetError(), ErrorCode.ANDROID_EGL_INITIALIZE_FAILED);
    }

    public Resolution getSurfaceSize() throws EGLException {
        return this.g;
    }

    public boolean isCurrent() {
        return a.eglGetCurrentDisplay().equals(this.c) && a.eglGetCurrentContext().equals(this.d) && a.eglGetCurrentSurface(12377).equals(this.e);
    }

    public void makeCurrent(boolean isCurrent) throws EGLException {
        if (this.e != EGL10.EGL_NO_SURFACE) {
            boolean eglMakeCurrent;
            if (isCurrent) {
                eglMakeCurrent = a.eglMakeCurrent(this.c, this.e, this.e, this.d);
            } else {
                eglMakeCurrent = a.eglMakeCurrent(this.c, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            }
            if (eglMakeCurrent) {
                EGLException.checkAfter("EGL.eglMakeCurrent()", ErrorCode.ANDROID_EGL_MAKE_CURRENT_FAILED);
                return;
            }
            throw new EGLException("EGL.eglMakeCurrent() has failed in " + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_MAKE_CURRENT_FAILED);
        }
    }

    public void swapBuffers() throws GraphicsException {
        if (this.c == EGL10.EGL_NO_DISPLAY) {
            throw new EGLException("No display", ErrorCode.ANDROID_EGL_NO_DISPLAY);
        } else if (this.e == EGL10.EGL_NO_SURFACE) {
            throw new EGLException("No surface", ErrorCode.ANDROID_EGL_NO_SURFACE);
        } else if (a.eglSwapBuffers(this.c, this.e)) {
            EGLException.checkAfter("EGL.swapBuffers()", ErrorCode.ANDROID_EGL_SWAP_BUFFERS_FAILED);
        } else {
            throw new EGLException("EGL.eglSwapBuffers() has failed in " + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_SWAP_BUFFERS_FAILED);
        }
    }

    public void close() throws EGLException {
        Exception e;
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, this.b + " closing");
        }
        Exception exception = null;
        try {
            releaseSurface();
        } catch (EGLException e2) {
            exception = e2;
        } catch (RuntimeException e3) {
            exception = e3;
        }
        try {
            if (!(this.c == EGL10.EGL_NO_DISPLAY || this.d == EGL10.EGL_NO_CONTEXT)) {
                if (a.eglDestroyContext(this.c, this.d)) {
                    EGLException.checkAfter("EGL.eglDestroyContext()", ErrorCode.ANDROID_EGL_DESTROY_CONTEXT_FAILED);
                    this.d = EGL10.EGL_NO_CONTEXT;
                } else {
                    throw new EGLException("EGL.eglDestroyContext() has failed in " + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_DESTROY_CONTEXT_FAILED);
                }
            }
        } catch (Exception e4) {
            e = e4;
            if (exception == null) {
                exception = e;
            }
            if (this.c != EGL10.EGL_NO_DISPLAY) {
                if (a.eglTerminate(this.c)) {
                    throw new EGLException("EGL.eglTerminate() has failed in " + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_TERMINATE_FAILED);
                }
                EGLException.checkAfter("EGL.eglTerminate()", ErrorCode.ANDROID_EGL_TERMINATE_FAILED);
                this.c = EGL10.EGL_NO_DISPLAY;
            }
            if (!(exception instanceof EGLException)) {
                throw ((EGLException) exception);
            } else if (!(exception instanceof RuntimeException)) {
                throw ((RuntimeException) exception);
            } else if (!Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, this.b + " closed");
            }
        } catch (Exception e42) {
            e = e42;
            if (exception == null) {
                exception = e;
            }
            if (this.c != EGL10.EGL_NO_DISPLAY) {
                if (a.eglTerminate(this.c)) {
                    throw new EGLException("EGL.eglTerminate() has failed in " + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_TERMINATE_FAILED);
                }
                EGLException.checkAfter("EGL.eglTerminate()", ErrorCode.ANDROID_EGL_TERMINATE_FAILED);
                this.c = EGL10.EGL_NO_DISPLAY;
            }
            if (!(exception instanceof EGLException)) {
                throw ((EGLException) exception);
            } else if (!(exception instanceof RuntimeException)) {
                throw ((RuntimeException) exception);
            } else if (!Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, this.b + " closed");
            }
        }
        try {
            if (this.c != EGL10.EGL_NO_DISPLAY) {
                if (a.eglTerminate(this.c)) {
                    throw new EGLException("EGL.eglTerminate() has failed in " + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_TERMINATE_FAILED);
                }
                EGLException.checkAfter("EGL.eglTerminate()", ErrorCode.ANDROID_EGL_TERMINATE_FAILED);
                this.c = EGL10.EGL_NO_DISPLAY;
            }
        } catch (Exception e422) {
            e = e422;
        } catch (Exception e4222) {
            e = e4222;
        }
        if (!(exception instanceof EGLException)) {
            throw ((EGLException) exception);
        } else if (!(exception instanceof RuntimeException)) {
            throw ((RuntimeException) exception);
        } else if (!Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, this.b + " closed");
        }
        if (exception == null) {
            exception = e;
        }
        if (!(exception instanceof EGLException)) {
            throw ((EGLException) exception);
        } else if (!(exception instanceof RuntimeException)) {
            throw ((RuntimeException) exception);
        } else if (!Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, this.b + " closed");
        }
    }

    protected void releaseSurface() throws EGLException {
        if (this.c != EGL10.EGL_NO_DISPLAY && this.e != EGL10.EGL_NO_SURFACE) {
            if (a.eglDestroySurface(this.c, this.e)) {
                EGLException.checkAfter("EGL.eglDestroySurface()", ErrorCode.ANDROID_EGL_DESTROY_SURFACE_FAILED);
                this.e = EGL10.EGL_NO_SURFACE;
                this.g = null;
                return;
            }
            throw new EGLException("EGL.eglDestroySurface() has failed in" + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_DESTROY_SURFACE_FAILED);
        }
    }

    private String b() {
        StringBuilder str = new StringBuilder();
        for (DisplayAttribute attr : DisplayAttribute.values()) {
            if (str.length() > 0) {
                str.append(", ");
            }
            str.append(attr);
            str.append("='");
            str.append(a.eglQueryString(this.c, attr.getCode()));
            str.append('\'');
        }
        return str.toString();
    }

    private String c() {
        StringBuilder str = new StringBuilder();
        int[] value = new int[]{0};
        for (ConfigAttribute attr : ConfigAttribute.values()) {
            if (a.eglGetConfigAttrib(this.c, this.f, attr.getCode(), value)) {
                if (str.length() > 0) {
                    str.append(", ");
                }
                str.append(attr);
                str.append("=");
                str.append(attr.parseValue(value[0]));
            }
        }
        return str.toString();
    }

    private String d() {
        StringBuilder str = new StringBuilder();
        int[] value = new int[]{0};
        for (ContextAttribute attr : ContextAttribute.values()) {
            if (a.eglQueryContext(this.c, this.d, attr.getCode(), value)) {
                if (str.length() > 0) {
                    str.append(", ");
                }
                str.append(attr);
                str.append("=");
                str.append(attr.parseValue(value[0]));
            }
        }
        return str.toString();
    }

    protected final String a() {
        StringBuilder str = new StringBuilder();
        int[] value = new int[]{0};
        for (SurfaceAttribute attr : SurfaceAttribute.values()) {
            if (a.eglQuerySurface(this.c, this.e, attr.getCode(), value)) {
                if (str.length() > 0) {
                    str.append(", ");
                }
                str.append(attr);
                str.append("=");
                str.append(attr.parseValue(value[0]));
            }
        }
        return str.toString();
    }

    public String toString() {
        return this.b + " [eglDisplay=0x" + Integer.toHexString(System.identityHashCode(this.c)) + ", eglConfig=0x" + Integer.toHexString(System.identityHashCode(this.f)) + ", eglContext=0x" + Integer.toHexString(System.identityHashCode(this.d)) + ", eglSurface=0x" + Integer.toHexString(System.identityHashCode(this.e)) + ", surfaceSize=" + this.g + "]";
    }
}
