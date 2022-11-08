package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fk extends d<fk> {
    private static volatile fk[] g;
    public String c;
    public String d;
    public Long e;
    public Double f;
    private Float h;

    public fk() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fk[] e() {
        if (g == null) {
            synchronized (h.b) {
                if (g == null) {
                    g = new fk[0];
                }
            }
        }
        return g;
    }

    protected final int a() {
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c);
        }
        if (this.d != null) {
            a += b.b(2, this.d);
        }
        if (this.e != null) {
            a += b.c(3, this.e.longValue());
        }
        if (this.h != null) {
            this.h.floatValue();
            a += b.c(32) + 4;
        }
        if (this.f == null) {
            return a;
        }
        this.f.doubleValue();
        return a + (b.c(40) + 8);
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    this.c = aVar.c();
                    continue;
                case 18:
                    this.d = aVar.c();
                    continue;
                case 24:
                    this.e = Long.valueOf(aVar.e());
                    continue;
                case 37:
                    this.h = Float.valueOf(Float.intBitsToFloat(aVar.f()));
                    continue;
                case 41:
                    this.f = Double.valueOf(Double.longBitsToDouble(aVar.g()));
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
            bVar.a(1, this.c);
        }
        if (this.d != null) {
            bVar.a(2, this.d);
        }
        if (this.e != null) {
            bVar.b(3, this.e.longValue());
        }
        if (this.h != null) {
            bVar.a(4, this.h.floatValue());
        }
        if (this.f != null) {
            bVar.a(5, this.f.doubleValue());
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fk)) {
            return false;
        }
        fk fkVar = (fk) obj;
        if (this.c == null) {
            if (fkVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fkVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fkVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fkVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fkVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fkVar.e)) {
            return false;
        }
        if (this.h == null) {
            if (fkVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(fkVar.h)) {
            return false;
        }
        if (this.f == null) {
            if (fkVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fkVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fkVar.a == null || fkVar.a.b() : this.a.equals(fkVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.f == null ? 0 : this.f.hashCode()) + (((this.h == null ? 0 : this.h.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
