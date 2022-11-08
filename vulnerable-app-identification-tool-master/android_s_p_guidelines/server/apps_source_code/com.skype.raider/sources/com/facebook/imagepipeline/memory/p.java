package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.e.b;

public final class p implements d {
    protected final aa<Bitmap> a = new e();
    private final int b;
    private int c;
    private final ae d;
    private int e;

    public final /* synthetic */ Object a(int i) {
        return c(i);
    }

    public p(int maxPoolSize, int maxBitmapSize, ae poolStatsTracker) {
        this.b = maxPoolSize;
        this.c = maxBitmapSize;
        this.d = poolStatsTracker;
    }

    public final void a(b trimType) {
        b((int) (((double) this.b) * (1.0d - trimType.a())));
    }

    private synchronized void b(int maxSize) {
        while (this.e > maxSize) {
            Bitmap removed = (Bitmap) this.a.a();
            if (removed == null) {
                break;
            }
            this.e -= this.a.b(removed);
        }
    }

    private synchronized Bitmap c(int size) {
        Bitmap cached;
        if (this.e > this.b) {
            b(this.b);
        }
        cached = (Bitmap) this.a.a(size);
        if (cached != null) {
            this.e -= this.a.b(cached);
        } else {
            cached = Bitmap.createBitmap(1, size, Config.ALPHA_8);
        }
        return cached;
    }

    private synchronized void a(Bitmap value) {
        int size = this.a.b(value);
        if (size <= this.c) {
            this.a.a((Object) value);
            this.e += size;
        }
    }
}
