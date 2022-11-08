package androidx.recyclerview.widget;

import defpackage.gp;
import defpackage.gq;

final class cn {
    static gp<cn> d = new gq(20);
    int a;
    bg b;
    bg c;

    private cn() {
    }

    static cn a() {
        cn cnVar = (cn) d.a();
        return cnVar == null ? new cn() : cnVar;
    }

    static void a(cn cnVar) {
        cnVar.a = 0;
        cnVar.b = null;
        cnVar.c = null;
        d.a(cnVar);
    }

    static void b() {
        do {
        } while (d.a() != null);
    }
}
