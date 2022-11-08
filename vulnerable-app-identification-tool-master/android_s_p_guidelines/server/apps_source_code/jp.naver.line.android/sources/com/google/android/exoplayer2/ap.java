package com.google.android.exoplayer2;

import defpackage.axb;
import defpackage.axc;

public final class ap {
    public Object a;
    public Object b;
    public int c;
    public long d;
    private long e;
    private axb f;

    public final ap a(Object obj, long j, long j2) {
        axb axb = axb.a;
        this.a = null;
        this.b = obj;
        this.c = 0;
        this.d = j;
        this.e = j2;
        this.f = axb;
        return this;
    }

    public final long a() {
        return c.a(this.e);
    }

    public final int b() {
        return this.f.b;
    }

    public final long a(int i) {
        return this.f.c[i];
    }

    public final int b(int i) {
        return this.f.d[i].a(-1);
    }

    public final int a(int i, int i2) {
        return this.f.d[i].a(i2);
    }

    public final boolean c(int i) {
        return !this.f.d[i].a();
    }

    public final int a(long j) {
        axb axb = this.f;
        int length = axb.c.length - 1;
        while (length >= 0) {
            long j2 = axb.c[length];
            Object obj = null;
            if (j2 != Long.MIN_VALUE ? j >= j2 : axb.f != -9223372036854775807L && j >= axb.f) {
                obj = 1;
            }
            if (obj == null) {
                break;
            }
            length--;
        }
        return (length < 0 || !axb.d[length].a()) ? -1 : length;
    }

    public final int b(long j) {
        axb axb = this.f;
        int i = 0;
        while (i < axb.c.length && axb.c[i] != Long.MIN_VALUE && (j >= axb.c[i] || !axb.d[i].a())) {
            i++;
        }
        return i < axb.c.length ? i : -1;
    }

    public final int d(int i) {
        return this.f.d[i].a;
    }

    public final boolean b(int i, int i2) {
        axc axc = this.f.d[i];
        return (axc.a == -1 || axc.c[i2] == 0) ? false : true;
    }

    public final long c(int i, int i2) {
        axc axc = this.f.d[i];
        return axc.a != -1 ? axc.d[i2] : -9223372036854775807L;
    }

    public final long c() {
        return this.f.e;
    }
}
