package com.google.android.gms.internal.measurement;

import com.skype.Defines;
import java.io.IOException;

public final class fm extends d<fm> {
    private static volatile fm[] K;
    public String A;
    public Boolean B;
    public fi[] C;
    public String D;
    public Integer E;
    public String F;
    public Long G;
    public Long H;
    public String I;
    public Integer J;
    private Integer L;
    private Integer M;
    private String N;
    public Integer c;
    public fj[] d;
    public fo[] e;
    public Long f;
    public Long g;
    public Long h;
    public Long i;
    public Long j;
    public String k;
    public String l;
    public String m;
    public String n;
    public Integer o;
    public String p;
    public String q;
    public String r;
    public Long s;
    public Long t;
    public String u;
    public Boolean v;
    public String w;
    public Long x;
    public Integer y;
    public String z;

    public fm() {
        this.c = null;
        this.d = fj.e();
        this.e = fo.e();
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = fi.e();
        this.D = null;
        this.E = null;
        this.L = null;
        this.M = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.N = null;
        this.J = null;
        this.a = null;
        this.b = -1;
    }

    public static fm[] e() {
        if (K == null) {
            synchronized (h.b) {
                if (K == null) {
                    K = new fm[0];
                }
            }
        }
        return K;
    }

    protected final int a() {
        int i;
        int i2 = 0;
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            i = a;
            for (j jVar : this.d) {
                if (jVar != null) {
                    i += b.b(2, jVar);
                }
            }
            a = i;
        }
        if (this.e != null && this.e.length > 0) {
            i = a;
            for (j jVar2 : this.e) {
                if (jVar2 != null) {
                    i += b.b(3, jVar2);
                }
            }
            a = i;
        }
        if (this.f != null) {
            a += b.c(4, this.f.longValue());
        }
        if (this.g != null) {
            a += b.c(5, this.g.longValue());
        }
        if (this.h != null) {
            a += b.c(6, this.h.longValue());
        }
        if (this.j != null) {
            a += b.c(7, this.j.longValue());
        }
        if (this.k != null) {
            a += b.b(8, this.k);
        }
        if (this.l != null) {
            a += b.b(9, this.l);
        }
        if (this.m != null) {
            a += b.b(10, this.m);
        }
        if (this.n != null) {
            a += b.b(11, this.n);
        }
        if (this.o != null) {
            a += b.b(12, this.o.intValue());
        }
        if (this.p != null) {
            a += b.b(13, this.p);
        }
        if (this.q != null) {
            a += b.b(14, this.q);
        }
        if (this.r != null) {
            a += b.b(16, this.r);
        }
        if (this.s != null) {
            a += b.c(17, this.s.longValue());
        }
        if (this.t != null) {
            a += b.c(18, this.t.longValue());
        }
        if (this.u != null) {
            a += b.b(19, this.u);
        }
        if (this.v != null) {
            this.v.booleanValue();
            a += b.c(160) + 1;
        }
        if (this.w != null) {
            a += b.b(21, this.w);
        }
        if (this.x != null) {
            a += b.c(22, this.x.longValue());
        }
        if (this.y != null) {
            a += b.b(23, this.y.intValue());
        }
        if (this.z != null) {
            a += b.b(24, this.z);
        }
        if (this.A != null) {
            a += b.b(25, this.A);
        }
        if (this.i != null) {
            a += b.c(26, this.i.longValue());
        }
        if (this.B != null) {
            this.B.booleanValue();
            a += b.c(224) + 1;
        }
        if (this.C != null && this.C.length > 0) {
            while (i2 < this.C.length) {
                j jVar3 = this.C[i2];
                if (jVar3 != null) {
                    a += b.b(29, jVar3);
                }
                i2++;
            }
        }
        if (this.D != null) {
            a += b.b(30, this.D);
        }
        if (this.E != null) {
            a += b.b(31, this.E.intValue());
        }
        if (this.L != null) {
            a += b.b(32, this.L.intValue());
        }
        if (this.M != null) {
            a += b.b(33, this.M.intValue());
        }
        if (this.F != null) {
            a += b.b(34, this.F);
        }
        if (this.G != null) {
            a += b.c(35, this.G.longValue());
        }
        if (this.H != null) {
            a += b.c(36, this.H.longValue());
        }
        if (this.I != null) {
            a += b.b(37, this.I);
        }
        if (this.N != null) {
            a += b.b(38, this.N);
        }
        return this.J != null ? a + b.b(39, this.J.intValue()) : a;
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            int a2;
            Object obj;
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.c = Integer.valueOf(aVar.d());
                    continue;
                case 18:
                    a2 = m.a(aVar, 18);
                    a = this.d == null ? 0 : this.d.length;
                    obj = new fj[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.d, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fj();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fj();
                    aVar.a(obj[a]);
                    this.d = obj;
                    continue;
                case 26:
                    a2 = m.a(aVar, 26);
                    a = this.e == null ? 0 : this.e.length;
                    obj = new fo[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.e, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fo();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fo();
                    aVar.a(obj[a]);
                    this.e = obj;
                    continue;
                case 32:
                    this.f = Long.valueOf(aVar.e());
                    continue;
                case 40:
                    this.g = Long.valueOf(aVar.e());
                    continue;
                case 48:
                    this.h = Long.valueOf(aVar.e());
                    continue;
                case 56:
                    this.j = Long.valueOf(aVar.e());
                    continue;
                case 66:
                    this.k = aVar.c();
                    continue;
                case 74:
                    this.l = aVar.c();
                    continue;
                case 82:
                    this.m = aVar.c();
                    continue;
                case 90:
                    this.n = aVar.c();
                    continue;
                case 96:
                    this.o = Integer.valueOf(aVar.d());
                    continue;
                case 106:
                    this.p = aVar.c();
                    continue;
                case 114:
                    this.q = aVar.c();
                    continue;
                case 130:
                    this.r = aVar.c();
                    continue;
                case 136:
                    this.s = Long.valueOf(aVar.e());
                    continue;
                case 144:
                    this.t = Long.valueOf(aVar.e());
                    continue;
                case 154:
                    this.u = aVar.c();
                    continue;
                case 160:
                    this.v = Boolean.valueOf(aVar.b());
                    continue;
                case 170:
                    this.w = aVar.c();
                    continue;
                case 176:
                    this.x = Long.valueOf(aVar.e());
                    continue;
                case 184:
                    this.y = Integer.valueOf(aVar.d());
                    continue;
                case 194:
                    this.z = aVar.c();
                    continue;
                case 202:
                    this.A = aVar.c();
                    continue;
                case 208:
                    this.i = Long.valueOf(aVar.e());
                    continue;
                case 224:
                    this.B = Boolean.valueOf(aVar.b());
                    continue;
                case 234:
                    a2 = m.a(aVar, 234);
                    a = this.C == null ? 0 : this.C.length;
                    obj = new fi[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.C, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fi();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fi();
                    aVar.a(obj[a]);
                    this.C = obj;
                    continue;
                case 242:
                    this.D = aVar.c();
                    continue;
                case 248:
                    this.E = Integer.valueOf(aVar.d());
                    continue;
                case Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE /*256*/:
                    this.L = Integer.valueOf(aVar.d());
                    continue;
                case 264:
                    this.M = Integer.valueOf(aVar.d());
                    continue;
                case 274:
                    this.F = aVar.c();
                    continue;
                case 280:
                    this.G = Long.valueOf(aVar.e());
                    continue;
                case 288:
                    this.H = Long.valueOf(aVar.e());
                    continue;
                case 298:
                    this.I = aVar.c();
                    continue;
                case 306:
                    this.N = aVar.c();
                    continue;
                case 312:
                    this.J = Integer.valueOf(aVar.d());
                    continue;
                default:
                    if (!super.a(aVar, a)) {
                        break;
                    }
                    continue;
            }
        }
        return this;
    }

