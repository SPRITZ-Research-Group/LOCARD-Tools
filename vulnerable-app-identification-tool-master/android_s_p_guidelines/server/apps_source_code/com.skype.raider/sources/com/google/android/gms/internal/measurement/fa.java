package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fa extends d<fa> {
    private static volatile fa[] g;
    public Integer c;
    public String d;
    public fb[] e;
    public fc f;
    private Boolean h;

    public fa() {
        this.c = null;
        this.d = null;
        this.e = fb.e();
        this.h = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fa[] e() {
        if (g == null) {
            synchronized (h.b) {
                if (g == null) {
                    g = new fa[0];
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
        if (this.e != null && this.e.length > 0) {
            int i = a;
            for (j jVar : this.e) {
                if (jVar != null) {
                    i += b.b(3, jVar);
                }
            }
            a = i;
        }
        if (this.h != null) {
            this.h.booleanValue();
            a += b.c(32) + 1;
        }
        return this.f != null ? a + b.b(5, this.f) : a;
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
                    this.d = aVar.c();
                    continue;
                case 26:
                    int a2 = m.a(aVar, 26);
                    a = this.e == null ? 0 : this.e.length;
                    Object obj = new fb[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.e, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fb();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fb();
                    aVar.a(obj[a]);
                    this.e = obj;
                    continue;
                case 32:
                    this.h = Boolean.valueOf(aVar.b());
                    continue;
                case 42:
                    if (this.f == null) {
                        this.f = new fc();
                    }
                    aVar.a(this.f);
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
        if (this.e != null && this.e.length > 0) {
            for (j jVar : this.e) {
                if (jVar != null) {
                    bVar.a(3, jVar);
                }
            }
        }
        if (this.h != null) {
            bVar.a(4, this.h.booleanValue());
        }
        if (this.f != null) {
            bVar.a(5, this.f);
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fa)) {
            return false;
        }
        fa faVar = (fa) obj;
        if (this.c == null) {
            if (faVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(faVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (faVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(faVar.d)) {
            return false;
        }
        if (!h.a(this.e, faVar.e)) {
            return false;
        }
        if (this.h == null) {
            if (faVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(faVar.h)) {
            return false;
        }
        if (this.f == null) {
            if (faVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(faVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? faVar.a == null || faVar.a.b() : this.a.equals(faVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.h == null ? 0 : this.h.hashCode()) + (((((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + h.a(this.e)) * 31);
        fc fcVar = this.f;
        hashCode = ((fcVar == null ? 0 : fcVar.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
