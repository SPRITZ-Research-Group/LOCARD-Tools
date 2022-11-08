package com.google.android.gms.internal.clearcut;

import java.util.Arrays;

public final class dz extends dm<dz> implements Cloneable {
    private byte[] c;
    private String d;
    private byte[][] e;
    private boolean f;

    public dz() {
        this.c = dt.e;
        this.d = "";
        this.e = dt.d;
        this.f = false;
        this.a = null;
        this.b = -1;
    }

    private final dz c() {
        try {
            dz dzVar = (dz) super.a();
            if (this.e != null && this.e.length > 0) {
                dzVar.e = (byte[][]) this.e.clone();
            }
            return dzVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ dm a() throws CloneNotSupportedException {
        return (dz) clone();
    }

    public final /* synthetic */ dr b() throws CloneNotSupportedException {
        return (dz) clone();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dz)) {
            return false;
        }
        dz dzVar = (dz) obj;
        if (!Arrays.equals(this.c, dzVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (dzVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(dzVar.d)) {
            return false;
        }
        return !dq.a(this.e, dzVar.e) ? false : (this.a == null || this.a.a()) ? dzVar.a == null || dzVar.a.a() : this.a.equals(dzVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((this.d == null ? 0 : this.d.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.c)) * 31)) * 31) + dq.a(this.e)) * 31) + 1237) * 31;
        if (!(this.a == null || this.a.a())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
