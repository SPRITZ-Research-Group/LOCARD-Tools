package com.google.android.exoplayer2;

import com.google.android.exoplayer2.trackselection.m;
import java.util.Set;

final class l {
    private final z a;
    private final Set<ac> b;
    private final m c;
    private final boolean d;
    private final int e;
    private final int f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;

    public l(z zVar, z zVar2, Set<ac> set, m mVar, boolean z, int i, int i2, boolean z2, boolean z3, boolean z4) {
        this.a = zVar;
        this.b = set;
        this.c = mVar;
        this.d = z;
        this.e = i;
        this.f = i2;
        this.g = z2;
        this.h = z3;
        boolean z5 = false;
        z = z4 || zVar2.f != zVar.f;
        this.i = z;
        z = (zVar2.a == zVar.a && zVar2.b == zVar.b) ? false : true;
        this.j = z;
        this.k = zVar2.g != zVar.g;
        if (zVar2.i != zVar.i) {
            z5 = true;
        }
        this.l = z5;
    }

    public final void a() {
        if (this.j || this.f == 0) {
            for (ac onTimelineChanged : this.b) {
                onTimelineChanged.onTimelineChanged(this.a.a, this.a.b, this.f);
            }
        }
        if (this.d) {
            for (ac onTimelineChanged2 : this.b) {
                onTimelineChanged2.onPositionDiscontinuity(this.e);
            }
        }
        if (this.l) {
            this.c.a(this.a.i.d);
            for (ac onTimelineChanged22 : this.b) {
                onTimelineChanged22.onTracksChanged(this.a.h, this.a.i.c);
            }
        }
        if (this.k) {
            for (ac onTimelineChanged222 : this.b) {
                onTimelineChanged222.onLoadingChanged(this.a.g);
            }
        }
        if (this.i) {
            for (ac onTimelineChanged2222 : this.b) {
                onTimelineChanged2222.onPlayerStateChanged(this.h, this.a.f);
            }
        }
        if (this.g) {
            for (ac onTimelineChanged22222 : this.b) {
                onTimelineChanged22222.onSeekProcessed();
            }
        }
    }
}
