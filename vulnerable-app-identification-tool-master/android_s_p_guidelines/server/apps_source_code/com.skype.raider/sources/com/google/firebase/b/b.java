package com.google.firebase.b;

import java.util.concurrent.atomic.AtomicReference;

public final class b {
    private static final AtomicReference<b> a = new AtomicReference();

    private b() {
    }

    public static b a() {
        a.compareAndSet(null, new b());
        return (b) a.get();
    }

    public static void b() {
    }
}
