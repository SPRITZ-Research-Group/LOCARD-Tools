package com.google.android.exoplayer2.d;

import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.l;

public final class p implements g {
    private boolean a;
    private long b;
    private long c;
    private l d = l.a;

    public final void a() {
        if (!this.a) {
            this.c = SystemClock.elapsedRealtime();
            this.a = true;
        }
    }

    public final void b() {
        if (this.a) {
            a(y());
            this.a = false;
        }
    }

    public final void a(long positionUs) {
        this.b = positionUs;
        if (this.a) {
            this.c = SystemClock.elapsedRealtime();
        }
    }

    public final void a(g clock) {
        a(clock.y());
        this.d = clock.z();
    }

    public final long y() {
        long positionUs = this.b;
        if (!this.a) {
            return positionUs;
        }
        long elapsedSinceBaseMs = SystemClock.elapsedRealtime() - this.c;
        if (this.d.b == 1.0f) {
            return positionUs + C.b(elapsedSinceBaseMs);
        }
        return positionUs + this.d.a(elapsedSinceBaseMs);
    }

    public final l a(l playbackParameters) {
        if (this.a) {
            a(y());
        }
        this.d = playbackParameters;
        return playbackParameters;
    }

    public final l z() {
        return this.d;
    }
}
