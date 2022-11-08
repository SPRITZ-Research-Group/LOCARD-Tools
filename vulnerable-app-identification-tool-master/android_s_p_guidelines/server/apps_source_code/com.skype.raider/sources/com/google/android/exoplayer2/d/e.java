package com.google.android.exoplayer2.d;

public final class e {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final long h;

    public e(byte[] data) {
        j scratch = new j(data);
        scratch.a(136);
        this.a = scratch.c(16);
        this.b = scratch.c(16);
        this.c = scratch.c(24);
        this.d = scratch.c(24);
        this.e = scratch.c(20);
        this.f = scratch.c(3) + 1;
        this.g = scratch.c(5) + 1;
        this.h = (long) scratch.c(36);
    }
}
