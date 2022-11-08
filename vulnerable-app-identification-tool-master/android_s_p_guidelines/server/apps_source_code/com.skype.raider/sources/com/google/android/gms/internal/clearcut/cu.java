package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class cu extends AbstractList<String> implements av, RandomAccess {
    private final av a;

    public cu(av avVar) {
        this.a = avVar;
    }

    public final Object a(int i) {
        return this.a.a(i);
    }

    public final List<?> d() {
        return this.a.d();
    }

    public final av e() {
        return this;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.a.get(i);
    }

    public final Iterator<String> iterator() {
        return new cw(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new cv(this, i);
    }

    public final int size() {
        return this.a.size();
    }
}
