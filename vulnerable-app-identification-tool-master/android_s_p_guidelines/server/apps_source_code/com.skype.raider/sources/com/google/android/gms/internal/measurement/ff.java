package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class ff extends d<ff> {
    private static volatile ff[] g;
    public String c;
    public Boolean d;
    public Boolean e;
    public Integer f;

    public ff() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static ff[] e() {
        if (g == null) {
            synchronized (h.b) {
                if (g == null) {
                    g = new ff[0];
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
            this.d.booleanValue();
            a += b.c(16) + 1;
        }
        if (this.e != null) {
            this.e.booleanValue();
            a += b.c(24) + 1;
        }
        return this.f != null ? a + b.b(4, this.f.intValue()) : a;
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
                case 16:
                    this.d = Boolean.valueOf(aVar.b());
                    continue;
                case 24:
                    this.e = Boolean.valueOf(aVar.b());
                    continue;
                case 32:
                    this.f = Integer.valueOf(aVar.d());
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
            bVar.a(2, this.d.booleanValue());
        }
        if (this.e != null) {
            bVar.a(3, this.e.booleanValue());
        }
        if (this.f != null) {
            bVar.a(4, this.f.intValue());
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ff)) {
            return false;
        }
        ff ffVar = (ff) obj;
        if (this.c == null) {
            if (ffVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(ffVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (ffVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(ffVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (ffVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(ffVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (ffVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(ffVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? ffVar.a == null || ffVar.a.b() : this.a.equals(ffVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
