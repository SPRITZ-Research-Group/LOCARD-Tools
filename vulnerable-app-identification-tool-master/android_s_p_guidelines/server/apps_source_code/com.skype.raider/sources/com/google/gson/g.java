package com.google.gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class g extends i implements Iterable<i> {
    private final List<i> a = new ArrayList();

    public final void a(i element) {
        Object element2;
        if (element2 == null) {
            element2 = k.a;
        }
        this.a.add(element2);
    }

    public final Iterator<i> iterator() {
        return this.a.iterator();
    }

    public final Number a() {
        if (this.a.size() == 1) {
            return ((i) this.a.get(0)).a();
        }
        throw new IllegalStateException();
    }

    public final String b() {
        if (this.a.size() == 1) {
            return ((i) this.a.get(0)).b();
        }
        throw new IllegalStateException();
    }

    public final double c() {
        if (this.a.size() == 1) {
            return ((i) this.a.get(0)).c();
        }
        throw new IllegalStateException();
    }

    public final long d() {
        if (this.a.size() == 1) {
            return ((i) this.a.get(0)).d();
        }
        throw new IllegalStateException();
    }

    public final int e() {
        if (this.a.size() == 1) {
            return ((i) this.a.get(0)).e();
        }
        throw new IllegalStateException();
    }

    public final boolean f() {
        if (this.a.size() == 1) {
            return ((i) this.a.get(0)).f();
        }
        throw new IllegalStateException();
    }

    public final boolean equals(Object o) {
        return o == this || ((o instanceof g) && ((g) o).a.equals(this.a));
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
