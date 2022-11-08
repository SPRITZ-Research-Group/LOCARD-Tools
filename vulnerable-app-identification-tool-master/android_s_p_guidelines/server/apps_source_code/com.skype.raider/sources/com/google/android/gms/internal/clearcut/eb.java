package com.google.android.gms.internal.clearcut;

public final class eb extends dm<eb> implements Cloneable {
    private static volatile eb[] c;
    private String d;
    private String e;

    public eb() {
        this.d = "";
        this.e = "";
        this.a = null;
        this.b = -1;
    }

    public static eb[] c() {
        if (c == null) {
            synchronized (dq.a) {
                if (c == null) {
                    c = new eb[0];
                }
            }
        }
        return c;
    }

    private final eb d() {
        try {
            return (eb) super.a();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ dm a() throws CloneNotSupportedException {
        return (eb) clone();
    }

    public final /* synthetic */ dr b() throws CloneNotSupportedException {
        return (eb) clone();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof eb)) {
            return false;
        }
        eb ebVar = (eb) obj;
        if (this.d == null) {
            if (ebVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(ebVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (ebVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(ebVar.e)) {
            return false;
        }
        return (this.a == null || this.a.a()) ? ebVar.a == null || ebVar.a.a() : this.a.equals(ebVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.a())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
