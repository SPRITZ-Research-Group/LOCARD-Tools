package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class e<K, V> extends HashMap<K, V> {
    private e(Map<? extends K, ? extends V> map) {
        super(map);
    }

    public static <K, V> Map<K, V> a(K k1, V v1) {
        Map<K, V> map = new HashMap(1);
        map.put(k1, v1);
        return Collections.unmodifiableMap(map);
    }

    public static <K, V> Map<K, V> a(K k1, V v1, K k2, V v2) {
        Map<K, V> map = new HashMap(2);
        map.put(k1, v1);
        map.put(k2, v2);
        return Collections.unmodifiableMap(map);
    }

    public static <K, V> e<K, V> a(Map<? extends K, ? extends V> map) {
        return new e(map);
    }
}
