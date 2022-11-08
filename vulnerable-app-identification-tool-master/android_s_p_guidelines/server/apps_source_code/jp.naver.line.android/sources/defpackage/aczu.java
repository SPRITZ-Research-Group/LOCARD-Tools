package defpackage;

import java.util.Iterator;

/* renamed from: aczu */
final class aczu<E> implements Iterator<E> {
    private aczt<E> a;

    public aczu(aczt<E> aczt) {
        this.a = aczt;
    }

    public final boolean hasNext() {
        return this.a.d > 0;
    }

    public final E next() {
        E e = this.a.a;
        this.a = this.a.b;
        return e;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
