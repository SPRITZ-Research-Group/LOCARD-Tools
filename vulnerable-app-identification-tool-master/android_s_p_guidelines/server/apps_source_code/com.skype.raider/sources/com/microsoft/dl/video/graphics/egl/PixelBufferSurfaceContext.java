package com.microsoft.dl.video.graphics.egl;

import android.opengl.GLES20;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.graphics.egl.EGLUtils.ConfigAttributesBuilder;
import com.microsoft.dl.video.graphics.egl.EGLUtils.PBufferSurfaceAttributeBuilder;
import com.microsoft.dl.video.graphics.egl.EGLUtils.RenderableTypeAttribute;
import com.microsoft.dl.video.graphics.egl.EGLUtils.SurfaceTypeAttribute;
import com.microsoft.dl.video.graphics.gles.GLException;
import com.microsoft.dl.video.utils.Resolution;
import java.nio.ByteBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class PixelBufferSurfaceContext extends AbstractTargetSurfaceContext {
    private static final int[] h = new ConfigAttributesBuilder().renderableType(RenderableTypeAttribute.OPENGL_ES2).redSize(8).greenSize(8).blueSize(8).alphaSize(8).depthSize(0).stencilSize(0).surfaceType(SurfaceTypeAttribute.PBUFFER).build();
    private int i;
    private ByteBuffer j;

    public enum BufferFormat {
        NoBuffer,
        RGB,
        RGBA
    }

    public /* bridge */ /* synthetic */ void close() throws EGLException {
        super.close();
    }

    public /* bridge */ /* synthetic */ Resolution getSurfaceSize() throws EGLException {
        return super.getSurfaceSize();
    }

    public /* bridge */ /* synthetic */ boolean isCurrent() {
        return super.isCurrent();
    }

    public /* bridge */ /* synthetic */ void makeCurrent(boolean z) throws EGLException {
        super.makeCurrent(z);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public PixelBufferSurfaceContext() throws EGLException {
        super(h);
    }

    public ByteBuffer allocSurface(Resolution size, BufferFormat bufferFormat) throws EGLException {
        if (size == null) {
            throw new EGLException("size is null", ErrorCode.ANDROID_EGL_NO_SURFACE_SIZE);
        }
        releaseSurface();
        int[] surfaceAttributes = new PBufferSurfaceAttributeBuilder().width(size.getWidth()).height(size.getHeight()).build();
        EGLDisplay eGLDisplay = this.c;
        EGLConfig eGLConfig = this.f;
        String str = this.b;
        EGLSurface eglCreatePbufferSurface = a.eglCreatePbufferSurface(eGLDisplay, eGLConfig, surfaceAttributes);
        if (eglCreatePbufferSurface == null || eglCreatePbufferSurface == EGL10.EGL_NO_SURFACE) {
            throw new EGLException("EGL.eglCreatePbufferSurface() has failed " + str, a.eglGetError(), ErrorCode.ANDROID_EGL_CREATE_PBUFFER_SURFACE_FAILED);
        }
        EGLException.checkAfter("EGL.eglCreatePbufferSurface()", ErrorCode.ANDROID_EGL_CREATE_PBUFFER_SURFACE_FAILED);
        this.e = eglCreatePbufferSurface;
        this.g = size;
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, this.b + " pixel buffer surface created: [0x" + Integer.toHexString(System.identityHashCode(this.e)) + "] " + a());
        }
        ByteBuffer allocateDirect;
        switch (bufferFormat) {
            case NoBuffer:
                this.i = 0;
                this.j = null;
                return null;
            case RGB:
                this.i = 6407;
                allocateDirect = ByteBuffer.allocateDirect(size.getNumPixels() * 3);
                this.j = allocateDirect;
                return allocateDirect;
            case RGBA:
                this.i = 6408;
                allocateDirect = ByteBuffer.allocateDirect(size.getNumPixels() * 4);
                this.j = allocateDirect;
                return allocateDirect;
            default:
                throw new AssertionError("BufferFormat '" + bufferFormat + "' is not supported");
        }
    }

    public void releaseSurface() throws EGLException {
        this.j = null;
        this.i = 0;
        super.releaseSurface();
    }

    public void swapBuffers() throws GraphicsException {
        if (this.j != null) {
            GLES20.glReadPixels(0, 0, getSurfaceSize().getWidth(), getSurfaceSize().getHeight(), this.i, 5121, this.j);
        }
        GLException.checkAfter("GLES20.glReadPixels()");
        super.swapBuffers();
    }
}
