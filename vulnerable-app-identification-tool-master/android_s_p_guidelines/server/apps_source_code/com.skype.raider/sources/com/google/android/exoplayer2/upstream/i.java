package com.google.android.exoplayer2.upstream;

import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.d.o;
import com.google.android.exoplayer2.upstream.d.a;

public final class i implements d, p<Object> {
    private final Handler a;
    private final a b;
    private final o c;
    private int d;
    private long e;
    private long f;
    private long g;
    private long h;
    private long i;

    public i() {
        this((byte) 0);
    }

    private i(byte b) {
        this(0);
    }

    private i(char c) {
        this.a = null;
        this.b = null;
        this.c = new o();
        this.i = -1;
    }

    public final synchronized long a() {
        return this.i;
    }

    public final synchronized void b() {
        if (this.d == 0) {
            this.e = SystemClock.elapsedRealtime();
        }
        this.d++;
    }

    public final synchronized void a(int bytes) {
        this.f += (long) bytes;
    }

    public final synchronized void c() {
        com.google.android.exoplayer2.d.a.b(this.d > 0);
        long nowMs = SystemClock.elapsedRealtime();
        final int sampleElapsedTimeMs = (int) (nowMs - this.e);
        this.g += (long) sampleElapsedTimeMs;
        this.h += this.f;
        if (sampleElapsedTimeMs > 0) {
            this.c.a((int) Math.sqrt((double) this.f), (float) ((this.f * 8000) / ((long) sampleElapsedTimeMs)));
            if (this.g >= 2000 || this.h >= 524288) {
                float bitrateEstimateFloat = this.c.a();
                this.i = Float.isNaN(bitrateEstimateFloat) ? -1 : (long) bitrateEstimateFloat;
            }
        }
        final long j = this.f;
        final long j2 = this.i;
        if (!(this.a == null || this.b == null)) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ i d;

                public final void run() {
                }
            });
        }
        int i = this.d - 1;
        this.d = i;
        if (i > 0) {
            this.e = nowMs;
        }
        this.f = 0;
    }
}
