package com.google.android.gms.internal.clearcut;

final class f {
    private static final Class<?> a = a("libcore.io.Memory");
    private static final boolean b = (a("org.robolectric.Robolectric") != null);

    private static <T> Class<T> a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean a() {
        return (a == null || b) ? false : true;
    }

    static Class<?> b() {
        return a;
    }
}
