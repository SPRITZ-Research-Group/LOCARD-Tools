package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class i implements Iterator {
    private int a = 0;
    private final int b = this.c.a();
    private final /* synthetic */ h c;

    i(h hVar) {
        this.c = hVar;
    }

    private final byte a() {
        try {
            h hVar = this.c;
            int i = this.a;
            this.a = i + 1;
            return hVar.a(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final boolean hasNext() {
        return this.a < this.b;
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(a());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
