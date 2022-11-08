package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fg extends d<fg> {
    public Long c;
    public String d;
    public fh[] e;
    public ff[] f;
    public ez[] g;
    private Integer h;

    public fg() {
        this.c = null;
        this.d = null;
        this.h = null;
        this.e = fh.e();
        this.f = ff.e();
        this.g = ez.e();
        this.a = null;
        this.b = -1;
    }

    protected final int a() {
        int i;
        int i2 = 0;
        int a = super.a();
        if (this.c != null) {
            a += b.c(1, this.c.longValue());
        }
        if (this.d != null) {
            a += b.b(2, this.d);
        }
        if (this.h != null) {
            a += b.b(3, this.h.intValue());
        }
        if (this.e != null && this.e.length > 0) {
            i = a;
            for (j jVar : this.e) {
                if (jVar != null) {
                    i += b.b(4, jVar);
                }
            }
            a = i;
        }
        if (this.f != null && this.f.length > 0) {
            i = a;
            for (j jVar2 : this.f) {
                if (jVar2 != null) {
                    i += b.b(5, jVar2);
                }
            }
            a = i;
        }
        if (this.g != null && this.g.length > 0) {
            while (i2 < this.g.length) {
                j jVar3 = this.g[i2];
                if (jVar3 != null) {
                    a += b.b(6, jVar3);
                }
                i2++;
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
                    this.c = Long.valueOf(aVar.e());
                    continue;
                case 18:
                    this.d = aVar.c();
                    continue;
                case 24:
                    this.h = Integer.valueOf(aVar.d());
                    continue;
                case 34:
                    a2 = m.a(aVar, 34);
                    a = this.e == null ? 0 : this.e.length;
                    obj = new fh[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.e, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fh();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fh();
                    aVar.a(obj[a]);
                    this.e = obj;
                    continue;
                case 42:
                    a2 = m.a(aVar, 42);
                    a = this.f == null ? 0 : this.f.length;
                    obj = new ff[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.f, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new ff();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new ff();
                    aVar.a(obj[a]);
                    this.f = obj;
                    continue;
                case 50:
                    a2 = m.a(aVar, 50);
                    a = this.g == null ? 0 : this.g.length;
                    obj = new ez[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.g, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new ez();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new ez();
                    aVar.a(obj[a]);
                    this.g = obj;
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
            bVar.b(1, this.c.longValue());
        }
        if (this.d != null) {
            bVar.a(2, this.d);
        }
        if (this.h != null) {
            bVar.a(3, this.h.intValue());
        }
        if (this.e != null && this.e.length > 0) {
            for (j jVar : this.e) {
                if (jVar != null) {
                    bVar.a(4, jVar);
                }
            }
        }
        if (this.f != null && this.f.length > 0) {
            for (j jVar2 : this.f) {
                if (jVar2 != null) {
                    bVar.a(5, jVar2);
                }
            }
        }
        if (this.g != null && this.g.length > 0) {
            while (i < this.g.length) {
                j jVar3 = this.g[i];
                if (jVar3 != null) {
                    bVar.a(6, jVar3);
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
        if (!(obj instanceof fg)) {
            return false;
        }
        fg fgVar = (fg) obj;
        if (this.c == null) {
            if (fgVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fgVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fgVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fgVar.d)) {
            return false;
        }
        if (this.h == null) {
            if (fgVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(fgVar.h)) {
            return false;
        }
        return !h.a(this.e, fgVar.e) ? false : !h.a(this.f, fgVar.f) ? false : !h.a(this.g, fgVar.g) ? false : (this.a == null || this.a.b()) ? fgVar.a == null || fgVar.a.b() : this.a.equals(fgVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.h == null ? 0 : this.h.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + h.a(this.e)) * 31) + h.a(this.f)) * 31) + h.a(this.g)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
