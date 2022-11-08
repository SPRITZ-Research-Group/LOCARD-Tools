package com.facebook.common.e;

public final class e implements d {
    private static e a = null;

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    public final void a(c trimmable) {
    }
}
