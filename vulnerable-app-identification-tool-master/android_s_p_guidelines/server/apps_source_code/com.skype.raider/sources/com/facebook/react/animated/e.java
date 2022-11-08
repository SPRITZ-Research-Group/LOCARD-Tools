package com.facebook.react.animated;

import com.facebook.react.bridge.am;

public final class e extends d {
    private final double e;
    private final double f;
    private long g = -1;
    private double h = 0.0d;
    private double i = 0.0d;
    private int j;
    private int k;

    public e(am config) {
        int i;
        boolean z = true;
        this.e = config.getDouble("velocity");
        this.f = config.getDouble("deceleration");
        if (config.hasKey("iterations")) {
            i = config.getInt("iterations");
        } else {
            i = 1;
        }
        this.j = i;
        this.k = 1;
        if (this.j != 0) {
            z = false;
        }
        this.a = z;
    }

    public final void a(long frameTimeNanos) {
        long frameTimeMillis = frameTimeNanos / 1000000;
        if (this.g == -1) {
            this.g = frameTimeMillis - 16;
            if (this.h == this.i) {
                this.h = this.b.e;
            } else {
                this.b.e = this.h;
            }
            this.i = this.b.e;
        }
        double value = this.h + ((this.e / (1.0d - this.f)) * (1.0d - Math.exp((-(1.0d - this.f)) * ((double) (frameTimeMillis - this.g)))));
        if (Math.abs(this.i - value) < 0.1d) {
            if (this.j == -1 || this.k < this.j) {
                this.g = -1;
                this.k++;
            } else {
                this.a = true;
                return;
            }
        }
        this.i = value;
        this.b.e = value;
    }
}
