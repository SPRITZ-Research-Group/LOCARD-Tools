package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.source.j;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.trackselection.m;
import defpackage.bbf;
import defpackage.bcz;

final class x {
    private final ap a = new ap();
    private final aq b = new aq();
    private long c;
    private ao d = ao.a;
    private int e;
    private boolean f;
    private v g;
    private v h;
    private v i;
    private int j;
    private Object k;
    private long l;

    public final void a(ao aoVar) {
        this.d = aoVar;
    }

    public final boolean a(int i) {
        this.e = i;
        return i();
    }

    public final boolean a(boolean z) {
        this.f = z;
        return i();
    }

    public final boolean a(j jVar) {
        return this.i != null && this.i.a == jVar;
    }

    public final boolean a() {
        return this.i == null || (!this.i.g.f && this.i.c() && this.i.g.d != -9223372036854775807L && this.j < 100);
    }

    public final w a(long j, z zVar) {
        if (this.i == null) {
            return a(zVar.c, zVar.e, zVar.d);
        }
        return a(this.i, j);
    }

    public final j a(ah[] ahVarArr, m mVar, bbf bbf, l lVar, w wVar) {
        long j;
        if (this.i == null) {
            j = wVar.b;
        } else {
            j = this.i.a() + this.i.g.d;
        }
        v vVar = new v(ahVarArr, j, mVar, bbf, lVar, wVar);
        if (this.i != null) {
            bcz.b(f());
            this.i.h = vVar;
        }
        this.k = null;
        this.i = vVar;
        this.j++;
        return vVar.a;
    }

    public final v b() {
        return this.i;
    }

    public final v c() {
        return this.g;
    }

    public final v d() {
        return this.h;
    }

    public final v e() {
        return f() ? this.g : this.i;
    }

    public final boolean f() {
        return this.g != null;
    }

    public final v g() {
        boolean z = (this.h == null || this.h.h == null) ? false : true;
        bcz.b(z);
        this.h = this.h.h;
        return this.h;
    }

    public final v h() {
        if (this.g != null) {
            if (this.g == this.h) {
                this.h = this.g.h;
            }
            this.g.f();
            this.j--;
            if (this.j == 0) {
                this.i = null;
                this.k = this.g.b;
                this.l = this.g.g.a.d;
            }
            this.g = this.g.h;
        } else {
            this.g = this.i;
            this.h = this.i;
        }
        return this.g;
    }

    public final boolean a(v vVar) {
        boolean z = false;
        bcz.b(vVar != null);
        this.i = vVar;
        while (vVar.h != null) {
            vVar = vVar.h;
            if (vVar == this.h) {
                this.h = this.g;
                z = true;
            }
            vVar.f();
            this.j--;
        }
        this.i.h = null;
        return z;
    }

    public final void b(boolean z) {
        v e = e();
        if (e != null) {
            this.k = z ? e.b : null;
            this.l = e.g.a.d;
            e.f();
            a(e);
        } else if (!z) {
            this.k = null;
        }
        this.g = null;
        this.i = null;
        this.h = null;
        this.j = 0;
    }

    public final boolean a(com.google.android.exoplayer2.source.m mVar, long j) {
        int a = this.d.a(mVar.a);
        v e = e();
        int i = a;
        v vVar = null;
        while (true) {
            v vVar2 = e;
            if (vVar2 == null) {
                return true;
            }
            if (vVar == null) {
                vVar2.g = a(vVar2.g);
            } else if (i != -1 && vVar2.b.equals(this.d.a(i))) {
                w a2 = a(vVar, j);
                if (a2 != null) {
                    vVar2.g = a(vVar2.g);
                    w wVar = vVar2.g;
                    Object obj = (wVar.b == a2.b && wVar.a.equals(a2.a)) ? 1 : null;
                    if (obj == null) {
                        if (a(vVar)) {
                            return false;
                        }
                        return true;
                    }
                } else if (a(vVar)) {
                    return false;
                } else {
                    return true;
                }
            }
            if (vVar2.g.e) {
                i = this.d.a(i, this.a, this.b, this.e);
            }
            vVar = vVar2.h;
            e = vVar2;
            vVar2 = vVar;
        }
        if (a(vVar)) {
            return false;
        }
        return true;
    }

