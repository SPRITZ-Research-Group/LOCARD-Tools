package com.google.android.gms.internal.clearcut;

public class at {
    private static final v a = v.a();
    private h b;
    private volatile bk c;
    private volatile h d;

    private final bk b(bk bkVar) {
        if (this.c == null) {
            synchronized (this) {
                if (this.c != null) {
                } else {
                    try {
                        this.c = bkVar;
                        this.d = h.a;
                    } catch (an e) {
                        this.c = bkVar;
                        this.d = h.a;
                    }
                }
            }
        }
        return this.c;
    }

    public final bk a(bk bkVar) {
        bk bkVar2 = this.c;
        this.b = null;
        this.d = null;
        this.c = bkVar;
        return bkVar2;
    }

    public final int b() {
        return this.d != null ? this.d.a() : this.c != null ? this.c.d() : 0;
    }

    public final h c() {
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            h hVar;
            if (this.d != null) {
                hVar = this.d;
                return hVar;
            }
            if (this.c == null) {
                this.d = h.a;
            } else {
                this.d = this.c.a();
            }
            hVar = this.d;
            return hVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof at)) {
            return false;
        }
        at atVar = (at) obj;
        bk bkVar = this.c;
        bk bkVar2 = atVar.c;
        return (bkVar == null && bkVar2 == null) ? c().equals(atVar.c()) : (bkVar == null || bkVar2 == null) ? bkVar != null ? bkVar.equals(atVar.b(bkVar.f())) : b(bkVar2.f()).equals(bkVar2) : bkVar.equals(bkVar2);
    }

    public int hashCode() {
        return 1;
    }
}
