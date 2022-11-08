package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fh extends d<fh> {
    private static volatile fh[] e;
    public String c;
    public String d;

    public fh() {
        this.c = null;
        this.d = null;
        this.a = null;
        this.b = -1;
    }

    public static fh[] e() {
        if (e == null) {
            synchronized (h.b) {
                if (e == null) {
                    e = new fh[0];
                }
            }
        }
        return e;
    }

    protected final int a() {
        int a = super.a();
        if (this.c != null) {
            a += b.b(1, this.c);
        }
        return this.d != null ? a + b.b(2, this.d) : a;
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
                case 18:
                    this.d = aVar.c();
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
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fh)) {
            return false;
        }
        fh fhVar = (fh) obj;
        if (this.c == null) {
            if (fhVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fhVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fhVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fhVar.d)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fhVar.a == null || fhVar.a.b() : this.a.equals(fhVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
        if (!(this.a == null || this.a.b())) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
