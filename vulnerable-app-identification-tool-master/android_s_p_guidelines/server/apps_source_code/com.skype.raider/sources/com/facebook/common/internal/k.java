package com.facebook.common.internal;

public final class k {
    public static final j<Boolean> a = new j<Boolean>() {
        public final /* synthetic */ Object a() {
            return Boolean.valueOf(true);
        }
    };
    public static final j<Boolean> b = new j<Boolean>() {
        public final /* synthetic */ Object a() {
            return Boolean.valueOf(false);
        }
    };

    public static <T> j<T> a(final T instance) {
        return new j<T>() {
            public final T a() {
                return instance;
            }
        };
    }
}
