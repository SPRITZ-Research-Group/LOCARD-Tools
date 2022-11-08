package com.android.volley.toolbox;

import defpackage.xi;
import java.util.LinkedList;

final class b {
    final /* synthetic */ a a;
    private final xi<?> b;
    private final LinkedList<d> c = new LinkedList();

    public b(a aVar, xi<?> xiVar, d dVar) {
        this.a = aVar;
        this.b = xiVar;
        this.c.add(dVar);
    }

    public final void a(d dVar) {
        this.c.add(dVar);
    }

    public final boolean b(d dVar) {
        this.c.remove(dVar);
        if (this.c.size() != 0) {
            return false;
        }
        this.b.b();
        return true;
    }
}
