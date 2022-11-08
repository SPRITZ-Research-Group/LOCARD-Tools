package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.ah.e;

final class bw implements bi {
    private final bk a;
    private final String b;
    private final bx c;

    bw(bk bkVar, String str, Object[] objArr) {
        this.a = bkVar;
        this.b = str;
        this.c = new bx(bkVar.getClass(), str, objArr);
    }

    public final int a() {
        return (this.c.d & 1) == 1 ? e.h : e.i;
    }

    public final boolean b() {
        return (this.c.d & 2) == 2;
    }

    public final bk c() {
        return this.a;
    }

    final bx d() {
        return this.c;
    }

    public final int e() {
        return this.c.h;
    }

    public final int f() {
        return this.c.i;
    }

    public final int g() {
        return this.c.e;
    }

    public final int h() {
        return this.c.j;
    }

    public final int i() {
        return this.c.m;
    }

    final int[] j() {
        return this.c.n;
    }

    public final int k() {
        return this.c.l;
    }

    public final int l() {
        return this.c.k;
    }
}
