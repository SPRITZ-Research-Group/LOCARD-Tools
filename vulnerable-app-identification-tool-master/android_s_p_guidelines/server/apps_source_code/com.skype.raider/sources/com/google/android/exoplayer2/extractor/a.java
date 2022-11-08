package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.d.s;

public final class a implements m {
    public final int a;
    public final int[] b;
    public final long[] c;
    public final long[] d;
    public final long[] e;
    private final long f;

    public a(int[] sizes, long[] offsets, long[] durationsUs, long[] timesUs) {
        this.b = sizes;
        this.c = offsets;
        this.d = durationsUs;
        this.e = timesUs;
        this.a = sizes.length;
        if (this.a > 0) {
            this.f = durationsUs[this.a - 1] + timesUs[this.a - 1];
        } else {
            this.f = 0;
        }
    }

    public final boolean i_() {
        return true;
    }

    public final long b() {
        return this.f;
    }

    public final long a(long timeUs) {
        return this.c[s.a(this.e, timeUs, true)];
    }
}
