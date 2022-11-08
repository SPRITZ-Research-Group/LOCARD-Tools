package com.microsoft.dl.video.capture.impl;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CameraCallback;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.graphics.egl.EGLException;
import com.microsoft.dl.video.graphics.egl.PixelBufferSurfaceContext;
import com.microsoft.dl.video.graphics.egl.PixelBufferSurfaceContext.BufferFormat;
import com.microsoft.dl.video.graphics.egl.TargetSurfaceContext;
import com.microsoft.dl.video.utils.Resolution;

public class OffscreenPreviewSurface extends AbstractPreviewSurface {
    private Resolution a;

    private static PixelBufferSurfaceContext d() throws EGLException {
        try {
            return new PixelBufferSurfaceContext();
        } catch (EGLException e) {
            PixelBufferSurfaceContext pixelBufferSurfaceContext = null;
            pixelBufferSurfaceContext.close();
            throw e;
        }
    }

    public OffscreenPreviewSurface(CameraCallback callback) throws EGLException {
        super(callback, d());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void allocSurfaceTexture(Resolution surfaceSize) throws GraphicsException {
        synchronized (((PixelBufferSurfaceContext) super.a())) {
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, getClass().getSimpleName() + " requested for " + surfaceSize);
            }
            if (!surfaceSize.equals(this.a)) {
                releaseSurfaceTexture();
                try {
                    ((PixelBufferSurfaceContext) super.a()).allocSurface(surfaceSize, BufferFormat.NoBuffer);
                    super.b();
                    this.a = surfaceSize;
                    if (Log.isLoggable(PackageInfo.TAG, 4)) {
                        Log.i(PackageInfo.TAG, getClass().getSimpleName() + " allocated " + surfaceSize + " size " + getSurfaceTexture());
                    }
                } catch (GraphicsException e) {
                    ((PixelBufferSurfaceContext) super.a()).releaseSurface();
                    throw e;
                }
            } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, getClass().getSimpleName() + " reusing existing " + getSurfaceTexture());
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseSurfaceTexture() throws GraphicsException {
        synchronized (((PixelBufferSurfaceContext) super.a())) {
            if (getSurfaceTexture() == null) {
                return;
            }
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, getClass().getSimpleName() + " releasing " + getSurfaceTexture());
            }
            super.c();
            ((PixelBufferSurfaceContext) super.a()).releaseSurface();
            this.a = null;
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, getClass().getSimpleName() + " surface texture released");
            }
        }
    }

    protected final /* bridge */ /* synthetic */ TargetSurfaceContext a() {
        return (PixelBufferSurfaceContext) super.a();
    }
}