    public final void a(b bVar) throws IOException {
        int i = 0;
        if (this.c != null) {
            bVar.a(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            for (j jVar : this.d) {
                if (jVar != null) {
                    bVar.a(2, jVar);
                }
            }
        }
        if (this.e != null && this.e.length > 0) {
            for (j jVar2 : this.e) {
                if (jVar2 != null) {
                    bVar.a(3, jVar2);
                }
            }
        }
        if (this.f != null) {
            bVar.b(4, this.f.longValue());
        }
        if (this.g != null) {
            bVar.b(5, this.g.longValue());
        }
        if (this.h != null) {
            bVar.b(6, this.h.longValue());
        }
        if (this.j != null) {
            bVar.b(7, this.j.longValue());
        }
        if (this.k != null) {
            bVar.a(8, this.k);
        }
        if (this.l != null) {
            bVar.a(9, this.l);
        }
        if (this.m != null) {
            bVar.a(10, this.m);
        }
        if (this.n != null) {
            bVar.a(11, this.n);
        }
        if (this.o != null) {
            bVar.a(12, this.o.intValue());
        }
        if (this.p != null) {
            bVar.a(13, this.p);
        }
        if (this.q != null) {
            bVar.a(14, this.q);
        }
        if (this.r != null) {
            bVar.a(16, this.r);
        }
        if (this.s != null) {
            bVar.b(17, this.s.longValue());
        }
        if (this.t != null) {
            bVar.b(18, this.t.longValue());
        }
        if (this.u != null) {
            bVar.a(19, this.u);
        }
        if (this.v != null) {
            bVar.a(20, this.v.booleanValue());
        }
        if (this.w != null) {
            bVar.a(21, this.w);
        }
        if (this.x != null) {
            bVar.b(22, this.x.longValue());
        }
        if (this.y != null) {
            bVar.a(23, this.y.intValue());
        }
        if (this.z != null) {
            bVar.a(24, this.z);
        }
        if (this.A != null) {
            bVar.a(25, this.A);
        }
        if (this.i != null) {
            bVar.b(26, this.i.longValue());
        }
        if (this.B != null) {
            bVar.a(28, this.B.booleanValue());
        }
        if (this.C != null && this.C.length > 0) {
            while (i < this.C.length) {
                j jVar3 = this.C[i];
                if (jVar3 != null) {
                    bVar.a(29, jVar3);
                }
                i++;
            }
        }
        if (this.D != null) {
            bVar.a(30, this.D);
        }
        if (this.E != null) {
            bVar.a(31, this.E.intValue());
        }
        if (this.L != null) {
            bVar.a(32, this.L.intValue());
        }
        if (this.M != null) {
            bVar.a(33, this.M.intValue());
        }
        if (this.F != null) {
            bVar.a(34, this.F);
        }
        if (this.G != null) {
            bVar.b(35, this.G.longValue());
        }
        if (this.H != null) {
            bVar.b(36, this.H.longValue());
        }
        if (this.I != null) {
            bVar.a(37, this.I);
        }
        if (this.N != null) {
            bVar.a(38, this.N);
        }
        if (this.J != null) {
            bVar.a(39, this.J.intValue());
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fm)) {
            return false;
        }
        fm fmVar = (fm) obj;
        if (this.c == null) {
            if (fmVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fmVar.c)) {
            return false;
        }
        if (!h.a(this.d, fmVar.d)) {
            return false;
        }
        if (!h.a(this.e, fmVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fmVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fmVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (fmVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fmVar.g)) {
            return false;
        }
        if (this.h == null) {
            if (fmVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(fmVar.h)) {
            return false;
        }
        if (this.i == null) {
            if (fmVar.i != null) {
                return false;
            }
        } else if (!this.i.equals(fmVar.i)) {
            return false;
        }
        if (this.j == null) {
            if (fmVar.j != null) {
                return false;
            }
        } else if (!this.j.equals(fmVar.j)) {
            return false;
        }
        if (this.k == null) {
            if (fmVar.k != null) {
                return false;
            }
        } else if (!this.k.equals(fmVar.k)) {
            return false;
        }
        if (this.l == null) {
            if (fmVar.l != null) {
                return false;
            }
        } else if (!this.l.equals(fmVar.l)) {
            return false;
        }
        if (this.m == null) {
            if (fmVar.m != null) {
                return false;
            }
        } else if (!this.m.equals(fmVar.m)) {
            return false;
        }
        if (this.n == null) {
            if (fmVar.n != null) {
                return false;
            }
        } else if (!this.n.equals(fmVar.n)) {
            return false;
        }
        if (this.o == null) {
            if (fmVar.o != null) {
                return false;
            }
        } else if (!this.o.equals(fmVar.o)) {
            return false;
        }
        if (this.p == null) {
            if (fmVar.p != null) {
                return false;
            }
        } else if (!this.p.equals(fmVar.p)) {
            return false;
        }
        if (this.q == null) {
            if (fmVar.q != null) {
                return false;
            }
        } else if (!this.q.equals(fmVar.q)) {
            return false;
        }
        if (this.r == null) {
            if (fmVar.r != null) {
                return false;
            }
        } else if (!this.r.equals(fmVar.r)) {
            return false;
        }
        if (this.s == null) {
            if (fmVar.s != null) {
                return false;
            }
        } else if (!this.s.equals(fmVar.s)) {
            return false;
        }
        if (this.t == null) {
            if (fmVar.t != null) {
                return false;
            }
        } else if (!this.t.equals(fmVar.t)) {
            return false;
        }
        if (this.u == null) {
            if (fmVar.u != null) {
                return false;
            }
        } else if (!this.u.equals(fmVar.u)) {
            return false;
        }
        if (this.v == null) {
            if (fmVar.v != null) {
                return false;
            }
        } else if (!this.v.equals(fmVar.v)) {
            return false;
        }
        if (this.w == null) {
            if (fmVar.w != null) {
                return false;
            }
        } else if (!this.w.equals(fmVar.w)) {
            return false;
        }
        if (this.x == null) {
            if (fmVar.x != null) {
                return false;
            }
        } else if (!this.x.equals(fmVar.x)) {
            return false;
        }
        if (this.y == null) {
            if (fmVar.y != null) {
                return false;
            }
        } else if (!this.y.equals(fmVar.y)) {
            return false;
        }
        if (this.z == null) {
            if (fmVar.z != null) {
                return false;
            }
        } else if (!this.z.equals(fmVar.z)) {
            return false;
        }
        if (this.A == null) {
            if (fmVar.A != null) {
                return false;
            }
        } else if (!this.A.equals(fmVar.A)) {
            return false;
        }
        if (this.B == null) {
            if (fmVar.B != null) {
                return false;
            }
        } else if (!this.B.equals(fmVar.B)) {
            return false;
        }
        if (!h.a(this.C, fmVar.C)) {
            return false;
        }
        if (this.D == null) {
            if (fmVar.D != null) {
                return false;
            }
        } else if (!this.D.equals(fmVar.D)) {
            return false;
        }
        if (this.E == null) {
            if (fmVar.E != null) {
                return false;
            }
        } else if (!this.E.equals(fmVar.E)) {
            return false;
        }
        if (this.L == null) {
            if (fmVar.L != null) {
                return false;
            }
        } else if (!this.L.equals(fmVar.L)) {
            return false;
        }
        if (this.M == null) {
            if (fmVar.M != null) {
                return false;
            }
        } else if (!this.M.equals(fmVar.M)) {
            return false;
        }
        if (this.F == null) {
            if (fmVar.F != null) {
                return false;
            }
        } else if (!this.F.equals(fmVar.F)) {
            return false;
        }
        if (this.G == null) {
            if (fmVar.G != null) {
                return false;
            }
        } else if (!this.G.equals(fmVar.G)) {
            return false;
        }
        if (this.H == null) {
            if (fmVar.H != null) {
                return false;
            }
        } else if (!this.H.equals(fmVar.H)) {
            return false;
        }
        if (this.I == null) {
            if (fmVar.I != null) {
                return false;
            }
        } else if (!this.I.equals(fmVar.I)) {
            return false;
        }
        if (this.N == null) {
            if (fmVar.N != null) {
                return false;
            }
        } else if (!this.N.equals(fmVar.N)) {
            return false;
        }
        if (this.J == null) {
            if (fmVar.J != null) {
                return false;
            }
        } else if (!this.J.equals(fmVar.J)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fmVar.a == null || fmVar.a.b() : this.a.equals(fmVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.J == null ? 0 : this.J.hashCode()) + (((this.N == null ? 0 : this.N.hashCode()) + (((this.I == null ? 0 : this.I.hashCode()) + (((this.H == null ? 0 : this.H.hashCode()) + (((this.G == null ? 0 : this.G.hashCode()) + (((this.F == null ? 0 : this.F.hashCode()) + (((this.M == null ? 0 : this.M.hashCode()) + (((this.L == null ? 0 : this.L.hashCode()) + (((this.E == null ? 0 : this.E.hashCode()) + (((this.D == null ? 0 : this.D.hashCode()) + (((((this.B == null ? 0 : this.B.hashCode()) + (((this.A == null ? 0 : this.A.hashCode()) + (((this.z == null ? 0 : this.z.hashCode()) + (((this.y == null ? 0 : this.y.hashCode()) + (((this.x == null ? 0 : this.x.hashCode()) + (((this.w == null ? 0 : this.w.hashCode()) + (((this.v == null ? 0 : this.v.hashCode()) + (((this.u == null ? 0 : this.u.hashCode()) + (((this.t == null ? 0 : this.t.hashCode()) + (((this.s == null ? 0 : this.s.hashCode()) + (((this.r == null ? 0 : this.r.hashCode()) + (((this.q == null ? 0 : this.q.hashCode()) + (((this.p == null ? 0 : this.p.hashCode()) + (((this.o == null ? 0 : this.o.hashCode()) + (((this.n == null ? 0 : this.n.hashCode()) + (((this.m == null ? 0 : this.m.hashCode()) + (((this.l == null ? 0 : this.l.hashCode()) + (((this.k == null ? 0 : this.k.hashCode()) + (((this.j == null ? 0 : this.j.hashCode()) + (((this.i == null ? 0 : this.i.hashCode()) + (((this.h == null ? 0 : this.h.hashCode()) + (((this.g == null ? 0 : this.g.hashCode()) + (((this.f == null ? 0 : this.f.hashCode()) + (((((((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + h.a(this.d)) * 31) + h.a(this.e)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + h.a(this.C)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
