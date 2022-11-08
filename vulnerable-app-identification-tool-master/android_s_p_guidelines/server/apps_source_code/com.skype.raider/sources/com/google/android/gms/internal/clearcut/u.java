package com.google.android.gms.internal.clearcut;

final class u {
    private static final Class<?> a = b();

    public static v a() {
        if (a != null) {
            try {
                return (v) a.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
            }
        }
        return v.a;
    }

    private static Class<?> b() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
