package com.airbnb.lottie.c.b;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.n;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.f;
import com.airbnb.lottie.c.a.m;
import com.airbnb.lottie.c.c.a;

public final class j implements b {
    private final String a;
    private final m<PointF, PointF> b;
    private final f c;
    private final b d;

    public j(String name, m<PointF, PointF> position, f size, b cornerRadius) {
        this.a = name;
        this.b = position;
        this.c = size;
        this.d = cornerRadius;
    }

    public final String a() {
        return this.a;
    }

    public final b b() {
        return this.d;
    }

    public final f c() {
        return this.c;
    }

    public final m<PointF, PointF> d() {
        return this.b;
    }

    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, a layer) {
        return new n(drawable, layer, this);
    }

    public final String toString() {
        return "RectangleShape{position=" + this.b + ", size=" + this.c + '}';
    }
}
