package com.facebook.react.animated;

import com.facebook.react.bridge.am;

final class n extends d {
    private long e;
    private boolean f;
    private double g;
    private double h;
    private boolean i;
    private final a j = new a();
    private final a k = new a();
    private final a l = new a();
    private double m;
    private double n;
    private double o;
    private double p;
    private double q = 0.0d;
    private int r;
    private int s = 0;
    private double t;

    private static class a {
        double a;
        double b;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    n(am config) {
        int i;
        boolean z = true;
        this.g = config.getDouble("friction");
        this.h = config.getDouble("tension");
        this.j.b = config.getDouble("initialVelocity");
        this.n = config.getDouble("toValue");
        this.o = config.getDouble("restSpeedThreshold");
        this.p = config.getDouble("restDisplacementThreshold");
        this.i = config.getBoolean("overshootClamping");
        if (config.hasKey("iterations")) {
            i = config.getInt("iterations");
        } else {
            i = 1;
        }
        this.r = i;
        if (this.r != 0) {
            z = false;
        }
        this.a = z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long frameTimeNanos) {
        double d;
        long frameTimeMillis = frameTimeNanos / 1000000;
        if (!this.f) {
            if (this.s == 0) {
                this.t = this.b.e;
                this.s = 1;
            }
            a aVar = this.j;
            d = this.b.e;
            aVar.a = d;
            this.m = d;
            this.e = frameTimeMillis;
            this.f = true;
        }
        double d2 = ((double) (frameTimeMillis - this.e)) / 1000.0d;
        if (!a()) {
            if (d2 > 0.064d) {
                d2 = 0.064d;
            }
            this.q = d2 + this.q;
            double d3 = this.h;
            double d4 = this.g;
            double d5 = this.j.a;
            double d6 = this.j.b;
            d = this.l.a;
            d2 = this.l.b;
            while (this.q >= 0.001d) {
                this.q -= 0.001d;
                if (this.q < 0.001d) {
                    this.k.a = d5;
                    this.k.b = d6;
                }
                double d7 = ((this.n - d) * d3) - (d4 * d6);
                double d8 = d6 + ((0.001d * d7) * 0.5d);
                double d9 = ((this.n - (((0.001d * d6) * 0.5d) + d5)) * d3) - (d4 * d8);
                double d10 = d6 + ((0.001d * d9) * 0.5d);
                double d11 = ((this.n - (((0.001d * d8) * 0.5d) + d5)) * d3) - (d4 * d10);
                d = d5 + (0.001d * d10);
                d2 = (0.001d * d11) + d6;
                d5 += (((((d8 + d10) * 2.0d) + d6) + d2) * 0.16666666666666666d) * 0.001d;
                d6 += (((d7 + ((d9 + d11) * 2.0d)) + (((this.n - d) * d3) - (d4 * d2))) * 0.16666666666666666d) * 0.001d;
            }
            this.l.a = d;
            this.l.b = d2;
            this.j.a = d5;
            this.j.b = d6;
            if (this.q > 0.0d) {
                d2 = this.q / 0.001d;
                this.j.a = (this.j.a * d2) + (this.k.a * (1.0d - d2));
                this.j.b = ((1.0d - d2) * this.k.b) + (this.j.b * d2);
            }
            if (!a()) {
                if (this.i) {
                    Object obj;
                    if (this.h <= 0.0d || ((this.m >= this.n || this.j.a <= this.n) && (this.m <= this.n || this.j.a >= this.n))) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                }
            }
            if (d3 > 0.0d) {
                this.m = this.n;
                this.j.a = this.n;
            } else {
                this.n = this.j.a;
                this.m = this.n;
            }
            this.j.b = 0.0d;
        }
        this.e = frameTimeMillis;
        this.b.e = this.j.a;
        if (!a()) {
            return;
        }
        if (this.r == -1 || this.s < this.r) {
            this.f = false;
            this.b.e = this.t;
            this.s++;
            return;
        }
        this.a = true;
    }

    private boolean a() {
        if (Math.abs(this.j.b) <= this.o) {
            if (Math.abs(this.n - this.j.a) <= this.p || this.h == 0.0d) {
                return true;
            }
        }
        return false;
    }
}
