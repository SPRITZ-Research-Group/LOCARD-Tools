package com.microsoft.dl.video.capture.impl;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CameraCallback;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.graphics.egl.EGLException;
import com.microsoft.dl.video.graphics.egl.TargetSurfaceContext;
import com.microsoft.dl.video.graphics.egl.WindowSurfaceContext;

public class PassthroughPreviewSurface extends AbstractPreviewSurface {
    private Object a;

    private static WindowSurfaceContext d() throws EGLException {
        try {
            return new WindowSurfaceContext();
        } catch (EGLException e) {
            WindowSurfaceContext windowSurfaceContext = null;
            windowSurfaceContext.close();
            throw e;
        }
    }

    public PassthroughPreviewSurface(CameraCallback callback) throws EGLException {
        super(callback, d());
    }

    public Object getExternalPreviewDisplay() {
        return this.a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void allocSurfaceTexture(Object externalPreviewDisplay) throws GraphicsException {
        synchronized (((WindowSurfaceContext) super.a())) {
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, getClass().getSimpleName() + " requested for " + externalPreviewDisplay);
            }
            if (externalPreviewDisplay != this.a) {
                releaseSurfaceTexture();
                if (externalPreviewDisplay == null) {
                    return;
                }
                try {
                    ((WindowSurfaceContext) super.a()).attachSurface(externalPreviewDisplay);
                    super.b();
                    this.a = externalPreviewDisplay;
                    if (Log.isLoggable(PackageInfo.TAG, 4)) {
                        Log.i(PackageInfo.TAG, getClass().getSimpleName() + " allocated " + getSurfaceTexture());
                    }
                } catch (GraphicsException e) {
                    ((WindowSurfaceContext) super.a()).detachSurface();
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
        synchronized (((WindowSurfaceContext) super.a())) {
            if (getSurfaceTexture() == null) {
                return;
            }
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, getClass().getSimpleName() + " releasing " + getSurfaceTexture());
            }
            super.c();
            ((WindowSurfaceContext) super.a()).detachSurface();
            this.a = null;
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, getClass().getSimpleName() + " surface texture released");
            }
        }
    }

    protected final /* bridge */ /* synthetic */ TargetSurfaceContext a() {
        return (WindowSurfaceContext) super.a();
    }
}
