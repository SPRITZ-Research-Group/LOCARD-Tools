package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fi extends d<fi> {
    private static volatile fi[] g;
    public Integer c;
    public fn d;
    public fn e;
    public Boolean f;

    public fi() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fi[] e() {
        if (g == null) {
            synchronized (h.b) {
                if (g == null) {
                    g = new fi[0];
                }
            }
        }
        return g;
    }

    protected final int a() {
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c.intValue());
        }
        if (this.d != null) {
            a += b.b(2, this.d);
        }
        if (this.e != null) {
            a += b.b(3, this.e);
        }
        if (this.f == null) {
            return a;
        }
        this.f.booleanValue();
        return a + (b.c(32) + 1);
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.c = Integer.valueOf(aVar.d());
                    continue;
                case 18:
                    if (this.d == null) {
                        this.d = new fn();
                    }
                    aVar.a(this.d);
                    continue;
                case 26:
                    if (this.e == null) {
                        this.e = new fn();
                    }
                    aVar.a(this.e);
                    continue;
                case 32:
                    this.f = Boolean.valueOf(aVar.b());
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
        if (this.c != null) {
            bVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            bVar.a(2, this.d);
        }
        if (this.e != null) {
            bVar.a(3, this.e);
        }
        if (this.f != null) {
            bVar.a(4, this.f.booleanValue());
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fi)) {
            return false;
        }
        fi fiVar = (fi) obj;
        if (this.c == null) {
            if (fiVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fiVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fiVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fiVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fiVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fiVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fiVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fiVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fiVar.a == null || fiVar.a.b() : this.a.equals(fiVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        fn fnVar = this.d;
        hashCode = (fnVar == null ? 0 : fnVar.hashCode()) + (hashCode * 31);
        fnVar = this.e;
        hashCode = ((this.f == null ? 0 : this.f.hashCode()) + (((fnVar == null ? 0 : fnVar.hashCode()) + (hashCode * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
