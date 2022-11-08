package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class ez extends d<ez> {
    private static volatile ez[] f;
    public Integer c;
    public fd[] d;
    public fa[] e;

    public ez() {
        this.c = null;
        this.d = fd.e();
        this.e = fa.e();
        this.a = null;
        this.b = -1;
    }

    public static ez[] e() {
        if (f == null) {
            synchronized (h.b) {
                if (f == null) {
                    f = new ez[0];
                }
            }
        }
        return f;
    }

    protected final int a() {
        int i = 0;
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            int i2 = a;
            for (j jVar : this.d) {
                if (jVar != null) {
                    i2 += b.b(2, jVar);
                }
            }
            a = i2;
        }
        if (this.e != null && this.e.length > 0) {
            while (i < this.e.length) {
                j jVar2 = this.e[i];
                if (jVar2 != null) {
                    a += b.b(3, jVar2);
                }
                i++;
            }
        }
        return a;
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            int a2;
            Object obj;
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.c = Integer.valueOf(aVar.d());
                    continue;
                case 18:
                    a2 = m.a(aVar, 18);
                    a = this.d == null ? 0 : this.d.length;
                    obj = new fd[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.d, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fd();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fd();
                    aVar.a(obj[a]);
                    this.d = obj;
                    continue;
                case 26:
                    a2 = m.a(aVar, 26);
                    a = this.e == null ? 0 : this.e.length;
                    obj = new fa[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.e, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fa();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fa();
                    aVar.a(obj[a]);
                    this.e = obj;
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
        int i = 0;
        if (this.c != null) {
            bVar.a(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            for (j jVar : this.d) {
                if (jVar != null) {
                    bVar.a(2, jVar);
                }
            }
        }
        if (this.e != null && this.e.length > 0) {
            while (i < this.e.length) {
                j jVar2 = this.e[i];
                if (jVar2 != null) {
                    bVar.a(3, jVar2);
                }
                i++;
            }
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ez)) {
            return false;
        }
        ez ezVar = (ez) obj;
        if (this.c == null) {
            if (ezVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(ezVar.c)) {
            return false;
        }
        return !h.a(this.d, ezVar.d) ? false : !h.a(this.e, ezVar.e) ? false : (this.a == null || this.a.b()) ? ezVar.a == null || ezVar.a.b() : this.a.equals(ezVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + h.a(this.d)) * 31) + h.a(this.e)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
