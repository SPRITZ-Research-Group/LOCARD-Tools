package com.google.android.gms.b;

import java.util.Iterator;

final class g implements e<T> {
    private final /* synthetic */ a a;

    g(a aVar) {
        this.a = aVar;
    }

    public final void a(T t) {
        this.a.a = t;
        Iterator it = this.a.c.iterator();
        while (it.hasNext()) {
            ((a) it.next()).b();
        }
        this.a.c.clear();
        this.a.b = null;
    }
}
