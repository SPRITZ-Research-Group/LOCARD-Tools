package com.facebook.datasource;

import com.facebook.common.internal.j;

public final class d {
    public static <T> c<T> a(Throwable failure) {
        h<T> simpleDataSource = h.i();
        simpleDataSource.a(failure);
        return simpleDataSource;
    }

    public static <T> j<c<T>> b(final Throwable failure) {
        return new j<c<T>>() {
            public final /* bridge */ /* synthetic */ Object a() {
                return d.a(failure);
            }
        };
    }
}
