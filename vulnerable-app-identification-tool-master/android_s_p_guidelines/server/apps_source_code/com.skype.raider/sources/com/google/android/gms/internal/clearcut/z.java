package com.google.android.gms.internal.clearcut;

final class z {
    private static final w<?> a = new x();
    private static final w<?> b = c();

    static w<?> a() {
        return a;
    }

    static w<?> b() {
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static w<?> c() {
        try {
            return (w) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
