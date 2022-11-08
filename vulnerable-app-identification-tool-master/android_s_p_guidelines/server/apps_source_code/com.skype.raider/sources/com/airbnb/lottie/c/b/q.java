package com.airbnb.lottie.c.b;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.r;
import com.airbnb.lottie.c.a.b;

public final class q implements b {
    private final String a;
    private final a b;
    private final b c;
    private final b d;
    private final b e;

    public enum a {
        Simultaneously,
        Individually;

        public static a a(int id) {
            switch (id) {
                case 1:
                    return Simultaneously;
                case 2:
                    return Individually;
                default:
                    throw new IllegalArgumentException("Unknown trim path type " + id);
            }
        }
    }

    public q(String name, a type, b start, b end, b offset) {
        this.a = name;
        this.b = type;
        this.c = start;
        this.d = end;
        this.e = offset;
    }

    public final String a() {
        return this.a;
    }

    public final a b() {
        return this.b;
    }

    public final b c() {
        return this.d;
    }

    public final b d() {
        return this.c;
    }

    public final b e() {
        return this.e;
    }

    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer) {
        return new r(layer, this);
    }

    public final String toString() {
        return "Trim Path: {start: " + this.c + ", end: " + this.d + ", offset: " + this.e + "}";
    }
}
