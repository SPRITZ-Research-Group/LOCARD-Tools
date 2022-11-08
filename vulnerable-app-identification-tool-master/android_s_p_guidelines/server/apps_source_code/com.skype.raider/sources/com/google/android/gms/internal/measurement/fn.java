package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fn extends d<fn> {
    public long[] c;
    public long[] d;

    public fn() {
        this.c = m.b;
        this.d = m.b;
        this.a = null;
        this.b = -1;
    }

    protected final int a() {
        int i;
        int i2;
        int i3 = 0;
        int a = super.a();
        if (this.c == null || this.c.length <= 0) {
            i = a;
        } else {
            i2 = 0;
            for (long a2 : this.c) {
                i2 += b.a(a2);
            }
            i = (a + i2) + (this.c.length * 1);
        }
        if (this.d == null || this.d.length <= 0) {
            return i;
        }
        i2 = 0;
        while (i3 < this.d.length) {
            i2 += b.a(this.d[i3]);
            i3++;
        }
        return (i + i2) + (this.d.length * 1);
    }

    public final /* synthetic */ j a(a aVar) throws IOException {
        while (true) {
            int a = aVar.a();
            int a2;
            Object obj;
            int b;
            Object obj2;
            switch (a) {
                case 0:
                    break;
                case 8:
                    a2 = m.a(aVar, 8);
                    a = this.c == null ? 0 : this.c.length;
                    obj = new long[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.c, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = aVar.e();
                        aVar.a();
                        a++;
                    }
                    obj[a] = aVar.e();
                    this.c = obj;
                    continue;
                case 10:
                    b = aVar.b(aVar.d());
                    a2 = aVar.i();
                    a = 0;
                    while (aVar.h() > 0) {
                        aVar.e();
                        a++;
                    }
                    aVar.d(a2);
                    a2 = this.c == null ? 0 : this.c.length;
                    obj2 = new long[(a + a2)];
                    if (a2 != 0) {
                        System.arraycopy(this.c, 0, obj2, 0, a2);
                    }
                    while (a2 < obj2.length) {
                        obj2[a2] = aVar.e();
                        a2++;
                    }
                    this.c = obj2;
                    aVar.c(b);
                    continue;
                case 16:
                    a2 = m.a(aVar, 16);
                    a = this.d == null ? 0 : this.d.length;
                    obj = new long[(a2 + a)];
                    if (a != 0) {
                        System.arraycopy(this.d, 0, obj, 0, a);
                    }
                    while (a < obj.length - 1) {
                        obj[a] = aVar.e();
                        aVar.a();
                        a++;
                    }
                    obj[a] = aVar.e();
                    this.d = obj;
                    continue;
                case 18:
                    b = aVar.b(aVar.d());
                    a2 = aVar.i();
                    a = 0;
                    while (aVar.h() > 0) {
                        aVar.e();
                        a++;
                    }
                    aVar.d(a2);
                    a2 = this.d == null ? 0 : this.d.length;
                    obj2 = new long[(a + a2)];
                    if (a2 != 0) {
                        System.arraycopy(this.d, 0, obj2, 0, a2);
                    }
                    while (a2 < obj2.length) {
                        obj2[a2] = aVar.e();
                        a2++;
                    }
                    this.d = obj2;
                    aVar.c(b);
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
        if (this.c != null && this.c.length > 0) {
            for (long a : this.c) {
                bVar.a(1, a);
            }
        }
        if (this.d != null && this.d.length > 0) {
            while (i < this.d.length) {
                bVar.a(2, this.d[i]);
                i++;
            }
        }
        super.a(bVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fn)) {
            return false;
        }
        fn fnVar = (fn) obj;
        return !h.a(this.c, fnVar.c) ? false : !h.a(this.d, fnVar.d) ? false : (this.a == null || this.a.b()) ? fnVar.a == null || fnVar.a.b() : this.a.equals(fnVar.a);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + h.a(this.c)) * 31) + h.a(this.d)) * 31;
        int hashCode2 = (this.a == null || this.a.b()) ? 0 : this.a.hashCode();
        return hashCode2 + hashCode;
    }
}
