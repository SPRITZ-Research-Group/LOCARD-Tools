package com.facebook.imagepipeline.a.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.facebook.imagepipeline.a.a.b;
import com.facebook.imagepipeline.a.a.c;
import com.facebook.imagepipeline.a.a.d;
import com.facebook.imagepipeline.a.a.e;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class a implements com.facebook.imagepipeline.a.a.a {
    private final com.facebook.imagepipeline.a.d.a a;
    private final e b;
    private final c c;
    private final Rect d;
    private final int[] e;
    private final int[] f;
    private final int g;
    private final b[] h;
    private final Rect i = new Rect();
    private final Rect j = new Rect();
    @GuardedBy("this")
    @Nullable
    private Bitmap k;

    public a(com.facebook.imagepipeline.a.d.a animatedDrawableUtil, e animatedImageResult, Rect bounds) {
        this.a = animatedDrawableUtil;
        this.b = animatedImageResult;
        this.c = animatedImageResult.a();
        this.e = this.c.getFrameDurations();
        com.facebook.imagepipeline.a.d.a.a(this.e);
        this.g = com.facebook.imagepipeline.a.d.a.b(this.e);
        this.f = com.facebook.imagepipeline.a.d.a.c(this.e);
        this.d = a(this.c, bounds);
        this.h = new b[this.c.getFrameCount()];
        for (int i = 0; i < this.c.getFrameCount(); i++) {
            this.h[i] = this.c.getFrameInfo(i);
        }
    }

    private static Rect a(c image, Rect targetBounds) {
        if (targetBounds == null) {
            return new Rect(0, 0, image.getWidth(), image.getHeight());
        }
        return new Rect(0, 0, Math.min(targetBounds.width(), image.getWidth()), Math.min(targetBounds.height(), image.getHeight()));
    }

    public final int a() {
        return this.c.getFrameCount();
    }

    public final int b() {
        return this.c.getLoopCount();
    }

    public final int c() {
        return this.c.getWidth();
    }

    public final int d() {
        return this.c.getHeight();
    }

    public final int e() {
        return this.d.width();
    }

    public final int f() {
        return this.d.height();
    }

    public final b a(int frameNumber) {
        return this.h[frameNumber];
    }

    public final int b(int frameNumber) {
        return this.e[frameNumber];
    }

    public final com.facebook.imagepipeline.a.a.a a(Rect bounds) {
        return a(this.c, bounds).equals(this.d) ? this : new a(this.a, this.b, bounds);
    }

    public final void a(int frameNumber, Canvas canvas) {
        d frame = this.c.getFrame(frameNumber);
        try {
            int round;
            int d;
            int e;
            int width;
            if (this.c.doesRenderSupportScaling()) {
                double width2 = ((double) this.d.width()) / ((double) this.c.getWidth());
                double height = ((double) this.d.height()) / ((double) this.c.getHeight());
                round = (int) Math.round(((double) frame.b()) * width2);
                int round2 = (int) Math.round(((double) frame.c()) * height);
                d = (int) (width2 * ((double) frame.d()));
                e = (int) (height * ((double) frame.e()));
                synchronized (this) {
                    width = this.d.width();
                    int height2 = this.d.height();
                    a(width, height2);
                    frame.a(round, round2, this.k);
                    this.i.set(0, 0, width, height2);
                    this.j.set(d, e, width + d, height2 + e);
                    canvas.drawBitmap(this.k, this.i, this.j, null);
                }
            } else {
                round = frame.b();
                d = frame.c();
                e = frame.d();
                width = frame.e();
                synchronized (this) {
                    a(round, d);
                    frame.a(round, d, this.k);
                    this.i.set(0, 0, round, d);
                    this.j.set(0, 0, round, d);
                    canvas.save();
                    canvas.translate((float) e, (float) width);
                    canvas.drawBitmap(this.k, this.i, this.j, null);
                    canvas.restore();
                }
            }
            frame.a();
        } catch (Throwable th) {
            frame.a();
        }
    }

    private synchronized void a(int width, int height) {
        if (this.k != null && (this.k.getWidth() < width || this.k.getHeight() < height)) {
            g();
        }
        if (this.k == null) {
            this.k = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        }
        this.k.eraseColor(0);
    }

    private synchronized void g() {
        if (this.k != null) {
            this.k.recycle();
            this.k = null;
        }
    }
}
