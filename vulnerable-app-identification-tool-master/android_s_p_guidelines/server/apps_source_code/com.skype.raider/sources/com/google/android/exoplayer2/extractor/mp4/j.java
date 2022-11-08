package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.d.k;

final class j {
    public c a;
    public long b;
    public long c;
    public long d;
    public int e;
    public int f;
    public long[] g;
    public int[] h;
    public int[] i;
    public int[] j;
    public long[] k;
    public boolean[] l;
    public boolean m;
    public boolean[] n;
    public i o;
    public int p;
    public k q;
    public boolean r;
    public long s;

    j() {
    }

    public final void a(int length) {
        if (this.q == null || this.q.c() < length) {
            this.q = new k(length);
        }
        this.p = length;
        this.m = true;
        this.r = true;
    }

    public final long b(int index) {
        return this.k[index] + ((long) this.j[index]);
    }
}
