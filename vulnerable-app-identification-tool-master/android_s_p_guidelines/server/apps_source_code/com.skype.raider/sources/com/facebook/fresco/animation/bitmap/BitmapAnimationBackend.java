package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.a.a;
import com.facebook.fresco.animation.a.c;
import com.facebook.fresco.animation.a.d;
import com.facebook.fresco.animation.bitmap.b.b;
import com.facebook.imagepipeline.c.f;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nullable;

public class BitmapAnimationBackend implements a, c.a {
    private static final Class<?> a = BitmapAnimationBackend.class;
    private final f b;
    private final a c;
    private final d d;
    private final b e;
    @Nullable
    private final com.facebook.fresco.animation.bitmap.b.a f;
    @Nullable
    private final b g;
    private final Paint h;
    @Nullable
    private Rect i;
    private int j;
    private int k;
    private Config l = Config.ARGB_8888;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameType {
    }

    public BitmapAnimationBackend(f platformBitmapFactory, a bitmapFrameCache, d animationInformation, b bitmapFrameRenderer, @Nullable com.facebook.fresco.animation.bitmap.b.a bitmapFramePreparationStrategy, @Nullable b bitmapFramePreparer) {
        this.b = platformBitmapFactory;
        this.c = bitmapFrameCache;
        this.d = animationInformation;
        this.e = bitmapFrameRenderer;
        this.f = bitmapFramePreparationStrategy;
        this.g = bitmapFramePreparer;
        this.h = new Paint(6);
        g();
    }

    public final int d() {
        return this.d.d();
    }

    public final int b(int frameNumber) {
        return this.d.b(frameNumber);
    }

    public final int e() {
        return this.d.e();
    }

    public final boolean a(Drawable parent, Canvas canvas, int frameNumber) {
        boolean drawn = a(canvas, frameNumber, 0);
        if (!(this.f == null || this.g == null)) {
            this.f.a(this.g, this.c, this, frameNumber);
        }
        return drawn;
    }

    private boolean a(Canvas canvas, int frameNumber, int frameType) {
        boolean drawn;
        while (true) {
            com.facebook.common.f.a<Bitmap> bitmapReference = null;
            int nextFrameType = -1;
            switch (frameType) {
                case 0:
                    bitmapReference = this.c.a(frameNumber);
                    drawn = a(frameNumber, bitmapReference, canvas, 0);
                    nextFrameType = 1;
                    break;
                case 1:
                    try {
                        bitmapReference = this.c.b();
                        if (a(frameNumber, bitmapReference) && a(frameNumber, bitmapReference, canvas, 1)) {
                            drawn = true;
                        } else {
                            drawn = false;
                        }
                        nextFrameType = 2;
                        break;
                    } catch (Throwable e) {
                        FLog.w(a, "Failed to create frame bitmap", e);
                        com.facebook.common.f.a.c(null);
                        return false;
                    } catch (Throwable th) {
                        com.facebook.common.f.a.c(bitmapReference);
                    }
                    break;
                case 2:
                    bitmapReference = this.b.a(this.j, this.k, this.l);
                    if (a(frameNumber, bitmapReference) && a(frameNumber, bitmapReference, canvas, 2)) {
                        drawn = true;
                    } else {
                        drawn = false;
                    }
                    nextFrameType = 3;
                    break;
                case 3:
                    bitmapReference = this.c.a();
                    drawn = a(frameNumber, bitmapReference, canvas, 3);
                    break;
                default:
                    com.facebook.common.f.a.c(null);
                    return false;
            }
            com.facebook.common.f.a.c(bitmapReference);
            if (!drawn && nextFrameType != -1) {
                frameType = nextFrameType;
            }
        }
        return drawn;
    }

    public final void a(@IntRange(from = 0, to = 255) int alpha) {
        this.h.setAlpha(alpha);
    }

    public final void a(@Nullable ColorFilter colorFilter) {
        this.h.setColorFilter(colorFilter);
    }

    public final void a(@Nullable Rect bounds) {
        this.i = bounds;
        this.e.a(bounds);
        g();
    }

    public final int a() {
        return this.j;
    }

    public final int b() {
        return this.k;
    }

    public final void c() {
        this.c.c();
    }

    public final void f() {
        c();
    }

    private void g() {
        int i = -1;
        this.j = this.e.a();
        if (this.j == -1) {
            this.j = this.i == null ? -1 : this.i.width();
        }
        this.k = this.e.b();
        if (this.k == -1) {
            if (this.i != null) {
                i = this.i.height();
            }
            this.k = i;
        }
    }

    private boolean a(int frameNumber, @Nullable com.facebook.common.f.a<Bitmap> targetBitmap) {
        if (!com.facebook.common.f.a.a((com.facebook.common.f.a) targetBitmap)) {
            return false;
        }
        boolean frameRendered = this.e.a(frameNumber, (Bitmap) targetBitmap.a());
        if (frameRendered) {
            return frameRendered;
        }
        com.facebook.common.f.a.c(targetBitmap);
        return frameRendered;
    }

    private boolean a(int frameNumber, @Nullable com.facebook.common.f.a<Bitmap> bitmapReference, Canvas canvas, int frameType) {
        if (!com.facebook.common.f.a.a((com.facebook.common.f.a) bitmapReference)) {
            return false;
        }
        if (this.i == null) {
            canvas.drawBitmap((Bitmap) bitmapReference.a(), 0.0f, 0.0f, this.h);
        } else {
            canvas.drawBitmap((Bitmap) bitmapReference.a(), null, this.i, this.h);
        }
        if (frameType != 3) {
            this.c.a(frameNumber, bitmapReference);
        }
        return true;
    }
}
