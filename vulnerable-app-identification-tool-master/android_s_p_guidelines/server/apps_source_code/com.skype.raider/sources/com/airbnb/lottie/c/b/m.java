package com.airbnb.lottie.c.b;

import android.graphics.Path.FillType;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.b;
import com.airbnb.lottie.a.a.f;
import com.airbnb.lottie.c.a.a;
import com.airbnb.lottie.c.a.d;

public final class m implements b {
    private final boolean a;
    private final FillType b;
    private final String c;
    @Nullable
    private final a d;
    @Nullable
    private final d e;

    public m(String name, boolean fillEnabled, FillType fillType, @Nullable a color, @Nullable d opacity) {
        this.c = name;
        this.a = fillEnabled;
        this.b = fillType;
        this.d = color;
        this.e = opacity;
    }

    public final String a() {
        return this.c;
    }

    @Nullable
    public final a b() {
        return this.d;
    }

    @Nullable
    public final d c() {
        return this.e;
    }

    public final FillType d() {
        return this.b;
    }

    public final b a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer) {
        return new f(drawable, layer, this);
    }

    public final String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.a + '}';
    }
}
