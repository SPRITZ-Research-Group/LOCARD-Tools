package com.facebook.react.common;

import java.util.HashMap;
import java.util.Map;

public final class e {

    public static final class a<K, V> {
        private Map a;
        private boolean b;

        /* synthetic */ a(byte b) {
            this();
        }

        private a() {
            this.a = new HashMap();
            this.b = true;
        }

        public final a<K, V> a(K k, V v) {
            if (this.b) {
                this.a.put(k, v);
                return this;
            }
            throw new IllegalStateException("Underlying map has already been built");
        }

        public final Map<K, V> a() {
            if (this.b) {
                this.b = false;
                return this.a;
            }
            throw new IllegalStateException("Underlying map has already been built");
        }
    }

    public static <K, V> a<K, V> a() {
        return new a();
    }

    public static <K, V> Map<K, V> a(K k1, V v1) {
        Map map = new HashMap();
        map.put(k1, v1);
        return map;
    }

    public static <K, V> Map<K, V> a(K k1, V v1, K k2, V v2) {
        Map map = new HashMap();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    public static <K, V> Map<K, V> a(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map map = new HashMap();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    public static <K, V> Map<K, V> a(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map map = new HashMap();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    public static <K, V> Map<K, V> a(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map map = new HashMap();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }
}