    public final w a(w wVar) {
        long c;
        boolean a = a(wVar.a);
        boolean a2 = a(wVar.a, a);
        this.d.a(wVar.a.a, this.a);
        if (wVar.a.a()) {
            c = this.a.c(wVar.a.b, wVar.a.c);
        } else if (wVar.a.e == Long.MIN_VALUE) {
            c = this.a.d;
        } else {
            c = wVar.a.e;
        }
        return new w(wVar.a, wVar.b, wVar.c, c, a, a2);
    }

    private com.google.android.exoplayer2.source.m a(Object obj, long j, long j2) {
        this.d.a(obj, this.a);
        int a = this.a.a(j);
        if (a == -1) {
            int b = this.a.b(j);
            if (b == -1) {
                j = Long.MIN_VALUE;
            } else {
                j = this.a.a(b);
            }
            return new com.google.android.exoplayer2.source.m(obj, j2, j);
        }
        return new com.google.android.exoplayer2.source.m(obj, a, this.a.b(a), j2);
    }

    private boolean i() {
        v e = e();
        if (e == null) {
            return true;
        }
        boolean a;
        int a2 = this.d.a(e.b);
        while (true) {
            a2 = this.d.a(a2, this.a, this.b, this.e);
            while (e.h != null && !e.g.e) {
                e = e.h;
            }
            if (a2 == -1 || e.h == null || this.d.a(e.h.b) != a2) {
                a = a(e);
                e.g = a(e.g);
            } else {
                e = e.h;
            }
        }
        a = a(e);
        e.g = a(e.g);
        if (a && f()) {
            return false;
        }
        return true;
    }

    private w a(v vVar, long j) {
        x xVar = this;
        v vVar2 = vVar;
        w wVar = vVar2.g;
        long a = (vVar.a() + wVar.d) - j;
        long j2 = 0;
        int a2;
        long j3;
        Pair a3;
        long j4;
        if (wVar.e) {
            a2 = xVar.d.a(xVar.d.a(wVar.a.a), xVar.a, xVar.b, xVar.e);
            if (a2 == -1) {
                return null;
            }
            Object obj;
            int i = xVar.d.a(a2, xVar.a, true).c;
            Object obj2 = xVar.a.b;
            j3 = wVar.a.d;
            if (xVar.d.a(i, xVar.b, 0).f == a2) {
                a3 = xVar.d.a(xVar.b, xVar.a, i, -9223372036854775807L, Math.max(0, a));
                if (a3 == null) {
                    return null;
                }
                long j5;
                Object obj3 = a3.first;
                long longValue = ((Long) a3.second).longValue();
                if (vVar2.h == null || !vVar2.h.b.equals(obj3)) {
                    j5 = xVar.c;
                    xVar.c = 1 + j5;
                } else {
                    j5 = vVar2.h.g.a.d;
                }
                j2 = longValue;
                j4 = j5;
                obj = obj3;
            } else {
                obj = obj2;
                j4 = j3;
            }
            a = j2;
            return a(a(obj, a, j4), a, j2);
        }
        com.google.android.exoplayer2.source.m mVar = wVar.a;
        xVar.d.a(mVar.a, xVar.a);
        int a4;
        int b;
        if (mVar.a()) {
            a2 = mVar.b;
            int d = xVar.a.d(a2);
            if (d == -1) {
                return null;
            }
            int a5 = xVar.a.a(a2, mVar.c);
            if (a5 >= d) {
                j3 = wVar.c;
                if (xVar.a.b() == 1 && xVar.a.a(0) == 0) {
                    a3 = xVar.d.a(xVar.b, xVar.a, xVar.a.c, -9223372036854775807L, Math.max(0, a));
                    if (a3 == null) {
                        return null;
                    }
                    a = ((Long) a3.second).longValue();
                } else {
                    a = j3;
                }
                return b(mVar.a, a, mVar.d);
            } else if (!xVar.a.b(a2, a5)) {
                return null;
            } else {
                return a(mVar.a, a2, a5, wVar.c, mVar.d);
            }
        } else if (wVar.a.e != Long.MIN_VALUE) {
            a4 = xVar.a.a(wVar.a.e);
            if (a4 == -1) {
                return b(mVar.a, wVar.a.e, mVar.d);
            }
            b = xVar.a.b(a4);
            if (!xVar.a.b(a4, b)) {
                return null;
            }
            return a(mVar.a, a4, b, wVar.a.e, mVar.d);
        } else {
            int b2 = xVar.a.b();
            if (b2 == 0) {
                return null;
            }
            a4 = b2 - 1;
            if (xVar.a.a(a4) != Long.MIN_VALUE || xVar.a.c(a4)) {
                return null;
            }
            b = xVar.a.b(a4);
            if (!xVar.a.b(a4, b)) {
                return null;
            }
            j4 = xVar.a.d;
            return a(mVar.a, a4, b, j4, mVar.d);
        }
    }

