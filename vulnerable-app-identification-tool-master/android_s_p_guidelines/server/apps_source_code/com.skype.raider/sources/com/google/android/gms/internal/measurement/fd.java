package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fd extends d<fd> {
    private static volatile fd[] f;
    public Integer c;
    public String d;
    public fb e;

    public fd() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = null;
        this.b = -1;
    }

    public static fd[] e() {
        if (f == null) {
            synchronized (h.b) {
                if (f == null) {
                    f = new fd[0];
                }
            }
        }
        return f;
    }

    protected final int a() {
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c.intValue());
        }
        if (this.d != null) {
            a += b.b(2, this.d);
        }
        return this.e != null ? a + b.b(3, this.e) : a;
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
                    if (this.e == null) {
                        this.e = new fb();
                    }
                    aVar.a(this.e);
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
        if (this.e != null) {
            bVar.a(3, this.e);
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fd)) {
            return false;
        }
        fd fdVar = (fd) obj;
        if (this.c == null) {
            if (fdVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fdVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fdVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fdVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fdVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fdVar.e)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fdVar.a == null || fdVar.a.b() : this.a.equals(fdVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31);
        fb fbVar = this.e;
        hashCode = ((fbVar == null ? 0 : fbVar.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
