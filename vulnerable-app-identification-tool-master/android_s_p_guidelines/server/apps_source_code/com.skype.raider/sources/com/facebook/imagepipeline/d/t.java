package com.facebook.imagepipeline.d;

public final class t implements n {
    private static t a = null;

    private t() {
    }

    public static synchronized t a() {
        t tVar;
        synchronized (t.class) {
            if (a == null) {
                a = new t();
            }
            tVar = a;
        }
        return tVar;
    }
}
