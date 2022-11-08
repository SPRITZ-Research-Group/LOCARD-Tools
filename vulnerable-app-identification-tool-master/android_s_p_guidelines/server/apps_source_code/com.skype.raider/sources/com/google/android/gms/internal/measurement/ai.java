package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class ai implements Iterator<String> {
    private Iterator<String> a = this.b.a.keySet().iterator();
    private final /* synthetic */ zzer b;

    ai(zzer zzer) {
        this.b = zzer;
    }

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.a.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
