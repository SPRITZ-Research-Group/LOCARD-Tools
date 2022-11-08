package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class ax extends aw {
    private static final Class<?> a = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private ax() {
        super();
    }

    /* synthetic */ ax(byte b) {
        this();
    }

    private static <E> List<E> b(Object obj, long j) {
        return (List) cx.f(obj, j);
    }

    final void a(Object obj, long j) {
        Object e;
        List list = (List) cx.f(obj, j);
        if (list instanceof av) {
            e = ((av) list).e();
        } else if (!a.isAssignableFrom(list.getClass())) {
            e = Collections.unmodifiableList(list);
        } else {
            return;
        }
        cx.a(obj, j, e);
    }

    final <E> void a(Object obj, Object obj2, long j) {
        Collection b = b(obj2, j);
        int size = b.size();
        Object b2 = b(obj, j);
        Object arrayList;
        if (b2.isEmpty()) {
            b2 = b2 instanceof av ? new au(size) : new ArrayList(size);
            cx.a(obj, j, b2);
        } else if (a.isAssignableFrom(b2.getClass())) {
            arrayList = new ArrayList(size + b2.size());
            arrayList.addAll(b2);
            cx.a(obj, j, arrayList);
            b2 = arrayList;
        } else if (b2 instanceof cu) {
            arrayList = new au(size + b2.size());
            arrayList.addAll((cu) b2);
            cx.a(obj, j, arrayList);
            b2 = arrayList;
        }
        int size2 = b2.size();
        size = b.size();
        if (size2 > 0 && size > 0) {
            b2.addAll(b);
        }
        if (size2 <= 0) {
            Collection collection = b;
        }
        cx.a(obj, j, b2);
    }
}
