package com.facebook.ads.internal.b;

import java.io.Serializable;

public final class c implements Serializable {
    private a a;
    private a b;

    public static class a implements Serializable {
        private double a;
        private double b;
        private double c;
        private double d;
        private double e;
        private double f;
        private double g;
        private int h;
        private double i;
        private double j;
        private double k;

        public a(double d) {
            this.e = d;
        }

        public final void a() {
            this.a = 0.0d;
            this.c = 0.0d;
            this.d = 0.0d;
            this.f = 0.0d;
            this.h = 0;
            this.i = 0.0d;
            this.j = 1.0d;
            this.k = 0.0d;
        }

        public final void a(double d, double d2) {
            this.h++;
            this.i += d;
            this.c = d2;
            this.k += d2 * d;
            this.a = this.k / this.i;
            this.j = Math.min(this.j, d2);
            this.f = Math.max(this.f, d2);
            if (d2 >= this.e) {
                this.d += d;
                this.b += d;
                this.g = Math.max(this.g, this.b);
                return;
            }
            this.b = 0.0d;
        }

        public final void b() {
            this.b = 0.0d;
        }

        public final double c() {
            return this.h == 0 ? 0.0d : this.j;
        }

        public final double d() {
            return this.a;
        }

        public final double e() {
            return this.f;
        }

        public final double f() {
            return this.i;
        }

        public final double g() {
            return this.d;
        }

        public final double h() {
            return this.g;
        }
    }

    public c() {
        this(0.5d, (byte) 0);
    }

    public c(double d) {
        this(d, (byte) 0);
    }

    private c(double d, byte b) {
        this.a = new a(d);
        this.b = new a(0.5d);
        a();
    }

    final void a() {
        this.a.a();
        this.b.a();
    }

    final void a(double d, double d2) {
        this.a.a(d, d2);
    }

    final void b() {
        this.a.b();
        this.b.b();
    }

    final void b(double d, double d2) {
        this.b.a(d, d2);
    }

    public final a c() {
        return this.a;
    }

    public final a d() {
        return this.b;
    }
}
