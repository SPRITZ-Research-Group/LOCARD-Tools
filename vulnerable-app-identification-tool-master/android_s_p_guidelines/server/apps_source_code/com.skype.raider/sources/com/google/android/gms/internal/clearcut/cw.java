package com.google.android.gms.internal.clearcut;

import java.util.Iterator;

final class cw implements Iterator<String> {
    private Iterator<String> a = this.b.a.iterator();
    private final /* synthetic */ cu b;

    cw(cu cuVar) {
        this.b = cuVar;
    }

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.a.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
