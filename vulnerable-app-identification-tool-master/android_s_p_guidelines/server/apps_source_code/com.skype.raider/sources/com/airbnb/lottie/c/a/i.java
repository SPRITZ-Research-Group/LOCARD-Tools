package com.airbnb.lottie.c.a;

import android.graphics.PointF;
import com.airbnb.lottie.a.b.a;
import com.airbnb.lottie.a.b.m;

public final class i implements m<PointF, PointF> {
    private final b a;
    private final b b;

    public i(b animatableXDimension, b animatableYDimension) {
        this.a = animatableXDimension;
        this.b = animatableYDimension;
    }

    public final a<PointF, PointF> a() {
        return new m(this.a.a(), this.b.a());
    }
}
