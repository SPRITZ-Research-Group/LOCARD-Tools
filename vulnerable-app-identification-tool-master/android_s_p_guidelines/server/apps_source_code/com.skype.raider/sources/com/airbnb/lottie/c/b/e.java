package com.airbnb.lottie.c.b;

import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.h;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.c;
import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.c.a.f;
import com.airbnb.lottie.c.b.p.a;
import java.util.List;

public final class e implements b {
    private final String a;
    private final f b;
    private final c c;
    private final d d;
    private final f e;
    private final f f;
    private final b g;
    private final a h;
    private final p.b i;
    private final List<b> j;
    @Nullable
    private final b k;

    public e(String name, f gradientType, c gradientColor, d opacity, f startPoint, f endPoint, b width, a capType, p.b joinType, List<b> lineDashPattern, @Nullable b dashOffset) {
        this.a = name;
        this.b = gradientType;
        this.c = gradientColor;
        this.d = opacity;
        this.e = startPoint;
        this.f = endPoint;
        this.g = width;
        this.h = capType;
        this.i = joinType;
        this.j = lineDashPattern;
        this.k = dashOffset;
    }

    public final String a() {
        return this.a;
    }

    public final f b() {
        return this.b;
    }

    public final c c() {
        return this.c;
    }

    public final d d() {
        return this.d;
    }

    public final f e() {
        return this.e;
    }

    public final f f() {
        return this.f;
    }

    public final b g() {
        return this.g;
    }

    public final a h() {
        return this.h;
    }

    public final p.b i() {
        return this.i;
    }

    public final List<b> j() {
        return this.j;
    }

    @Nullable
    public final b k() {
        return this.k;
    }

    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer) {
        return new h(drawable, layer, this);
    }
}
