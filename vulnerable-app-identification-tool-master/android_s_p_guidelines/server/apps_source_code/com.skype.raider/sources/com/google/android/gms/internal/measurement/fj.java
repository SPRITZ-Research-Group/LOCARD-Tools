package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fj extends d<fj> {
    private static volatile fj[] h;
    public fk[] c;
    public String d;
    public Long e;
    public Long f;
    public Integer g;

    public fj() {
        this.c = fk.e();
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = null;
        this.b = -1;
    }

    public static fj[] e() {
        if (h == null) {
            synchronized (h.b) {
                if (h == null) {
                    h = new fj[0];
                }
            }
        }
        return h;
    }

    protected final int a() {
        int a = super.a();
        if (this.c != null && this.c.length > 0) {
            for (j jVar : this.c) {
                if (jVar != null) {
                    a += b.b(1, jVar);
                }
            }
        }
        if (this.d != null) {
            a += b.b(2, this.d);
        }
        if (this.e != null) {
            a += b.c(3, this.e.longValue());
        }
        if (this.f != null) {
            a += b.c(4, this.f.longValue());
        }
        return this.g != null ? a + b.b(5, this.g.intValue()) : a;
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    int a2 = m.a(aVar, 10);
                    a = this.c == null ? 0 : this.c.length;
                    Object obj = new fk[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.c, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fk();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fk();
                    aVar.a(obj[a]);
                    this.c = obj;
                    continue;
                case 18:
                    this.d = aVar.c();
                    continue;
                case 24:
                    this.e = Long.valueOf(aVar.e());
                    continue;
                case 32:
                    this.f = Long.valueOf(aVar.e());
                    continue;
                case 40:
                    this.g = Integer.valueOf(aVar.d());
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
        if (this.c != null && this.c.length > 0) {
            for (j jVar : this.c) {
                if (jVar != null) {
                    bVar.a(1, jVar);
                }
            }
        }
        if (this.d != null) {
            bVar.a(2, this.d);
        }
        if (this.e != null) {
            bVar.b(3, this.e.longValue());
        }
        if (this.f != null) {
            bVar.b(4, this.f.longValue());
        }
        if (this.g != null) {
            bVar.a(5, this.g.intValue());
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fj)) {
            return false;
        }
        fj fjVar = (fj) obj;
        if (!h.a(this.c, fjVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fjVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fjVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fjVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fjVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fjVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fjVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (fjVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fjVar.g)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fjVar.a == null || fjVar.a.b() : this.a.equals(fjVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.g == null ? 0 : this.g.hashCode()) + (((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + h.a(this.c)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
