package com.facebook.react.bridge;

import javax.inject.a;

public final class y {
    private static final Class[] a = new Class[0];
    private static final Class[] b = new Class[]{ag.class};
    private final Class<? extends NativeModule> c;
    private final a<? extends NativeModule> d;

    public y(Class<? extends NativeModule> type, a<? extends NativeModule> provider) {
        this.c = type;
        this.d = provider;
    }

    public final Class<? extends NativeModule> a() {
        return this.c;
    }

    public final a<? extends NativeModule> b() {
        return this.d;
    }
}
