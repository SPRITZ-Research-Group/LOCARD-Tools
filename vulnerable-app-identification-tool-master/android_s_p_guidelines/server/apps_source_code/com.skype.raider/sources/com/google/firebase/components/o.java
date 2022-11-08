package com.google.firebase.components;

import com.google.firebase.a.a;

final class o<T> implements a<T> {
    private static final Object a = new Object();
    private volatile Object b = a;
    private volatile a<T> c;

    o(c<T> cVar, b bVar) {
        this.c = new p(cVar, bVar);
    }

    public final T a() {
        T t = this.b;
        if (t == a) {
            synchronized (this) {
                t = this.b;
                if (t == a) {
                    t = this.c.a();
                    this.b = t;
                    this.c = null;
                }
            }
        }
        return t;
    }
}
