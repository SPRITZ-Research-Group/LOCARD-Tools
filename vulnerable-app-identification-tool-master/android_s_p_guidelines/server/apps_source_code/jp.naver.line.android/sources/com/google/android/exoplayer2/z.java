package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.trackselection.o;

final class z {
    private static final m n = new m(new Object());
    public final ao a;
    public final Object b;
    public final m c;
    public final long d;
    public final long e;
    public final int f;
    public final boolean g;
    public final TrackGroupArray h;
    public final o i;
    public final m j;
    public volatile long k;
    public volatile long l;
    public volatile long m;

    public static z a(long j, o oVar) {
        return new z(ao.a, null, n, j, -9223372036854775807L, 1, false, TrackGroupArray.a, oVar, n, j, 0, j);
    }

    public z(ao aoVar, Object obj, m mVar, long j, long j2, int i, boolean z, TrackGroupArray trackGroupArray, o oVar, m mVar2, long j3, long j4, long j5) {
        this.a = aoVar;
        this.b = obj;
        this.c = mVar;
        this.d = j;
        this.e = j2;
        this.f = i;
        this.g = z;
        this.h = trackGroupArray;
        this.i = oVar;
        this.j = mVar2;
        this.k = j3;
        this.l = j4;
        this.m = j5;
    }

    public final m a(aq aqVar) {
        if (this.a.a()) {
            return n;
        }
        return new m(this.a.a(this.a.a(this.a.c(), aqVar, 0).f));
    }

    public final z a(m mVar, long j, long j2) {
        return new z(this.a, this.b, mVar, j, mVar.a() ? j2 : -9223372036854775807L, r0.f, r0.g, r0.h, r0.i, mVar, j, 0, j);
    }

    public final z a(m mVar, long j, long j2, long j3) {
        return new z(this.a, this.b, mVar, j, mVar.a() ? j2 : -9223372036854775807L, r0.f, r0.g, r0.h, r0.i, r0.j, r0.k, j3, j);
    }

    public final z a(int i) {
        int i2 = i;
        return new z(this.a, this.b, this.c, this.d, this.e, i2, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
    }

    public final z a(boolean z) {
        boolean z2 = z;
        return new z(this.a, this.b, this.c, this.d, this.e, this.f, z2, this.h, this.i, this.j, this.k, this.l, this.m);
    }

    public final z a(TrackGroupArray trackGroupArray, o oVar) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        o oVar2 = oVar;
        return new z(this.a, this.b, this.c, this.d, this.e, this.f, this.g, trackGroupArray2, oVar2, this.j, this.k, this.l, this.m);
    }

    public final z a(m mVar) {
        m mVar2 = mVar;
        return new z(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, mVar2, this.k, this.l, this.m);
    }
}
