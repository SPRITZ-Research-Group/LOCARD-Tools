package com.airbnb.lottie.c.b;

import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.q;
import com.airbnb.lottie.c.a.d;
import java.util.List;

public final class p implements b {
    private final String a;
    @Nullable
    private final com.airbnb.lottie.c.a.b b;
    private final List<com.airbnb.lottie.c.a.b> c;
    private final com.airbnb.lottie.c.a.a d;
    private final d e;
    private final com.airbnb.lottie.c.a.b f;
    private final a g;
    private final b h;

    public enum a {
        Butt,
        Round,
        Unknown;

        public final Cap a() {
            switch (this) {
                case Butt:
                    return Cap.BUTT;
                case Round:
                    return Cap.ROUND;
                default:
                    return Cap.SQUARE;
            }
        }
    }

    public enum b {
        Miter,
        Round,
        Bevel;

        public final Join a() {
            switch (this) {
                case Bevel:
                    return Join.BEVEL;
                case Miter:
                    return Join.MITER;
                case Round:
                    return Join.ROUND;
                default:
                    return null;
            }
        }
    }

    public p(String name, @Nullable com.airbnb.lottie.c.a.b offset, List<com.airbnb.lottie.c.a.b> lineDashPattern, com.airbnb.lottie.c.a.a color, d opacity, com.airbnb.lottie.c.a.b width, a capType, b joinType) {
        this.a = name;
        this.b = offset;
        this.c = lineDashPattern;
        this.d = color;
        this.e = opacity;
        this.f = width;
        this.g = capType;
        this.h = joinType;
    }

    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer) {
        return new q(drawable, layer, this);
    }

    public final String a() {
        return this.a;
    }

    public final com.airbnb.lottie.c.a.a b() {
        return this.d;
    }

    public final d c() {
        return this.e;
    }

    public final com.airbnb.lottie.c.a.b d() {
        return this.f;
    }

    public final List<com.airbnb.lottie.c.a.b> e() {
        return this.c;
    }

    public final com.airbnb.lottie.c.a.b f() {
        return this.b;
    }

    public final a g() {
        return this.g;
    }

    public final b h() {
        return this.h;
    }
}
