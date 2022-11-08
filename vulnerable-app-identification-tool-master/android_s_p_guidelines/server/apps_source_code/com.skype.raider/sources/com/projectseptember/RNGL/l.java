package com.projectseptember.RNGL;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.an;
import java.util.concurrent.Executor;

public final class l {
    private int a;
    private Bitmap b = null;
    private Executor c;
    private boolean d = false;
    private int e;
    private int f;

    public l(Executor glExecutor, int maxWidth, int maxHeight, int causeId) {
        FLog.i("RNGLTexture", "init %d (causeId %x)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(causeId));
        this.c = glExecutor;
        this.e = maxWidth;
        this.f = maxHeight;
        an.a(!this.d, "Must not be invalidated (causeId " + causeId + ")");
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.a = iArr[0];
        FLog.i("RNGLTexture", "makeTexture %d (causeId %x)", Integer.valueOf(this.a), Integer.valueOf(causeId));
        GLES20.glBindTexture(3553, this.a);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        if (GLES20.glGetError() != 0) {
            FLog.w("RNGLTexture", "makeTexture GL Error: %s (causeId %x)", GLUtils.getEGLErrorString(GLES20.glGetError()), Integer.valueOf(causeId));
        }
    }

    protected final void finalize() throws Throwable {
        super.finalize();
        an.a(this.d, "finalize: Must be invalidated");
    }

    public final void a(final int causeId) {
        this.d = true;
        FLog.i("RNGLTexture", "invalidate %d (causeId %x)", Integer.valueOf(this.a), Integer.valueOf(causeId));
        this.b = null;
        this.c.execute(new Runnable(this) {
            final /* synthetic */ l b;

            public final void run() {
                FLog.i("RNGLTexture", "invalidate glDeleteTextures %d (causeId %x)", Integer.valueOf(this.b.a), Integer.valueOf(causeId));
                GLES20.glDeleteTextures(1, new int[]{this.b.a}, 0);
            }
        });
    }

    public final int a(int unit, int causeId) {
        an.a(!this.d, "Must not be invalidated (causeId " + causeId + ")");
        FLog.i("RNGLTexture", "bind glActiveTexture unit %d glBindTexture handle %d (causeId %x)", Integer.valueOf(unit), Integer.valueOf(this.a), Integer.valueOf(causeId));
        GLES20.glActiveTexture(33984 + unit);
        GLES20.glBindTexture(3553, this.a);
        return unit;
    }

    public final void b(int causeId) {
        an.a(!this.d, "Must not be invalidated (causeId " + causeId + ")");
        FLog.i("RNGLTexture", "bind glBindTexture handle %d (causeId %x)", Integer.valueOf(this.a), Integer.valueOf(causeId));
        GLES20.glBindTexture(3553, this.a);
    }

    public final void a(Bitmap bitmap, int causeId) {
        boolean z;
        if (this.d) {
            z = false;
        } else {
            z = true;
        }
        an.a(z, "Must not be invalidated (causeId " + causeId + ")");
        if (bitmap != this.b) {
            FLog.i("RNGLTexture", "setPixels with new bitmap (causeId %x)", Integer.valueOf(causeId));
            an.a(!this.d, "Must not be invalidated");
            int[] iArr = new int[1];
            GLES20.glGetIntegerv(3379, iArr, 0);
            int min = Math.min(iArr[0], this.e);
            int min2 = Math.min(iArr[0], this.f);
            if (bitmap.getWidth() > min || bitmap.getHeight() > min2) {
                float min3 = Math.min(((float) Math.min(bitmap.getWidth(), min)) / ((float) bitmap.getWidth()), ((float) Math.min(bitmap.getHeight(), min2)) / ((float) bitmap.getHeight()));
                min = Math.min(Math.round(((float) bitmap.getWidth()) * min3), min);
                min2 = Math.min(Math.round(((float) bitmap.getHeight()) * min3), min2);
                FLog.i("RNGLTexture", "scaling bitmap by " + min3 + " to " + min + " " + min2);
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, min, min2, false);
                if (bitmap != createScaledBitmap) {
                    bitmap.recycle();
                }
                bitmap = createScaledBitmap;
            } else {
                FLog.i("RNGLTexture", "resize: no scaling");
            }
            this.b = bitmap;
            b(causeId);
            GLUtils.texImage2D(3553, 0, this.b, 0);
            if (GLES20.glGetError() != 0) {
                FLog.w("RNGLTexture", "GL Error: %s (causeId %x)", GLUtils.getEGLErrorString(GLES20.glGetError()), Integer.valueOf(causeId));
                return;
            }
            return;
        }
        FLog.i("RNGLTexture", "setPixels bitmap is already loaded (causeId %x)", Integer.valueOf(causeId));
    }

    public final void c(int causeId) {
        an.a(!this.d, "Must not be invalidated (causeId " + causeId + ")");
        a(Bitmap.createBitmap(2, 2, Config.ARGB_8888), causeId);
    }

    public final void a(int width, int height, int causeId) {
        boolean z;
        if (this.d) {
            z = false;
        } else {
            z = true;
        }
        an.a(z, "Must not be invalidated (causeId " + causeId + ")");
        b(causeId);
        GLES20.glTexImage2D(3553, 0, 6408, width, height, 0, 6408, 5121, null);
    }

    public final int a() {
        an.a(!this.d, "Must not be invalidated");
        return this.a;
    }
}
