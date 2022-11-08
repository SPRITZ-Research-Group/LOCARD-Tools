package com.facebook.imagepipeline.image;

import android.graphics.ColorSpace;
import android.util.Pair;
import com.facebook.common.e.h;
import com.facebook.common.f.a;
import com.facebook.common.internal.j;
import com.facebook.imageformat.b;
import com.facebook.imageformat.c;
import com.facebook.imageformat.d;
import com.facebook.imageutils.HeifExifUtil;
import com.facebook.imageutils.f;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class e implements Closeable {
    @Nullable
    private final a<h> a;
    @Nullable
    private final j<FileInputStream> b;
    private c c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    @Nullable
    private com.facebook.imagepipeline.common.a j;
    @Nullable
    private ColorSpace k;

    public e(a<h> pooledByteBufferRef) {
        this.c = c.a;
        this.d = -1;
        this.e = 0;
        this.f = -1;
        this.g = -1;
        this.h = 1;
        this.i = -1;
        com.facebook.common.internal.h.a(a.a((a) pooledByteBufferRef));
        this.a = pooledByteBufferRef.b();
        this.b = null;
    }

    private e(j<FileInputStream> inputStreamSupplier) {
        this.c = c.a;
        this.d = -1;
        this.e = 0;
        this.f = -1;
        this.g = -1;
        this.h = 1;
        this.i = -1;
        com.facebook.common.internal.h.a((Object) inputStreamSupplier);
        this.a = null;
        this.b = inputStreamSupplier;
    }

    private e(j<FileInputStream> inputStreamSupplier, int streamSize) {
        this((j) inputStreamSupplier);
        this.i = streamSize;
    }

    public static e a(e encodedImage) {
        return encodedImage != null ? encodedImage.o() : null;
    }

    private e o() {
        e encodedImage;
        if (this.b != null) {
            encodedImage = new e(this.b, this.i);
        } else {
            a pooledByteBufferRef = a.b(this.a);
            if (pooledByteBufferRef == null) {
                encodedImage = null;
            } else {
                try {
                    encodedImage = new e(pooledByteBufferRef);
                } catch (Throwable th) {
                    a.c(pooledByteBufferRef);
                }
            }
            a.c(pooledByteBufferRef);
        }
        if (encodedImage != null) {
            encodedImage.b(this);
        }
        return encodedImage;
    }

    public final void close() {
        a.c(this.a);
    }

    public final synchronized boolean a() {
        boolean z;
        z = a.a(this.a) || this.b != null;
        return z;
    }

    public final a<h> b() {
        return a.b(this.a);
    }

    public final InputStream c() {
        if (this.b != null) {
            return (InputStream) this.b.a();
        }
        a<h> pooledByteBufferRef = a.b(this.a);
        if (pooledByteBufferRef == null) {
            return null;
        }
        try {
            InputStream jVar = new com.facebook.common.e.j((h) pooledByteBufferRef.a());
            return jVar;
        } finally {
            a.c(pooledByteBufferRef);
        }
    }

    public final void a(c imageFormat) {
        this.c = imageFormat;
    }

    public final void a(int height) {
        this.g = height;
    }

    public final void b(int width) {
        this.f = width;
    }

    public final void c(int rotationAngle) {
        this.d = rotationAngle;
    }

    public final void d() {
        this.e = 0;
    }

    public final void d(int sampleSize) {
        this.h = sampleSize;
    }

    public final void a(@Nullable com.facebook.imagepipeline.common.a bytesRange) {
        this.j = bytesRange;
    }

    public final c e() {
        p();
        return this.c;
    }

    public final int f() {
        p();
        return this.d;
    }

    public final int g() {
        p();
        return this.e;
    }

    public final int h() {
        p();
        return this.f;
    }

    public final int i() {
        p();
        return this.g;
    }

    public final int j() {
        return this.h;
    }

    @Nullable
    public final com.facebook.imagepipeline.common.a k() {
        return this.j;
    }

    public final boolean e(int length) {
        if (this.c != b.a || this.b != null) {
            return true;
        }
        com.facebook.common.internal.h.a(this.a);
        h buf = (h) this.a.a();
        if (buf.a(length - 2) == (byte) -1 && buf.a(length - 1) == (byte) -39) {
            return true;
        }
        return false;
    }

    public final int l() {
        if (this.a == null || this.a.a() == null) {
            return this.i;
        }
        return ((h) this.a.a()).a();
    }

    public final String m() {
        a<h> imageBuffer = a.b(this.a);
        if (imageBuffer == null) {
            return "";
        }
        int resultSampleSize = Math.min(l(), 10);
        byte[] bytesBuffer = new byte[resultSampleSize];
        try {
            h pooledByteBuffer = (h) imageBuffer.a();
            if (pooledByteBuffer == null) {
                String str = "";
                return str;
            }
            pooledByteBuffer.a(0, bytesBuffer, 0, resultSampleSize);
            imageBuffer.close();
            StringBuilder stringBuilder = new StringBuilder(resultSampleSize * 2);
            for (int i = 0; i < resultSampleSize; i++) {
                stringBuilder.append(String.format("%02X", new Object[]{Byte.valueOf(bytesBuffer[i])}));
            }
            return stringBuilder.toString();
        } finally {
            imageBuffer.close();
        }
    }

    private void p() {
        if (this.f < 0 || this.g < 0) {
            n();
        }
    }

    public final void n() {
        Pair<Integer, Integer> dimensions;
        c imageFormat = d.a(c());
        this.c = imageFormat;
        if (b.a(imageFormat)) {
            dimensions = f.a(c());
            if (dimensions != null) {
                this.f = ((Integer) dimensions.first).intValue();
                this.g = ((Integer) dimensions.second).intValue();
            }
        } else {
            dimensions = q().a();
        }
        if (imageFormat == b.a && this.d == -1) {
            if (dimensions != null) {
                this.e = com.facebook.imageutils.c.a(c());
                this.d = com.facebook.imageutils.c.a(this.e);
            }
        } else if (imageFormat == b.k && this.d == -1) {
            this.e = HeifExifUtil.a(c());
            this.d = com.facebook.imageutils.c.a(this.e);
        } else {
            this.d = 0;
        }
    }

    private com.facebook.imageutils.b q() {
        InputStream inputStream = null;
        try {
            inputStream = c();
            com.facebook.imageutils.b metaData = com.facebook.imageutils.a.b(inputStream);
            this.k = metaData.b();
            Pair<Integer, Integer> dimensions = metaData.a();
            if (dimensions != null) {
                this.f = ((Integer) dimensions.first).intValue();
                this.g = ((Integer) dimensions.second).intValue();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            return metaData;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    public final void b(e encodedImage) {
        this.c = encodedImage.e();
        this.f = encodedImage.h();
        this.g = encodedImage.i();
        this.d = encodedImage.f();
        this.e = encodedImage.g();
        this.h = encodedImage.h;
        this.i = encodedImage.l();
        this.j = encodedImage.j;
        encodedImage.p();
        this.k = encodedImage.k;
    }

    public static boolean c(e encodedImage) {
        return encodedImage.d >= 0 && encodedImage.f >= 0 && encodedImage.g >= 0;
    }

    public static void d(@Nullable e encodedImage) {
        if (encodedImage != null) {
            encodedImage.close();
        }
    }

    public static boolean e(@Nullable e encodedImage) {
        return encodedImage != null && encodedImage.a();
    }
}
