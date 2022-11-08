package com.google.android.gms.internal.clearcut;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class v {
    static final v a = new v((byte) 0);
    private static volatile boolean b = false;
    private static final Class<?> c = b();
    private final Map<Object, Object<?, ?>> d;

    v() {
        this.d = new HashMap();
    }

    private v(byte b) {
        this.d = Collections.emptyMap();
    }

    public static v a() {
        return u.a();
    }

    private static Class<?> b() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
