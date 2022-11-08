package com.airbnb.lottie.e;

public final class d {
    private float a;
    private int b;

    public final void a(float number) {
        this.a += number;
        this.b++;
        if (this.b == Integer.MAX_VALUE) {
            this.a /= 2.0f;
            this.b /= 2;
        }
    }
}
