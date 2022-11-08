package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class be<K, V> extends LinkedHashMap<K, V> {
    private static final be b;
    private boolean a = true;

    static {
        be beVar = new be();
        b = beVar;
        beVar.a = false;
    }

    private be() {
    }

    private be(Map<K, V> map) {
        super(map);
    }

    private static int a(Object obj) {
        if (obj instanceof byte[]) {
            return aj.c((byte[]) obj);
        }
        if (!(obj instanceof ak)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    private final void d() {
        if (!this.a) {
            throw new UnsupportedOperationException();
        }
    }

    public final be<K, V> a() {
        return isEmpty() ? new be() : new be(this);
    }

    public final void a(be<K, V> beVar) {
        d();
        if (!beVar.isEmpty()) {
            putAll(beVar);
        }
    }

    public final void b() {
        this.a = false;
    }

    public final boolean c() {
        return this.a;
    }

    public final void clear() {
        d();
        super.clear();
    }

    public final Set<Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Map) {
            Object obj2;
            obj = (Map) obj;
            if (this != obj) {
                if (size() == obj.size()) {
                    for (Entry entry : entrySet()) {
                        if (!obj.containsKey(entry.getKey())) {
                            obj2 = null;
                            break;
                        }
                        boolean equals;
                        Object value = entry.getValue();
                        Object obj3 = obj.get(entry.getKey());
                        if ((value instanceof byte[]) && (obj3 instanceof byte[])) {
                            equals = Arrays.equals((byte[]) value, (byte[]) obj3);
                            continue;
                        } else {
                            equals = value.equals(obj3);
                            continue;
                        }
                        if (!equals) {
                            obj2 = null;
                            break;
                        }
                    }
                }
                obj2 = null;
                if (obj2 != null) {
                    return true;
                }
            }
            int obj22 = 1;
            if (obj22 != null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        Iterator it = entrySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            Entry entry = (Entry) it.next();
            i = (a(entry.getValue()) ^ a(entry.getKey())) + i2;
        }
    }

    public final V put(K k, V v) {
        d();
        aj.a((Object) k);
        aj.a((Object) v);
        return super.put(k, v);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        d();
        for (Object next : map.keySet()) {
            aj.a(next);
            aj.a(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        d();
        return super.remove(obj);
    }
}
