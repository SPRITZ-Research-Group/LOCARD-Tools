package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fc extends d<fc> {
    public Integer c;
    public Boolean d;
    public String e;
    public String f;
    public String g;

    public fc() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = null;
        this.b = -1;
    }

    private final fc b(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    int i = aVar.i();
                    try {
                        int d = aVar.d();
                        if (d < 0 || d > 4) {
                            throw new IllegalArgumentException(d + " is not a valid enum ComparisonType");
                        }
                        this.c = Integer.valueOf(d);
                        continue;
                    } catch (IllegalArgumentException e) {
                        aVar.d(i);
                        a(aVar, a);
                        break;
                    }
                case 16:
                    this.d = Boolean.valueOf(aVar.b());
                    continue;
                case 26:
                    this.e = aVar.c();
                    continue;
                case 34:
                    this.f = aVar.c();
                    continue;
                case 42:
                    this.g = aVar.c();
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

    protected final int a() {
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c.intValue());
        }
        if (this.d != null) {
            this.d.booleanValue();
            a += b.c(16) + 1;
        }
        if (this.e != null) {
            a += b.b(3, this.e);
        }
        if (this.f != null) {
            a += b.b(4, this.f);
        }
        return this.g != null ? a + b.b(5, this.g) : a;
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        return b(aVar);
    }

    public final void a(b bVar) throws IOException {
        if (this.c != null) {
            bVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            bVar.a(2, this.d.booleanValue());
        }
        if (this.e != null) {
            bVar.a(3, this.e);
        }
        if (this.f != null) {
            bVar.a(4, this.f);
        }
        if (this.g != null) {
            bVar.a(5, this.g);
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fc)) {
            return false;
        }
        fc fcVar = (fc) obj;
        if (this.c == null) {
            if (fcVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fcVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fcVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fcVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fcVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fcVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fcVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fcVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (fcVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fcVar.g)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fcVar.a == null || fcVar.a.b() : this.a.equals(fcVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.g == null ? 0 : this.g.hashCode()) + (((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
