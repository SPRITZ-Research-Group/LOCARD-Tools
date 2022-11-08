package com.google.android.gms.internal.clearcut;

public enum ap {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(h.class, h.class, h.a),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> k;
    private final Class<?> l;
    private final Object m;

    private ap(Class<?> cls, Class<?> cls2, Object obj) {
        this.k = cls;
        this.l = cls2;
        this.m = obj;
    }

    public final Class<?> a() {
        return this.l;
    }
}
