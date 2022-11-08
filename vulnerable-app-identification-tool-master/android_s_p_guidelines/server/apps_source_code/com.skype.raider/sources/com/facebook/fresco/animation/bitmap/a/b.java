package com.facebook.fresco.animation.bitmap.a;

import android.graphics.Bitmap;
import com.facebook.fresco.animation.bitmap.a;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class b implements a {
    private int a = -1;
    @GuardedBy("this")
    @Nullable
    private com.facebook.common.f.a<Bitmap> b;

    @Nullable
    public final synchronized com.facebook.common.f.a<Bitmap> a(int frameNumber) {
        com.facebook.common.f.a<Bitmap> b;
        if (this.a == frameNumber) {
            b = com.facebook.common.f.a.b(this.b);
        } else {
            b = null;
        }
        return b;
    }

    @Nullable
    public final synchronized com.facebook.common.f.a<Bitmap> a() {
        return com.facebook.common.f.a.b(this.b);
    }

    public final synchronized com.facebook.common.f.a<Bitmap> b() {
        com.facebook.common.f.a<Bitmap> b;
        try {
            b = com.facebook.common.f.a.b(this.b);
            d();
        } catch (Throwable th) {
            d();
        }
        return b;
    }

    public final synchronized boolean b(int frameNumber) {
        boolean z;
        z = frameNumber == this.a && com.facebook.common.f.a.a(this.b);
        return z;
    }

    public final synchronized void c() {
        d();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(int frameNumber, com.facebook.common.f.a<Bitmap> bitmapReference) {
        if (bitmapReference != null) {
            if (this.b != null) {
            }
        }
        com.facebook.common.f.a.c(this.b);
        this.b = com.facebook.common.f.a.b(bitmapReference);
        this.a = frameNumber;
    }

    public final void b(int frameNumber, com.facebook.common.f.a<Bitmap> aVar) {
    }

    private synchronized void d() {
        com.facebook.common.f.a.c(this.b);
        this.b = null;
        this.a = -1;
    }
}
