package com.facebook.common.internal;

import java.util.ArrayList;
import java.util.Collections;

public final class d<E> extends ArrayList<E> {
    private d() {
        super(4);
    }

    public static <E> d<E> a(E... elements) {
        d<E> list = new d();
        Collections.addAll(list, elements);
        return list;
    }
}
