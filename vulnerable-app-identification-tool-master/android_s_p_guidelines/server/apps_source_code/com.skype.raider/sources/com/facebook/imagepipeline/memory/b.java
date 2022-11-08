package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.f.c;
import com.facebook.common.internal.h;
import com.facebook.imageutils.a;
import javax.annotation.concurrent.GuardedBy;

public final class b {
    @GuardedBy("this")
    private int a;
    @GuardedBy("this")
    private long b;
    private final int c;
    private final int d;
    private final c<Bitmap> e;

    public b(int maxCount, int maxSize) {
        boolean z = true;
        h.a(maxCount > 0);
        if (maxSize <= 0) {
            z = false;
        }
        h.a(z);
        this.c = maxCount;
        this.d = maxSize;
        this.e = new c<Bitmap>(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void a(Object obj) {
                Bitmap bitmap = (Bitmap) obj;
                try {
                    this.a.b(bitmap);
                } finally {
                    bitmap.recycle();
                }
            }
        };
    }

    public final synchronized boolean a(Bitmap bitmap) {
        boolean z;
        int bitmapSize = a.a(bitmap);
        if (this.a >= this.c || this.b + ((long) bitmapSize) > ((long) this.d)) {
            z = false;
        } else {
            this.a++;
            this.b += (long) bitmapSize;
            z = true;
        }
        return z;
    }

    public final synchronized void b(Bitmap bitmap) {
        boolean z = true;
        synchronized (this) {
            int bitmapSize = a.a(bitmap);
            h.a(this.a > 0, (Object) "No bitmaps registered.");
            if (((long) bitmapSize) > this.b) {
                z = false;
            }
            h.a(z, "Bitmap size bigger than the total registered size: %d, %d", Integer.valueOf(bitmapSize), Long.valueOf(this.b));
            this.b -= (long) bitmapSize;
            this.a--;
        }
    }

    public final synchronized int a() {
        return this.a;
    }

    public final synchronized long b() {
        return this.b;
    }

    public final synchronized int c() {
        return this.c;
    }

    public final synchronized int d() {
        return this.d;
    }

    public final c<Bitmap> e() {
        return this.e;
    }
}
