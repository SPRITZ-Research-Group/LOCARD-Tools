package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.d;
import com.google.android.exoplayer2.source.i;
import com.google.android.exoplayer2.source.j;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.y;
import com.google.android.exoplayer2.trackselection.m;
import com.google.android.exoplayer2.trackselection.o;
import defpackage.bbf;
import defpackage.bcz;
import defpackage.bdk;

final class v {
    public final j a;
    public final Object b;
    public final y[] c;
    public final boolean[] d;
    public boolean e;
    public boolean f;
    public w g;
    public v h;
    public TrackGroupArray i;
    public o j;
    private final ah[] k;
    private final m l;
    private final l m;
    private long n;
    private o o;

    public v(ah[] ahVarArr, long j, m mVar, bbf bbf, l lVar, w wVar) {
        this.k = ahVarArr;
        this.n = j - wVar.b;
        this.l = mVar;
        this.m = lVar;
        this.b = bcz.a(wVar.a.a);
        this.g = wVar;
        this.c = new y[ahVarArr.length];
        this.d = new boolean[ahVarArr.length];
        j a = lVar.a(wVar.a, bbf);
        if (wVar.a.e != Long.MIN_VALUE) {
            a = new d(a, wVar.a.e);
        }
        this.a = a;
    }

    public final long a() {
        return this.n;
    }

    public final long b() {
        return this.g.b + this.n;
    }

    public final boolean c() {
        return this.e && (!this.f || this.a.d() == Long.MIN_VALUE);
    }

    public final long d() {
        if (!this.e) {
            return this.g.b;
        }
        long d = this.f ? this.a.d() : Long.MIN_VALUE;
        return d == Long.MIN_VALUE ? this.g.d : d;
    }

    public final long e() {
        return !this.e ? 0 : this.a.e();
    }

    public final void a(float f) throws h {
        this.e = true;
        this.i = this.a.b();
        b(f);
        long d = d(this.g.b);
        this.n += this.g.b - d;
        w wVar = this.g;
        this.g = new w(wVar.a, d, wVar.c, wVar.d, wVar.e, wVar.f);
    }

    public final boolean b(float f) throws h {
        o a = this.l.a(this.k, this.i);
        int i = 0;
        if (a.a(this.o)) {
            return false;
        }
        this.j = a;
        com.google.android.exoplayer2.trackselection.j[] a2 = this.j.c.a();
        int length = a2.length;
        while (i < length) {
            com.google.android.exoplayer2.trackselection.j jVar = a2[i];
            if (jVar != null) {
                jVar.onPlaybackSpeed(f);
            }
            i++;
        }
        return true;
    }

    public final long d(long j) {
        return a(j, false, new boolean[this.k.length]);
    }

    public final long a(long j, boolean z, boolean[] zArr) {
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= this.j.a) {
                break;
            }
            boolean[] zArr2 = this.d;
            if (z || !this.j.a(this.o, i)) {
                z2 = false;
            }
            zArr2[i] = z2;
            i++;
        }
        a(this.c);
        a(this.j);
        com.google.android.exoplayer2.trackselection.l lVar = this.j.c;
        j = this.a.a(lVar.a(), this.d, this.c, zArr, j);
        b(this.c);
        this.f = false;
        for (int i2 = 0; i2 < this.c.length; i2++) {
            if (this.c[i2] != null) {
                bcz.b(this.j.a(i2));
                if (this.k[i2].a() != 6) {
                    this.f = true;
                }
            } else {
                bcz.b(lVar.a(i2) == null);
            }
        }
        return j;
    }

    public final void f() {
        a(null);
        try {
            if (this.g.a.e != Long.MIN_VALUE) {
                this.m.a(((d) this.a).a);
            } else {
                this.m.a(this.a);
            }
        } catch (Throwable e) {
            bdk.b("MediaPeriodHolder", "Period release failed.", e);
        }
    }

    private void a(o oVar) {
        if (this.o != null) {
            c(this.o);
        }
        this.o = oVar;
        if (this.o != null) {
            b(this.o);
        }
    }

    private static void b(o oVar) {
        for (int i = 0; i < oVar.a; i++) {
            boolean a = oVar.a(i);
            com.google.android.exoplayer2.trackselection.j a2 = oVar.c.a(i);
            if (a && a2 != null) {
                a2.enable();
            }
        }
    }

    private static void c(o oVar) {
        for (int i = 0; i < oVar.a; i++) {
            boolean a = oVar.a(i);
            com.google.android.exoplayer2.trackselection.j a2 = oVar.c.a(i);
            if (a && a2 != null) {
                a2.disable();
            }
        }
    }

    private void a(y[] yVarArr) {
        for (int i = 0; i < this.k.length; i++) {
            if (this.k[i].a() == 6) {
                yVarArr[i] = null;
            }
        }
    }

    private void b(y[] yVarArr) {
        int i = 0;
        while (i < this.k.length) {
            if (this.k[i].a() == 6 && this.j.a(i)) {
                yVarArr[i] = new i();
            }
            i++;
        }
    }

    public final long a(long j) {
        return j + this.n;
    }

    public final long b(long j) {
        return j - this.n;
    }

    public final void c(long j) {
        this.a.b(j - this.n);
    }
}
