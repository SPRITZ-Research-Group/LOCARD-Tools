package com.facebook.common.a;

import javax.annotation.Nullable;

public final class b implements a {
    @Nullable
    private static b a = null;

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }
}
