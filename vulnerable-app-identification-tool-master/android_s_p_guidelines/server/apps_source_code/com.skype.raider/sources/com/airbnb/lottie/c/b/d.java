package com.airbnb.lottie.c.b;

import android.graphics.Path.FillType;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.g;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.c;
import com.airbnb.lottie.c.a.f;
import com.airbnb.lottie.c.c.a;

public final class d implements b {
    private final f a;
    private final FillType b;
    private final c c;
    private final com.airbnb.lottie.c.a.d d;
    private final f e;
    private final f f;
    private final String g;
    @Nullable
    private final b h = null;
    @Nullable
    private final b i = null;

    public d(String name, f gradientType, FillType fillType, c gradientColor, com.airbnb.lottie.c.a.d opacity, f startPoint, f endPoint) {
        this.a = gradientType;
        this.b = fillType;
        this.c = gradientColor;
        this.d = opacity;
        this.e = startPoint;
        this.f = endPoint;
        this.g = name;
    }

    public final String a() {
        return this.g;
    }

    public final f b() {
        return this.a;
    }

    public final FillType c() {
        return this.b;
    }

    public final c d() {
        return this.c;
    }

    public final com.airbnb.lottie.c.a.d e() {
        return this.d;
    }

    public final f f() {
        return this.e;
    }

    public final f g() {
        return this.f;
    }

    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, a layer) {
        return new g(drawable, layer, this);
    }
}
