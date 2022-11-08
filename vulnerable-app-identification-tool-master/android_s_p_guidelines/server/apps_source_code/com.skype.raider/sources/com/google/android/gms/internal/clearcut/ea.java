package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.du.a;
import com.google.android.gms.internal.clearcut.du.b;
import java.util.Arrays;

public final class ea extends dm<ea> implements Cloneable {
    private b A;
    public long c;
    public long d;
    public int e;
    public byte[] f;
    public long g;
    public byte[] h;
    public boolean i;
    private long j;
    private String k;
    private String l;
    private int m;
    private boolean n;
    private eb[] o;
    private byte[] p;
    private a q;
    private String r;
    private String s;
    private dy t;
    private String u;
    private dz v;
    private String w;
    private int x;
    private int[] y;
    private long z;

    public ea() {
        this.c = 0;
        this.d = 0;
        this.j = 0;
        this.k = "";
        this.e = 0;
        this.l = "";
        this.m = 0;
        this.n = false;
        this.o = eb.c();
        this.p = dt.e;
        this.q = null;
        this.f = dt.e;
        this.r = "";
        this.s = "";
        this.t = null;
        this.u = "";
        this.g = 180000;
        this.v = null;
        this.h = dt.e;
        this.w = "";
        this.x = 0;
        this.y = dt.a;
        this.z = 0;
        this.A = null;
        this.i = false;
        this.a = null;
        this.b = -1;
    }

    private final ea c() {
        try {
            ea eaVar = (ea) super.a();
            if (this.o != null && this.o.length > 0) {
                eaVar.o = new eb[this.o.length];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.o.length) {
                        break;
                    }
                    if (this.o[i2] != null) {
                        eaVar.o[i2] = (eb) this.o[i2].clone();
                    }
                    i = i2 + 1;
                }
            }
            if (this.q != null) {
                eaVar.q = this.q;
            }
            if (this.t != null) {
                eaVar.t = (dy) this.t.clone();
            }
            if (this.v != null) {
                eaVar.v = (dz) this.v.clone();
            }
            if (this.y != null && this.y.length > 0) {
                eaVar.y = (int[]) this.y.clone();
            }
            if (this.A != null) {
                eaVar.A = this.A;
            }
            return eaVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ dm a() throws CloneNotSupportedException {
        return (ea) clone();
    }

    public final /* synthetic */ dr b() throws CloneNotSupportedException {
        return (ea) clone();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ea)) {
            return false;
        }
        ea eaVar = (ea) obj;
        if (this.c != eaVar.c) {
            return false;
        }
        if (this.d != eaVar.d) {
            return false;
        }
        if (0 != 0) {
            return false;
        }
        if (this.k == null) {
            if (eaVar.k != null) {
                return false;
            }
        } else if (!this.k.equals(eaVar.k)) {
            return false;
        }
        if (this.e != eaVar.e) {
            return false;
        }
        if (this.l == null) {
            if (eaVar.l != null) {
                return false;
            }
        } else if (!this.l.equals(eaVar.l)) {
            return false;
        }
        if (!dq.a(this.o, eaVar.o)) {
            return false;
        }
        if (!Arrays.equals(this.p, eaVar.p)) {
            return false;
        }
        if (this.q == null) {
            if (eaVar.q != null) {
                return false;
            }
        } else if (!this.q.equals(eaVar.q)) {
            return false;
        }
        if (!Arrays.equals(this.f, eaVar.f)) {
            return false;
        }
        if (this.r == null) {
            if (eaVar.r != null) {
                return false;
            }
        } else if (!this.r.equals(eaVar.r)) {
            return false;
        }
        if (this.s == null) {
            if (eaVar.s != null) {
                return false;
            }
        } else if (!this.s.equals(eaVar.s)) {
            return false;
        }
        if (this.t == null) {
            if (eaVar.t != null) {
                return false;
            }
        } else if (!this.t.equals(eaVar.t)) {
            return false;
        }
        if (this.u == null) {
            if (eaVar.u != null) {
                return false;
            }
        } else if (!this.u.equals(eaVar.u)) {
            return false;
        }
        if (this.g != eaVar.g) {
            return false;
        }
        if (this.v == null) {
            if (eaVar.v != null) {
                return false;
            }
        } else if (!this.v.equals(eaVar.v)) {
            return false;
        }
        if (!Arrays.equals(this.h, eaVar.h)) {
            return false;
        }
        if (this.w == null) {
            if (eaVar.w != null) {
                return false;
            }
        } else if (!this.w.equals(eaVar.w)) {
            return false;
        }
        if (!dq.a(this.y, eaVar.y)) {
            return false;
        }
        if (0 != 0) {
            return false;
        }
        if (this.A == null) {
            if (eaVar.A != null) {
                return false;
            }
        } else if (!this.A.equals(eaVar.A)) {
            return false;
        }
        return this.i != eaVar.i ? false : (this.a == null || this.a.a()) ? eaVar.a == null || eaVar.a.a() : this.a.equals(eaVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.l == null ? 0 : this.l.hashCode()) + (((((this.k == null ? 0 : this.k.hashCode()) + (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) * 31)) * 31) + this.e) * 31)) * 31) * 31) + 1237) * 31) + dq.a(this.o)) * 31) + Arrays.hashCode(this.p);
        ah ahVar = this.q;
        hashCode = (this.s == null ? 0 : this.s.hashCode()) + (((this.r == null ? 0 : this.r.hashCode()) + (((((ahVar == null ? 0 : ahVar.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.f)) * 31)) * 31);
        dy dyVar = this.t;
        hashCode = (((this.u == null ? 0 : this.u.hashCode()) + (((dyVar == null ? 0 : dyVar.hashCode()) + (hashCode * 31)) * 31)) * 31) + ((int) (this.g ^ (this.g >>> 32)));
        dz dzVar = this.v;
        hashCode = (((((this.w == null ? 0 : this.w.hashCode()) + (((((dzVar == null ? 0 : dzVar.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.h)) * 31)) * 31) * 31) + dq.a(this.y)) * 31;
        ahVar = this.A;
        hashCode = ((this.i ? 1231 : 1237) + (((ahVar == null ? 0 : ahVar.hashCode()) + (hashCode * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.a())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
