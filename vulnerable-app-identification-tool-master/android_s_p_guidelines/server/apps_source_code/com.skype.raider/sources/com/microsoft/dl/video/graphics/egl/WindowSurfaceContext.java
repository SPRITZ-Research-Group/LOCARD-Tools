package com.microsoft.dl.video.graphics.egl;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.graphics.egl.EGLUtils.ConfigAttributesBuilder;
import com.microsoft.dl.video.graphics.egl.EGLUtils.RenderableTypeAttribute;
import com.microsoft.dl.video.graphics.egl.EGLUtils.SurfaceAttribute;
import com.microsoft.dl.video.graphics.egl.EGLUtils.SurfaceTypeAttribute;
import com.microsoft.dl.video.graphics.egl.EGLUtils.WindowSurfaceAttributeBuilder;
import com.microsoft.dl.video.utils.Resolution;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class WindowSurfaceContext extends AbstractTargetSurfaceContext {
    private static final int[] h = new ConfigAttributesBuilder().renderableType(RenderableTypeAttribute.OPENGL_ES2).redSize(8).greenSize(8).blueSize(8).alphaSize(8).depthSize(0).stencilSize(0).surfaceType(SurfaceTypeAttribute.WINDOW).build();
    private final int[] i = new int[]{0};
    private final int[] j = new int[]{0};

    public /* bridge */ /* synthetic */ void close() throws EGLException {
        super.close();
    }

    public /* bridge */ /* synthetic */ boolean isCurrent() {
        return super.isCurrent();
    }

    public /* bridge */ /* synthetic */ void makeCurrent(boolean z) throws EGLException {
        super.makeCurrent(z);
    }

    public /* bridge */ /* synthetic */ void swapBuffers() throws GraphicsException {
        super.swapBuffers();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public WindowSurfaceContext() throws EGLException {
        super(h);
    }

    public void attachSurface(Object window) throws EGLException {
        if (window == null) {
            throw new EGLException("No window", ErrorCode.ANDROID_EGL_NO_WINDOW);
        }
        releaseSurface();
        int[] surfaceAttributes = new WindowSurfaceAttributeBuilder().build();
        EGLDisplay eGLDisplay = this.c;
        EGLConfig eGLConfig = this.f;
        String str = this.b;
        EGLSurface eglCreateWindowSurface = a.eglCreateWindowSurface(eGLDisplay, eGLConfig, window, surfaceAttributes);
        if (eglCreateWindowSurface == null || eglCreateWindowSurface == EGL10.EGL_NO_SURFACE) {
            throw new EGLException("EGL.eglCreateWindowSurface() has failed in " + str, a.eglGetError(), ErrorCode.ANDROID_EGL_CREATE_WINDOW_SURFACE_FAILED);
        }
        EGLException.checkAfter("EGL.eglCreateWindowSurface()", ErrorCode.ANDROID_EGL_CREATE_WINDOW_SURFACE_FAILED);
        this.e = eglCreateWindowSurface;
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, this.b + " window surface created: [0x" + Integer.toHexString(System.identityHashCode(this.e)) + "] " + a());
        }
    }

    public void detachSurface() throws EGLException {
        super.releaseSurface();
    }

    public Resolution getSurfaceSize() throws EGLException {
        if (this.e == EGL10.EGL_NO_SURFACE) {
            return null;
        }
        if (a.eglQuerySurface(this.c, this.e, SurfaceAttribute.WIDTH.getCode(), this.i) && a.eglQuerySurface(this.c, this.e, SurfaceAttribute.HEIGHT.getCode(), this.j)) {
            EGLException.checkAfter("EGL.eglQuerySurface()", ErrorCode.ANDROID_EGL_QUERY_SURFACE_FAILED);
            if (!(this.g != null && this.g.getWidth() == this.i[0] && this.g.getHeight() == this.j[0])) {
                this.g = new Resolution(this.i[0], this.j[0]);
            }
            return super.getSurfaceSize();
        }
        throw new EGLException("EGL.eglQuerySurface() has failed in " + this.b, a.eglGetError(), ErrorCode.ANDROID_EGL_QUERY_SURFACE_FAILED);
    }
}
