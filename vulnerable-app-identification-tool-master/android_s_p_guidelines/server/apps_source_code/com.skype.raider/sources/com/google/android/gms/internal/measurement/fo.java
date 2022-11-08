package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fo extends d<fo> {
    private static volatile fo[] h;
    public Long c;
    public String d;
    public String e;
    public Long f;
    public Double g;
    private Float i;

    public fo() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.i = null;
        this.g = null;
        this.a = null;
        this.b = -1;
    }

    public static fo[] e() {
        if (h == null) {
            synchronized (h.b) {
                if (h == null) {
                    h = new fo[0];
                }
            }
        }
        return h;
    }

    protected final int a() {
        int a = super.a();
        if (this.c != null) {
            a += b.c(1, this.c.longValue());
        }
        if (this.d != null) {
            a += b.b(2, this.d);
        }
        if (this.e != null) {
            a += b.b(3, this.e);
        }
        if (this.f != null) {
            a += b.c(4, this.f.longValue());
        }
        if (this.i != null) {
            this.i.floatValue();
            a += b.c(40) + 4;
        }
        if (this.g == null) {
            return a;
        }
        this.g.doubleValue();
        return a + (b.c(48) + 8);
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.c = Long.valueOf(aVar.e());
                    continue;
                case 18:
                    this.d = aVar.c();
                    continue;
                case 26:
                    this.e = aVar.c();
                    continue;
                case 32:
                    this.f = Long.valueOf(aVar.e());
                    continue;
                case 45:
                    this.i = Float.valueOf(Float.intBitsToFloat(aVar.f()));
                    continue;
                case 49:
                    this.g = Double.valueOf(Double.longBitsToDouble(aVar.g()));
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
            bVar.b(1, this.c.longValue());
        }
        if (this.d != null) {
            bVar.a(2, this.d);
        }
        if (this.e != null) {
            bVar.a(3, this.e);
        }
        if (this.f != null) {
            bVar.b(4, this.f.longValue());
        }
        if (this.i != null) {
            bVar.a(5, this.i.floatValue());
        }
        if (this.g != null) {
            bVar.a(6, this.g.doubleValue());
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fo)) {
            return false;
        }
        fo foVar = (fo) obj;
        if (this.c == null) {
            if (foVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(foVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (foVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(foVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (foVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(foVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (foVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(foVar.f)) {
            return false;
        }
        if (this.i == null) {
            if (foVar.i != null) {
                return false;
            }
        } else if (!this.i.equals(foVar.i)) {
            return false;
        }
        if (this.g == null) {
            if (foVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(foVar.g)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? foVar.a == null || foVar.a.b() : this.a.equals(foVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.g == null ? 0 : this.g.hashCode()) + (((this.i == null ? 0 : this.i.hashCode()) + (((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
