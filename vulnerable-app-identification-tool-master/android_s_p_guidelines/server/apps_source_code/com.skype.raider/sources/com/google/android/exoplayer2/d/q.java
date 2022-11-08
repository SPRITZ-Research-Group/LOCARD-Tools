package com.google.android.exoplayer2.d;

public final class q {
    private long a;
    private long b;
    private volatile long c = -9223372036854775807L;

    public q(long firstSampleTimestampUs) {
        c(firstSampleTimestampUs);
    }

    private synchronized void c(long firstSampleTimestampUs) {
        a.b(this.c == -9223372036854775807L);
        this.a = firstSampleTimestampUs;
    }

    public final long a() {
        return this.a;
    }

    public final long b() {
        if (this.c != -9223372036854775807L) {
            return this.c;
        }
        return this.a != Long.MAX_VALUE ? this.a : -9223372036854775807L;
    }

    public final long c() {
        if (this.a == Long.MAX_VALUE) {
            return 0;
        }
        return this.c != -9223372036854775807L ? this.b : -9223372036854775807L;
    }

    public final void d() {
        this.c = -9223372036854775807L;
    }

    public final long a(long pts) {
        if (pts == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            long lastPts = (this.c * 90000) / 1000000;
            long closestWrapCount = (4294967296L + lastPts) / 8589934592L;
            long ptsWrapBelow = pts + (8589934592L * (closestWrapCount - 1));
            long ptsWrapAbove = pts + (8589934592L * closestWrapCount);
            if (Math.abs(ptsWrapBelow - lastPts) < Math.abs(ptsWrapAbove - lastPts)) {
                pts = ptsWrapBelow;
            } else {
                pts = ptsWrapAbove;
            }
        }
        return b((1000000 * pts) / 90000);
    }

    public final long b(long timeUs) {
        if (timeUs == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            this.c = timeUs;
        } else {
            if (this.a != Long.MAX_VALUE) {
                this.b = this.a - timeUs;
            }
            synchronized (this) {
                this.c = timeUs;
                notifyAll();
            }
        }
        return this.b + timeUs;
    }
}
