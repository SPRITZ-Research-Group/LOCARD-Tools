package com.skypecam.obscura.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.opengl.EGL14;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.Pair;
import com.skypecam.obscura.e.g;
import com.skypecam.obscura.e.j;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class c {
    protected b a;
    private EGLSurface b = EGL14.EGL_NO_SURFACE;
    private int c = -1;
    private int d = -1;

    protected c(b eglCore) {
        this.a = eglCore;
    }

    public final void a(Object surface) {
        if (this.b != EGL14.EGL_NO_SURFACE) {
            throw new IllegalStateException("surface already created");
        }
        this.b = this.a.a(surface);
    }

    public final void a() {
        g.a().b("EglSurfaceBase", "releaseEglSurface");
        this.a.a(this.b);
        this.b = EGL14.EGL_NO_SURFACE;
        this.d = -1;
        this.c = -1;
    }

    public final void b() {
        this.a.b(this.b);
    }

    public final boolean c() {
        boolean result = this.a.c(this.b);
        if (!result) {
            g.a().a("EglSurfaceBase", "WARNING: swapBuffers() failed");
        }
        return result;
    }

    public final Pair<ByteBuffer, j> d() throws IOException {
        if (this.a.d(this.b)) {
            int a;
            if (this.c < 0) {
                a = this.a.a(this.b, 12375);
            } else {
                a = this.c;
            }
            int width = a + 0;
            if (this.d < 0) {
                a = this.a.a(this.b, 12374);
            } else {
                a = this.d;
            }
            int height = a + 0;
            g.a().b("EglSurfaceBase", "saveFrame: ByteBuffer");
            ByteBuffer buf = ByteBuffer.allocateDirect((width * height) * 4);
            buf.order(ByteOrder.LITTLE_ENDIAN);
            g.a().b("EglSurfaceBase", "saveFrame: glFinish");
            GLES20.glFinish();
            g.a().b("EglSurfaceBase", "saveFrame: glFinish done");
            GLES20.glReadPixels(0, 0, width, height, 6408, 5121, buf);
            e.a("glReadPixels");
            g.a().b("EglSurfaceBase", "saveFrame: glReadPixels done");
            return new Pair(buf, new j(width, height));
        }
        throw new RuntimeException("Expected EGL context/surface is not current");
    }

    public static File a(int width, int height, int rotation, ByteBuffer buf) throws IOException {
        Throwable th;
        File file = File.createTempFile("scv_" + System.currentTimeMillis(), ".jpg");
        String filename = file.toString();
        byte[] row = new byte[(width * 4)];
        g.a().b("EglSurfaceBase", "starting flip");
        for (int i = 0; i < height / 2; i++) {
            buf.get(row);
            System.arraycopy(buf.array(), buf.limit() - buf.position(), buf.array(), buf.position() - row.length, row.length);
            System.arraycopy(row, 0, buf.array(), buf.limit() - buf.position(), row.length);
        }
        g.a().b("EglSurfaceBase", "done flip");
        buf.rewind();
        BufferedOutputStream bos = null;
        try {
            BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream(filename));
            try {
                Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                bmp.copyPixelsFromBuffer(buf);
                g.a().b("EglSurfaceBase", "jpeg compress");
                if (rotation != -1) {
                    Matrix m = null;
                    switch (rotation) {
                        case 1:
                            m = new Matrix();
                            m.postRotate(-90.0f);
                            break;
                        case 2:
                            m = new Matrix();
                            m.postRotate(180.0f);
                            break;
                        case 3:
                            m = new Matrix();
                            m.postRotate(90.0f);
                            break;
                    }
                    if (m != null) {
                        Bitmap rotatedBmp = Bitmap.createBitmap(bmp, 0, 0, width, height, m, true);
                        if (!bmp.equals(rotatedBmp)) {
                            bmp.recycle();
                        }
                        bmp = rotatedBmp;
                    }
                }
                bmp.compress(CompressFormat.JPEG, 90, bos2);
                g.a().b("EglSurfaceBase", "jpeg compress done");
                bmp.recycle();
                bos2.close();
                g.a().a("EglSurfaceBase", "Saved " + width + "x" + height + " frame as '" + filename + "'");
                return file;
            } catch (Throwable th2) {
                th = th2;
                bos = bos2;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bos != null) {
                bos.close();
            }
            throw th;
        }
    }
}
