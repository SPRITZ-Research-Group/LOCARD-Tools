package com.airbnb.lottie.f;

public final class d {
    private final float a;
    private final float b;

    public d(float sx, float sy) {
        this.a = sx;
        this.b = sy;
    }

    public d() {
        this(1.0f, 1.0f);
    }

    public final float a() {
        return this.a;
    }

    public final float b() {
        return this.b;
    }

    public final String toString() {
        return this.a + "x" + this.b;
    }
}
