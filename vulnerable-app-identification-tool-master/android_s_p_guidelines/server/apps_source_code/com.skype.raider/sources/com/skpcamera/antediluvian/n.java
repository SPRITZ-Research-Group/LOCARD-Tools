package com.skpcamera.antediluvian;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.opengl.EGL14;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import com.facebook.common.logging.FLog;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class n {
    protected m a;
    private EGLSurface b = EGL14.EGL_NO_SURFACE;
    private int c = -1;
    private int d = -1;

    protected n(m eglCore) {
        this.a = eglCore;
    }

    public final void a(Object surface) {
        if (this.b != EGL14.EGL_NO_SURFACE) {
            throw new IllegalStateException("surface already created");
        }
        this.b = this.a.a(surface);
    }

    public final int a() {
        if (this.c < 0) {
            return this.a.a(this.b, 12375);
        }
        return this.c;
    }

    public final int b() {
        if (this.d < 0) {
            return this.a.a(this.b, 12374);
        }
        return this.d;
    }

    public final void c() {
        this.a.a(this.b);
        this.b = EGL14.EGL_NO_SURFACE;
        this.d = -1;
        this.c = -1;
    }

    public final void d() {
        this.a.b(this.b);
    }

    public final boolean e() {
        return this.a.c(this.b);
    }

    public final void a(long nsecs) {
        this.a.a(this.b, nsecs);
    }

    public final void a(File file, int cropX, int cropY) throws IOException {
        Throwable th;
        if (this.a.d(this.b)) {
            String filename = file.toString();
            int width = a() - (cropX * 2);
            int height = b() - (cropY * 2);
            ByteBuffer buf = ByteBuffer.allocateDirect((width * height) * 4);
            buf.order(ByteOrder.LITTLE_ENDIAN);
            FLog.i("SkypeCameraEglSurfBase", "glFinish");
            GLES20.glFinish();
            FLog.i("SkypeCameraEglSurfBase", "glFinish done");
            GLES20.glReadPixels(cropX, cropY, width, height, 6408, 5121, buf);
            p.a("glReadPixels");
            byte[] row = new byte[(width * 4)];
            FLog.i("SkypeCameraEglSurfBase", "starting flip");
            for (int i = 0; i < height / 2; i++) {
                buf.get(row);
                System.arraycopy(buf.array(), buf.limit() - buf.position(), buf.array(), buf.position() - row.length, row.length);
                System.arraycopy(row, 0, buf.array(), buf.limit() - buf.position(), row.length);
            }
            FLog.i("SkypeCameraEglSurfBase", "done flip");
            buf.rewind();
            BufferedOutputStream bos = null;
            try {
                BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream(filename));
                try {
                    Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                    bmp.copyPixelsFromBuffer(buf);
                    FLog.i("SkypeCameraEglSurfBase", "jpeg compress");
                    bmp.compress(CompressFormat.JPEG, 90, bos2);
                    FLog.i("SkypeCameraEglSurfBase", "jpeg compress done");
                    bmp.recycle();
                    bos2.close();
                    new StringBuilder("Saved ").append(width).append("x").append(height).append(" frame as '").append(filename).append("'");
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    bos = bos2;
                    if (bos != null) {
                        bos.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bos != null) {
                    bos.close();
                }
                throw th;
            }
        }
        throw new RuntimeException("Expected EGL context/surface is not current");
    }
}
