package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class bp<T> implements bz<T> {
    private final bk a;
    private final cr<?, ?> b;
    private final boolean c;
    private final w<?> d;

    private bp(cr<?, ?> crVar, w<?> wVar, bk bkVar) {
        this.b = crVar;
        this.c = wVar.a(bkVar);
        this.d = wVar;
        this.a = bkVar;
    }

    static <T> bp<T> a(cr<?, ?> crVar, w<?> wVar, bk bkVar) {
        return new bp(crVar, wVar, bkVar);
    }

    public final int a(T t) {
        int hashCode = this.b.a(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.a((Object) t).hashCode() : hashCode;
    }

    public final void a(T t, dk dkVar) throws IOException {
        Iterator e = this.d.a((Object) t).e();
        while (e.hasNext()) {
            Entry entry = (Entry) e.next();
            ac acVar = (ac) entry.getKey();
            if (acVar.c() != dj.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof ar) {
                dkVar.a(acVar.a(), ((ar) entry).a().c());
            } else {
                dkVar.a(acVar.a(), entry.getValue());
            }
        }
        cr crVar = this.b;
        crVar.b(crVar.a(t), dkVar);
    }

    public final boolean a(T t, T t2) {
        return !this.b.a(t).equals(this.b.a(t2)) ? false : this.c ? this.d.a((Object) t).equals(this.d.a((Object) t2)) : true;
    }

    public final int b(T t) {
        cr crVar = this.b;
        int c = crVar.c(crVar.a(t)) + 0;
        return this.c ? c + this.d.a((Object) t).i() : c;
    }

    public final void b(T t, T t2) {
        cb.a(this.b, (Object) t, (Object) t2);
        if (this.c) {
            cb.a(this.d, (Object) t, (Object) t2);
        }
    }

    public final void c(T t) {
        this.b.b(t);
        this.d.c(t);
    }

    public final boolean d(T t) {
        return this.d.a((Object) t).g();
    }
}
