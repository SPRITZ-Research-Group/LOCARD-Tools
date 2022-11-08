package com.google.android.gms.internal.clearcut;

final class bh {
    private static final bf a = c();
    private static final bf b = new bg();

    static bf a() {
        return a;
    }

    static bf b() {
        return b;
    }

    private static bf c() {
        try {
            return (bf) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
