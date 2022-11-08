package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

public final class b {
    private b a;
    private ByteBuffer b;
    private Bitmap c;

    public static class a {
        private b a = new b();

        public final a a(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.a.c = bitmap;
            b a = this.a.a();
            a.a = width;
            a.b = height;
            return this;
        }

        public final b a() {
            if (this.a.b != null || this.a.c != null) {
                return this.a;
            }
            throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
        }
    }

    public static class b {
        private int a;
        private int b;
        private int c;
        private long d;
        private int e;
        private int f = -1;

        public final int a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final long d() {
            return this.d;
        }

        public final int e() {
            return this.e;
        }
    }

    private b() {
        this.a = new b();
        this.b = null;
        this.c = null;
    }

    /* synthetic */ b(byte b) {
        this();
    }

    public final b a() {
        return this.a;
    }

    public final ByteBuffer b() {
        int i = 0;
        if (this.c == null) {
            return this.b;
        }
        int width = this.c.getWidth();
        int height = this.c.getHeight();
        int[] iArr = new int[(width * height)];
        this.c.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[(width * height)];
        while (i < iArr.length) {
            bArr[i] = (byte) ((int) (((((float) Color.red(iArr[i])) * 0.299f) + (((float) Color.green(iArr[i])) * 0.587f)) + (((float) Color.blue(iArr[i])) * 0.114f)));
            i++;
        }
        return ByteBuffer.wrap(bArr);
    }

    public final Bitmap c() {
        return this.c;
    }
}
