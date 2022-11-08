package com.google.firebase.components;

import com.google.firebase.a.a;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class r implements b {
    private final Set<Class<?>> a;
    private final Set<Class<?>> b;
    private final b c;

    public r(Iterable<e> iterable, b bVar) {
        Set hashSet = new HashSet();
        Set hashSet2 = new HashSet();
        for (e eVar : iterable) {
            if (eVar.c()) {
                hashSet.add(eVar.a());
            } else {
                hashSet2.add(eVar.a());
            }
        }
        this.a = Collections.unmodifiableSet(hashSet);
        this.b = Collections.unmodifiableSet(hashSet2);
        this.c = bVar;
    }

    public final <T> T a(Class<T> cls) {
        if (this.a.contains(cls)) {
            return this.c.a(cls);
        }
        throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", new Object[]{cls}));
    }

    public final <T> a<T> b(Class<T> cls) {
        if (this.b.contains(cls)) {
            return this.c.b(cls);
        }
        throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", new Object[]{cls}));
    }
}
