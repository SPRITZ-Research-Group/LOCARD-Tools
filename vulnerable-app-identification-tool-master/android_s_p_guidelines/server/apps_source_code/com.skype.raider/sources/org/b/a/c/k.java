package org.b.a.c;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class k<K, V> extends LinkedHashMap<K, List<V>> {
    public final void a(K key, V value) {
        List<V> elementsForKey = (List) get(key);
        if (elementsForKey == null) {
            elementsForKey = new ArrayList();
            super.put(key, elementsForKey);
        }
        elementsForKey.add(value);
    }
}