    private w a(com.google.android.exoplayer2.source.m mVar, long j, long j2) {
        this.d.a(mVar.a, this.a);
        if (!mVar.a()) {
            return b(mVar.a, j2, mVar.d);
        } else if (!this.a.b(mVar.b, mVar.c)) {
            return null;
        } else {
            return a(mVar.a, mVar.b, mVar.c, j, mVar.d);
        }
    }

    private w a(Object obj, int i, int i2, long j, long j2) {
        com.google.android.exoplayer2.source.m mVar = new com.google.android.exoplayer2.source.m(obj, i, i2, j2);
        boolean a = a(mVar);
        boolean a2 = a(mVar, a);
        return new w(mVar, i2 == this.a.b(i) ? r0.a.c() : 0, j, this.d.a(mVar.a, this.a).c(mVar.b, mVar.c), a, a2);
    }

    private w b(Object obj, long j, long j2) {
        long j3;
        long j4;
        int b = this.a.b(j);
        if (b == -1) {
            j3 = Long.MIN_VALUE;
        } else {
            j3 = r0.a.a(b);
        }
        com.google.android.exoplayer2.source.m mVar = new com.google.android.exoplayer2.source.m(obj, j2, j3);
        r0.d.a(mVar.a, r0.a);
        boolean a = a(mVar);
        boolean a2 = a(mVar, a);
        if (j3 == Long.MIN_VALUE) {
            j4 = r0.a.d;
        } else {
            j4 = j3;
        }
        return new w(mVar, j, -9223372036854775807L, j4, a, a2);
    }

    private boolean a(com.google.android.exoplayer2.source.m mVar) {
        int b = this.d.a(mVar.a, this.a).b();
        if (b == 0) {
            return true;
        }
        b--;
        boolean a = mVar.a();
        if (this.a.a(b) == Long.MIN_VALUE) {
            int d = this.a.d(b);
            if (d == -1) {
                return false;
            }
            Object obj = (a && mVar.b == b && mVar.c == d - 1) ? 1 : null;
            if (obj != null || (!a && this.a.b(b) == d)) {
                return true;
            }
            return false;
        } else if (a || mVar.e != Long.MIN_VALUE) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(com.google.android.exoplayer2.source.m mVar, boolean z) {
        int a = this.d.a(mVar.a);
        if (!this.d.a(this.d.a(a, this.a, false).c, this.b, 0).e && this.d.b(a, this.a, this.b, this.e) && z) {
            return true;
        }
        return false;
    }

    public final com.google.android.exoplayer2.source.m a(Object obj, long j) {
        long j2;
        v e;
        int i = this.d.a(obj, this.a).c;
        if (this.k != null) {
            int a = this.d.a(this.k);
            if (a != -1 && this.d.a(a, this.a, false).c == i) {
                j2 = this.l;
                return a(obj, j, j2);
            }
        }
        for (e = e(); e != null; e = e.h) {
            if (e.b.equals(obj)) {
                j2 = e.g.a.d;
                break;
            }
        }
        for (e = e(); e != null; e = e.h) {
            int a2 = this.d.a(e.b);
            if (a2 != -1 && this.d.a(a2, this.a, false).c == i) {
                j2 = e.g.a.d;
                break;
            }
        }
        j2 = this.c;
        this.c = 1 + j2;
        return a(obj, j, j2);
    }
}
