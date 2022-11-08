package com.google.android.gms.internal.clearcut;

public final class dy extends dm<dy> implements Cloneable {
    private String[] c;
    private String[] d;
    private int[] e;
    private long[] f;
    private long[] g;

    public dy() {
        this.c = dt.c;
        this.d = dt.c;
        this.e = dt.a;
        this.f = dt.b;
        this.g = dt.b;
        this.a = null;
        this.b = -1;
    }

    private final dy c() {
        try {
            dy dyVar = (dy) super.a();
            if (this.c != null && this.c.length > 0) {
                dyVar.c = (String[]) this.c.clone();
            }
            if (this.d != null && this.d.length > 0) {
                dyVar.d = (String[]) this.d.clone();
            }
            if (this.e != null && this.e.length > 0) {
                dyVar.e = (int[]) this.e.clone();
            }
            if (this.f != null && this.f.length > 0) {
                dyVar.f = (long[]) this.f.clone();
            }
            if (this.g != null && this.g.length > 0) {
                dyVar.g = (long[]) this.g.clone();
            }
            return dyVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ dm a() throws CloneNotSupportedException {
        return (dy) clone();
    }

    public final /* synthetic */ dr b() throws CloneNotSupportedException {
        return (dy) clone();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dy)) {
            return false;
        }
        dy dyVar = (dy) obj;
        return !dq.a(this.c, dyVar.c) ? false : !dq.a(this.d, dyVar.d) ? false : !dq.a(this.e, dyVar.e) ? false : !dq.a(this.f, dyVar.f) ? false : !dq.a(this.g, dyVar.g) ? false : (this.a == null || this.a.a()) ? dyVar.a == null || dyVar.a.a() : this.a.equals(dyVar.a);
    }

    public final int hashCode() {
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + dq.a(this.c)) * 31) + dq.a(this.d)) * 31) + dq.a(this.e)) * 31) + dq.a(this.f)) * 31) + dq.a(this.g)) * 31;
        int hashCode2 = (this.a == null || this.a.a()) ? 0 : this.a.hashCode();
        return hashCode2 + hashCode;
    }
}
