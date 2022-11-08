package com.google.gson.a;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class i {
    private static final Map<Class<?>, Class<?>> a;
    private static final Map<Class<?>, Class<?>> b;

    static {
        Map<Class<?>, Class<?>> primToWrap = new HashMap(16);
        Map<Class<?>, Class<?>> wrapToPrim = new HashMap(16);
        a(primToWrap, wrapToPrim, Boolean.TYPE, Boolean.class);
        a(primToWrap, wrapToPrim, Byte.TYPE, Byte.class);
        a(primToWrap, wrapToPrim, Character.TYPE, Character.class);
        a(primToWrap, wrapToPrim, Double.TYPE, Double.class);
        a(primToWrap, wrapToPrim, Float.TYPE, Float.class);
        a(primToWrap, wrapToPrim, Integer.TYPE, Integer.class);
        a(primToWrap, wrapToPrim, Long.TYPE, Long.class);
        a(primToWrap, wrapToPrim, Short.TYPE, Short.class);
        a(primToWrap, wrapToPrim, Void.TYPE, Void.class);
        a = Collections.unmodifiableMap(primToWrap);
        b = Collections.unmodifiableMap(wrapToPrim);
    }

    private static void a(Map<Class<?>, Class<?>> forward, Map<Class<?>, Class<?>> backward, Class<?> key, Class<?> value) {
        forward.put(key, value);
        backward.put(value, key);
    }

    public static boolean a(Type type) {
        return a.containsKey(type);
    }

    public static <T> Class<T> a(Class<T> type) {
        Class<T> wrapped = (Class) a.get(a.a((Object) type));
        return wrapped == null ? type : wrapped;
    }
}
