package com.facebook.common.time;

public final class c implements a {
    private static final c a = new c();

    private c() {
    }

    public static c b() {
        return a;
    }

    public final long a() {
        return System.currentTimeMillis();
    }
}
