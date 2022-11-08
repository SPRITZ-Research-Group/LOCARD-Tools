package com.facebook.fresco.animation.bitmap.a;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.a.c.c;
import com.facebook.imagepipeline.image.d;
import com.facebook.imagepipeline.image.f;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class a implements com.facebook.fresco.animation.bitmap.a {
    private static final Class<?> a = a.class;
    private final c b;
    private final boolean c;
    @GuardedBy("this")
    private final SparseArray<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> d = new SparseArray();
    @GuardedBy("this")
    @Nullable
    private com.facebook.common.f.a<com.facebook.imagepipeline.image.c> e;

    public a(c animatedFrameCache, boolean enableBitmapReusing) {
        this.b = animatedFrameCache;
        this.c = enableBitmapReusing;
    }

    @Nullable
    public final synchronized com.facebook.common.f.a<Bitmap> a(int frameNumber) {
        return a(this.b.a(frameNumber));
    }

    @Nullable
    public final synchronized com.facebook.common.f.a<Bitmap> a() {
        return a(com.facebook.common.f.a.b(this.e));
    }

    @Nullable
    public final synchronized com.facebook.common.f.a<Bitmap> b() {
        com.facebook.common.f.a<Bitmap> a;
        if (this.c) {
            a = a(this.b.a());
        } else {
            a = null;
        }
        return a;
    }

    public final synchronized boolean b(int frameNumber) {
        return this.b.b(frameNumber);
    }

    public final synchronized void c() {
        com.facebook.common.f.a.c(this.e);
        this.e = null;
        for (int i = 0; i < this.d.size(); i++) {
            com.facebook.common.f.a.c((com.facebook.common.f.a) this.d.valueAt(i));
        }
        this.d.clear();
    }

    public final synchronized void a(int frameNumber, com.facebook.common.f.a<Bitmap> bitmapReference) {
        h.a((Object) bitmapReference);
        c(frameNumber);
        try {
            com.facebook.common.f.a closableReference = b((com.facebook.common.f.a) bitmapReference);
            if (closableReference != null) {
                com.facebook.common.f.a.c(this.e);
                this.e = this.b.a(frameNumber, closableReference);
            }
            com.facebook.common.f.a.c(closableReference);
        } catch (Throwable th) {
            com.facebook.common.f.a.c(null);
        }
    }

    public final synchronized void b(int frameNumber, com.facebook.common.f.a<Bitmap> bitmapReference) {
        h.a((Object) bitmapReference);
        com.facebook.common.f.a<com.facebook.imagepipeline.image.c> closableReference = null;
        try {
            closableReference = b((com.facebook.common.f.a) bitmapReference);
            if (closableReference != null) {
                com.facebook.common.f.a newReference = this.b.a(frameNumber, (com.facebook.common.f.a) closableReference);
                if (com.facebook.common.f.a.a(newReference)) {
                    com.facebook.common.f.a.c((com.facebook.common.f.a) this.d.get(frameNumber));
                    this.d.put(frameNumber, newReference);
                    FLog.v(a, "cachePreparedFrame(%d) cached. Pending frames: %s", Integer.valueOf(frameNumber), this.d);
                }
                com.facebook.common.f.a.c(closableReference);
            }
        } finally {
            com.facebook.common.f.a.c(closableReference);
        }
    }

    private synchronized void c(int frameNumber) {
        com.facebook.common.f.a<com.facebook.imagepipeline.image.c> existingPendingReference = (com.facebook.common.f.a) this.d.get(frameNumber);
        if (existingPendingReference != null) {
            this.d.delete(frameNumber);
            com.facebook.common.f.a.c(existingPendingReference);
            FLog.v(a, "removePreparedReference(%d) removed. Pending frames: %s", Integer.valueOf(frameNumber), this.d);
        }
    }

    @VisibleForTesting
    @Nullable
    private static com.facebook.common.f.a<Bitmap> a(@Nullable com.facebook.common.f.a<com.facebook.imagepipeline.image.c> closeableImage) {
        try {
            if (com.facebook.common.f.a.a((com.facebook.common.f.a) closeableImage) && (closeableImage.a() instanceof d)) {
                d closeableStaticBitmap = (d) closeableImage.a();
                if (closeableStaticBitmap != null) {
                    com.facebook.common.f.a<Bitmap> h = closeableStaticBitmap.h();
                    return h;
                }
            }
            com.facebook.common.f.a.c(closeableImage);
            return null;
        } finally {
            com.facebook.common.f.a.c(closeableImage);
        }
    }

    @Nullable
    private static com.facebook.common.f.a<com.facebook.imagepipeline.image.c> b(com.facebook.common.f.a<Bitmap> bitmapReference) {
        return com.facebook.common.f.a.a(new d(bitmapReference, f.a));
    }
}
