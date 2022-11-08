package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fb extends d<fb> {
    private static volatile fb[] g;
    public fe c;
    public fc d;
    public Boolean e;
    public String f;

    public fb() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fb[] e() {
        if (g == null) {
            synchronized (h.b) {
                if (g == null) {
                    g = new fb[0];
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
            this.e.booleanValue();
            a += b.c(24) + 1;
        }
        return this.f != null ? a + b.b(4, this.f) : a;
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    if (this.c == null) {
                        this.c = new fe();
                    }
                    aVar.a(this.c);
                    continue;
                case 18:
                    if (this.d == null) {
                        this.d = new fc();
                    }
                    aVar.a(this.d);
                    continue;
                case 24:
                    this.e = Boolean.valueOf(aVar.b());
                    continue;
                case 34:
                    this.f = aVar.c();
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
            bVar.a(3, this.e.booleanValue());
        }
        if (this.f != null) {
            bVar.a(4, this.f);
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fb)) {
            return false;
        }
        fb fbVar = (fb) obj;
        if (this.c == null) {
            if (fbVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fbVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fbVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fbVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fbVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fbVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fbVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fbVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fbVar.a == null || fbVar.a.b() : this.a.equals(fbVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = getClass().getName().hashCode() + 527;
        fe feVar = this.c;
        hashCode = (feVar == null ? 0 : feVar.hashCode()) + (hashCode * 31);
        fc fcVar = this.d;
        hashCode = ((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((fcVar == null ? 0 : fcVar.hashCode()) + (hashCode * 31)) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
