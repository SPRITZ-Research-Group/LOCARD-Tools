package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.r;
import com.google.android.exoplayer2.r.b;

public final class i extends r {
    private static final Object b = new Object();
    private final long c;
    private final long d;
    private final long e;
    private final long f;
    private final boolean g;
    private final boolean h;

    public i(long durationUs, boolean isSeekable) {
        this(durationUs, durationUs, isSeekable);
    }

    private i(long periodDurationUs, long windowDurationUs, boolean isSeekable) {
        this.c = periodDurationUs;
        this.d = windowDurationUs;
        this.e = 0;
        this.f = 0;
        this.g = isSeekable;
        this.h = false;
    }

    public final int b() {
        return 1;
    }

    public final b a(int windowIndex, b window, long defaultPositionProjectionUs) {
        a.a(windowIndex, 1);
        long windowDefaultStartPositionUs = this.f;
        if (this.h) {
            windowDefaultStartPositionUs += defaultPositionProjectionUs;
            if (windowDefaultStartPositionUs > this.d) {
                windowDefaultStartPositionUs = -9223372036854775807L;
            }
        }
        boolean z = this.g;
        boolean z2 = this.h;
        long j = this.d;
        long j2 = this.e;
        window.a = null;
        window.b = -9223372036854775807L;
        window.c = -9223372036854775807L;
        window.d = z;
        window.e = z2;
        window.h = windowDefaultStartPositionUs;
        window.i = j;
        window.f = 0;
        window.g = 0;
        window.j = j2;
        return window;
    }

    public final int c() {
        return 1;
    }

    public final r.a a(int periodIndex, r.a period, boolean setIds) {
        a.a(periodIndex, 1);
        Object id = setIds ? b : null;
        return period.a(id, id, this.c, -this.e);
    }

    public final int a(Object uid) {
        return b.equals(uid) ? 0 : -1;
    }
}
