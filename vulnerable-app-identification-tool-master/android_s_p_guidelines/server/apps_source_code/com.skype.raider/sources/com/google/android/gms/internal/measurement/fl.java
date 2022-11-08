package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fl extends d<fl> {
    public fm[] c;

    public fl() {
        this.c = fm.e();
        this.a = null;
        this.b = -1;
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
        return a;
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
                    Object obj = new fm[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.c, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = new fm();
                        aVar.a(obj[a]);
                        aVar.a();
                        a++;
                    }
                    obj[a] = new fm();
                    aVar.a(obj[a]);
                    this.c = obj;
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
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fl)) {
            return false;
        }
        fl flVar = (fl) obj;
        return !h.a(this.c, flVar.c) ? false : (this.a == null || this.a.b()) ? flVar.a == null || flVar.a.b() : this.a.equals(flVar.a);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + h.a(this.c)) * 31;
        int hashCode2 = (this.a == null || this.a.b()) ? 0 : this.a.hashCode();
        return hashCode2 + hashCode;
    }
}
