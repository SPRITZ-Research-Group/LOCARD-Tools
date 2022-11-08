package com.google.android.gms.internal.clearcut;

import java.util.Collection;

final class ay extends aw {
    private ay() {
        super();
    }

    /* synthetic */ ay(byte b) {
        this();
    }

    private static <E> am<E> b(Object obj, long j) {
        return (am) cx.f(obj, j);
    }

    final void a(Object obj, long j) {
        b(obj, j).b();
    }

    final <E> void a(Object obj, Object obj2, long j) {
        Object b = b(obj, j);
        Collection b2 = b(obj2, j);
        int size = b.size();
        int size2 = b2.size();
        if (size > 0 && size2 > 0) {
            if (!b.a()) {
                b = b.b(size2 + size);
            }
            b.addAll(b2);
        }
        if (size <= 0) {
            Collection collection = b2;
        }
        cx.a(obj, j, b);
    }
}
