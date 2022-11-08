package com.airbnb.lottie.c;

import android.graphics.PointF;

public final class a {
    private final PointF a;
    private final PointF b;
    private final PointF c;

    public a() {
        this.a = new PointF();
        this.b = new PointF();
        this.c = new PointF();
    }

    public a(PointF controlPoint1, PointF controlPoint2, PointF vertex) {
        this.a = controlPoint1;
        this.b = controlPoint2;
        this.c = vertex;
    }

    public final void a(float x, float y) {
        this.a.set(x, y);
    }

    public final PointF a() {
        return this.a;
    }

    public final void b(float x, float y) {
        this.b.set(x, y);
    }

    public final PointF b() {
        return this.b;
    }

    public final void c(float x, float y) {
        this.c.set(x, y);
    }

    public final PointF c() {
        return this.c;
    }
}
