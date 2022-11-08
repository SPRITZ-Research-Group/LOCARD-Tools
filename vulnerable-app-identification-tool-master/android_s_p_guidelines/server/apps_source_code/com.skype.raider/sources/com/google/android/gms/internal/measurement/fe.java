package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fe extends d<fe> {
    public Integer c;
    public String d;
    public Boolean e;
    public String[] f;

    public fe() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = m.c;
        this.a = null;
        this.b = -1;
    }

    private final fe b(a aVar) throws IOException {
        int i;
        while (true) {
            int a = aVar.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    i = aVar.i();
                    try {
                        int d = aVar.d();
                        if (d < 0 || d > 6) {
                            throw new IllegalArgumentException(d + " is not a valid enum MatchType");
                        }
                        this.c = Integer.valueOf(d);
                        continue;
                    } catch (IllegalArgumentException e) {
                        aVar.d(i);
                        a(aVar, a);
                        break;
                    }
                case 18:
                    this.d = aVar.c();
                    continue;
                case 24:
                    this.e = Boolean.valueOf(aVar.b());
                    continue;
                case 34:
                    i = m.a(aVar, 34);
                    a = this.f == null ? 0 : this.f.length;
                    Object obj = new String[(i + a)];
                    if (a != 0) {
                        System.arraycopy(this.f, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = aVar.c();
                        aVar.a();
                        a++;
                    }
                    obj[a] = aVar.c();
                    this.f = obj;
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
        int i = 0;
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c.intValue());
        }
        if (this.d != null) {
            a += b.b(2, this.d);
        }
        if (this.e != null) {
            this.e.booleanValue();
            a += b.c(24) + 1;
        }
        if (this.f == null || this.f.length <= 0) {
            return a;
        }
        int i2 = 0;
        int i3 = 0;
        while (i < this.f.length) {
            String str = this.f[i];
            if (str != null) {
                i3++;
                i2 += b.a(str);
            }
            i++;
        }
        return (a + i2) + (i3 * 1);
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        return b(aVar);
    }

    public final void a(b bVar) throws IOException {
        if (this.c != null) {
            bVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            bVar.a(2, this.d);
        }
        if (this.e != null) {
            bVar.a(3, this.e.booleanValue());
        }
        if (this.f != null && this.f.length > 0) {
            for (String str : this.f) {
                if (str != null) {
                    bVar.a(4, str);
                }
            }
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fe)) {
            return false;
        }
        fe feVar = (fe) obj;
        if (this.c == null) {
            if (feVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(feVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (feVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(feVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (feVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(feVar.e)) {
            return false;
        }
        return !h.a(this.f, feVar.f) ? false : (this.a == null || this.a.b()) ? feVar.a == null || feVar.a.b() : this.a.equals(feVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + h.a(this.f)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
