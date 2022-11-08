package com.google.android.exoplayer2;

import android.os.Handler;
import defpackage.bcz;

public final class ad {
    private final af a;
    private final ae b;
    private final ao c;
    private int d;
    private Object e;
    private Handler f;
    private int g;
    private long h = -9223372036854775807L;
    private boolean i = true;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;

    public ad(ae aeVar, af afVar, ao aoVar, int i, Handler handler) {
        this.b = aeVar;
        this.a = afVar;
        this.c = aoVar;
        this.f = handler;
        this.g = i;
    }

    public final ao a() {
        return this.c;
    }

    public final af b() {
        return this.a;
    }

    public final ad a(int i) {
        bcz.b(this.j ^ 1);
        this.d = i;
        return this;
    }

    public final int c() {
        return this.d;
    }

    public final ad a(Object obj) {
        bcz.b(this.j ^ 1);
        this.e = obj;
        return this;
    }

    public final Object d() {
        return this.e;
    }

    public final Handler e() {
        return this.f;
    }

    public final long f() {
        return this.h;
    }

    public final int g() {
        return this.g;
    }

    public final boolean h() {
        return this.i;
    }

    public final ad i() {
        bcz.b(this.j ^ true);
        if (this.h == -9223372036854775807L) {
            bcz.a(this.i);
        }
        this.j = true;
        this.b.a(this);
        return this;
    }

    public final synchronized boolean j() {
        return this.m;
    }

    public final synchronized boolean k() throws InterruptedException {
        bcz.b(this.j);
        bcz.b(this.f.getLooper().getThread() != Thread.currentThread());
        while (!this.l) {
            wait();
        }
        return this.k;
    }

    public final synchronized void a(boolean z) {
        this.k = z | this.k;
        this.l = true;
        notifyAll();
    }
}
