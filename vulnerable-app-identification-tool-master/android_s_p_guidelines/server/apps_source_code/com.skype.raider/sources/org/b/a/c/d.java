package org.b.a.c;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class d implements Iterator<Object> {
    protected int a = -1;
    protected Object b = null;
    protected int c;

    public d(Object array) {
        this.b = array;
        this.c = Array.getLength(array);
    }

    public final boolean hasNext() {
        return this.a + 1 < this.c && this.c > 0;
    }

    public final Object next() {
        this.a++;
        if (this.a < this.c) {
            return Array.get(this.b, this.a);
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
