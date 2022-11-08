package com.airbnb.lottie.c.b;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.b;
import com.airbnb.lottie.a.a.e;
import com.airbnb.lottie.c.a.f;
import com.airbnb.lottie.c.a.m;

public final class a implements b {
    private final String a;
    private final m<PointF, PointF> b;
    private final f c;
    private final boolean d;

    public a(String name, m<PointF, PointF> position, f size, boolean isReversed) {
        this.a = name;
        this.b = position;
        this.c = size;
        this.d = isReversed;
    }

    public final b a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer) {
        return new e(drawable, layer, this);
    }

    public final String a() {
        return this.a;
    }

    public final m<PointF, PointF> b() {
        return this.b;
    }

    public final f c() {
        return this.c;
    }

    public final boolean d() {
        return this.d;
    }
}
