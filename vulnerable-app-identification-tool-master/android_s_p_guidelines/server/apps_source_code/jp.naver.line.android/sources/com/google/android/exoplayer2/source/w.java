package com.google.android.exoplayer2.source;

import defpackage.bbe;

final class w {
    public final long a;
    public final long b;
    public boolean c;
    public bbe d;
    public w e;

    public w(long j, int i) {
        this.a = j;
        this.b = j + ((long) i);
    }

    public final void a(bbe bbe, w wVar) {
        this.d = bbe;
        this.e = wVar;
        this.c = true;
    }

    public final int a(long j) {
        return ((int) (j - this.a)) + this.d.b;
    }

    public final w a() {
        this.d = null;
        w wVar = this.e;
        this.e = null;
        return wVar;
    }
}
