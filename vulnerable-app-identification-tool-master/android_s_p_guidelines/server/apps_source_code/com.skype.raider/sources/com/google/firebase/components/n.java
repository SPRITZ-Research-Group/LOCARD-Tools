package com.google.firebase.components;

import java.util.HashSet;
import java.util.Set;

final class n {
    private final a<?> a;
    private final Set<n> b = new HashSet();
    private final Set<n> c = new HashSet();

    n(a<?> aVar) {
        this.a = aVar;
    }

    final Set<n> a() {
        return this.b;
    }

    final void a(n nVar) {
        this.b.add(nVar);
    }

    final a<?> b() {
        return this.a;
    }

    final void b(n nVar) {
        this.c.add(nVar);
    }

    final void c(n nVar) {
        this.c.remove(nVar);
    }

    final boolean c() {
        return this.c.isEmpty();
    }

    final boolean d() {
        return this.b.isEmpty();
    }
}
