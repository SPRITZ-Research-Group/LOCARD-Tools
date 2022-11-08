package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class d<M extends d<M>> extends j {
    protected f a;

    protected int a() {
        int i = 0;
        if (this.a == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.a.a()) {
            i2 += this.a.b(i).a();
            i++;
        }
        return i2;
    }

    public void a(b bVar) throws IOException {
        if (this.a != null) {
            for (int i = 0; i < this.a.a(); i++) {
                this.a.b(i).a(bVar);
            }
        }
    }

    protected final boolean a(a aVar, int i) throws IOException {
        int i2 = aVar.i();
        if (!aVar.a(i)) {
            return false;
        }
        int i3 = i >>> 3;
        l lVar = new l(i, aVar.a(i2, aVar.i() - i2));
        g gVar = null;
        if (this.a == null) {
            this.a = new f();
        } else {
            gVar = this.a.a(i3);
        }
        if (gVar == null) {
            gVar = new g();
            this.a.a(i3, gVar);
        }
        gVar.a(lVar);
        return true;
    }

    public final /* synthetic */ j b() throws CloneNotSupportedException {
        return (d) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        d dVar = (d) super.b();
        h.a(this, dVar);
        return dVar;
    }
}
