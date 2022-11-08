package com.airbnb.lottie.c.b;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.m;

public final class i implements b {
    private final String a;
    private final a b;
    private final b c;
    private final m<PointF, PointF> d;
    private final b e;
    private final b f;
    private final b g;
    private final b h;
    private final b i;

    public enum a {
        Star(1),
        Polygon(2);
        
        private final int c;

        private a(int value) {
            this.c = value;
        }

        public static a a(int value) {
            for (a type : values()) {
                if (type.c == value) {
                    return type;
                }
            }
            return null;
        }
    }

    public i(String name, a type, b points, m<PointF, PointF> position, b rotation, b innerRadius, b outerRadius, b innerRoundedness, b outerRoundedness) {
        this.a = name;
        this.b = type;
        this.c = points;
        this.d = position;
        this.e = rotation;
        this.f = innerRadius;
        this.g = outerRadius;
        this.h = innerRoundedness;
        this.i = outerRoundedness;
    }

    public final String a() {
        return this.a;
    }

    public final a b() {
        return this.b;
    }

    public final b c() {
        return this.c;
    }

    public final m<PointF, PointF> d() {
        return this.d;
    }

    public final b e() {
        return this.e;
    }

    public final b f() {
        return this.f;
    }

    public final b g() {
        return this.g;
    }

    public final b h() {
        return this.h;
    }

    public final b i() {
        return this.i;
    }

    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer) {
        return new com.airbnb.lottie.a.a.m(drawable, layer, this);
    }
}
