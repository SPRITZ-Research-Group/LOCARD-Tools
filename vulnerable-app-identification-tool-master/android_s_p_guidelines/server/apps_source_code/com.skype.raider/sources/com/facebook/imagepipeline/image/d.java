package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.f.a;
import com.facebook.common.f.c;
import com.facebook.common.internal.h;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class d extends b {
    @GuardedBy("this")
    private a<Bitmap> a;
    private volatile Bitmap b;
    private final g c;
    private final int d;
    private final int e;

    public d(Bitmap bitmap, c<Bitmap> resourceReleaser, g qualityInfo) {
        this(bitmap, (c) resourceReleaser, qualityInfo, (byte) 0);
    }

    private d(Bitmap bitmap, c<Bitmap> resourceReleaser, g qualityInfo, byte b) {
        this.b = (Bitmap) h.a((Object) bitmap);
        this.a = a.a(this.b, (c) h.a((Object) resourceReleaser));
        this.c = qualityInfo;
        this.d = 0;
        this.e = 0;
    }

    public d(a<Bitmap> bitmapReference, g qualityInfo) {
        this((a) bitmapReference, qualityInfo, 0, 0);
    }

    public d(a<Bitmap> bitmapReference, g qualityInfo, int rotationAngle, int exifOrientation) {
        this.a = (a) h.a(bitmapReference.c());
        this.b = (Bitmap) this.a.a();
        this.c = qualityInfo;
        this.d = rotationAngle;
        this.e = exifOrientation;
    }

    public final void close() {
        a<Bitmap> reference = k();
        if (reference != null) {
            reference.close();
        }
    }

    private synchronized a<Bitmap> k() {
        a<Bitmap> reference;
        reference = this.a;
        this.a = null;
        this.b = null;
        return reference;
    }

    @Nullable
    public final synchronized a<Bitmap> h() {
        return a.b(this.a);
    }

    public final synchronized boolean c() {
        return this.a == null;
    }

    public final Bitmap f() {
        return this.b;
    }

    public final int d() {
        return com.facebook.imageutils.a.a(this.b);
    }

    public final int a() {
        if (this.d % 180 != 0 || this.e == 5 || this.e == 7) {
            return b(this.b);
        }
        return a(this.b);
    }

    public final int b() {
        if (this.d % 180 != 0 || this.e == 5 || this.e == 7) {
            return a(this.b);
        }
        return b(this.b);
    }

    private static int a(@Nullable Bitmap bitmap) {
        return bitmap == null ? 0 : bitmap.getWidth();
    }

    private static int b(@Nullable Bitmap bitmap) {
        return bitmap == null ? 0 : bitmap.getHeight();
    }

    public final int i() {
        return this.d;
    }

    public final int j() {
        return this.e;
    }

    public final g g() {
        return this.c;
    }
}
