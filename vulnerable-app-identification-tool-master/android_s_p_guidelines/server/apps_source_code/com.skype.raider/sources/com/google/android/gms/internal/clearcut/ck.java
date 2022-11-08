package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map.Entry;

final class ck implements Iterator<Entry<K, V>> {
    private int a;
    private boolean b;
    private Iterator<Entry<K, V>> c;
    private final /* synthetic */ cc d;

    private ck(cc ccVar) {
        this.d = ccVar;
        this.a = -1;
    }

    /* synthetic */ ck(cc ccVar, byte b) {
        this(ccVar);
    }

    private final Iterator<Entry<K, V>> a() {
        if (this.c == null) {
            this.c = this.d.c.entrySet().iterator();
        }
        return this.c;
    }

    public final boolean hasNext() {
        return this.a + 1 < this.d.b.size() || (!this.d.c.isEmpty() && a().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.b = true;
        int i = this.a + 1;
        this.a = i;
        return i < this.d.b.size() ? (Entry) this.d.b.get(this.a) : (Entry) a().next();
    }

    public final void remove() {
        if (this.b) {
            this.b = false;
            this.d.f();
            if (this.a < this.d.b.size()) {
                cc ccVar = this.d;
                int i = this.a;
                this.a = i - 1;
                ccVar.c(i);
                return;
            }
            a().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
