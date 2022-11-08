package com.google.android.gms.internal.clearcut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class bv {
    private static final bv a = new bv();
    private final ca b;
    private final ConcurrentMap<Class<?>, bz<?>> c = new ConcurrentHashMap();

    private bv() {
        ca caVar = null;
        String[] strArr = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        for (int i = 0; i <= 0; i++) {
            caVar = a(strArr[0]);
            if (caVar != null) {
                break;
            }
        }
        if (caVar == null) {
            caVar = new ba();
        }
        this.b = caVar;
    }

    public static bv a() {
        return a;
    }

    private static ca a(String str) {
        try {
            return (ca) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public final <T> bz<T> a(Class<T> cls) {
        aj.a((Object) cls, "messageType");
        bz<T> bzVar = (bz) this.c.get(cls);
        if (bzVar != null) {
            return bzVar;
        }
        bz<T> a = this.b.a(cls);
        aj.a((Object) cls, "messageType");
        aj.a((Object) a, "schema");
        bzVar = (bz) this.c.putIfAbsent(cls, a);
        return bzVar != null ? bzVar : a;
    }

    public final <T> bz<T> a(T t) {
        return a(t.getClass());
    }
}
