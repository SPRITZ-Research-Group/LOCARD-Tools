package com.facebook.cache.a;

public final class f implements a {
    private static f a = null;

    private f() {
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }
}
