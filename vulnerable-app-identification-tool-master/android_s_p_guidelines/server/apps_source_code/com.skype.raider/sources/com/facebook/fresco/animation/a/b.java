package com.facebook.fresco.animation.a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import javax.annotation.Nullable;

public class b<T extends a> implements a {
    @Nullable
    private T a;
    @IntRange(from = -1, to = 255)
    private int b = -1;
    @Nullable
    private ColorFilter c;
    @Nullable
    private Rect d;

    public b(@Nullable T animationBackend) {
        this.a = animationBackend;
    }

    public final int d() {
        return this.a == null ? 0 : this.a.d();
    }

    public final int b(int frameNumber) {
        return this.a == null ? 0 : this.a.b(frameNumber);
    }

    public final int e() {
        return this.a == null ? 0 : this.a.e();
    }

    public boolean a(Drawable parent, Canvas canvas, int frameNumber) {
        return this.a != null && this.a.a(parent, canvas, frameNumber);
    }

    public final void a(@IntRange(from = 0, to = 255) int alpha) {
        if (this.a != null) {
            this.a.a(alpha);
        }
        this.b = alpha;
    }

    public final void a(ColorFilter colorFilter) {
        if (this.a != null) {
            this.a.a(colorFilter);
        }
        this.c = colorFilter;
    }

    public final void a(@Nullable Rect bounds) {
        if (this.a != null) {
            this.a.a(bounds);
        }
        this.d = bounds;
    }

    public final void c() {
        if (this.a != null) {
            this.a.c();
        }
    }

    public final int a() {
        if (this.a == null) {
            return -1;
        }
        return this.a.a();
    }

    public final int b() {
        if (this.a == null) {
            return -1;
        }
        return this.a.b();
    }
}
