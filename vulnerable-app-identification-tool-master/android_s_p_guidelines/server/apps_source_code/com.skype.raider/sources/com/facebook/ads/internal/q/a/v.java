package com.facebook.ads.internal.q.a;

import java.lang.ref.WeakReference;

public abstract class v<T> implements Runnable {
    private final WeakReference<T> a;

    public v(T t) {
        this.a = new WeakReference(t);
    }

    public final T a() {
        return this.a.get();
    }
}
