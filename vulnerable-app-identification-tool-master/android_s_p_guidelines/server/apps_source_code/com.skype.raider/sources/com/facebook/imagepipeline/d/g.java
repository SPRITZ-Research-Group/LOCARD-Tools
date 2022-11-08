package com.facebook.imagepipeline.d;

import com.facebook.common.internal.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class g<K, V> {
    private final v<V> a;
    @GuardedBy("this")
    private final LinkedHashMap<K, V> b = new LinkedHashMap();
    @GuardedBy("this")
    private int c = 0;

    public g(v<V> valueDescriptor) {
        this.a = valueDescriptor;
    }

    public final synchronized int a() {
        return this.b.size();
    }

    public final synchronized int b() {
        return this.c;
    }

    @Nullable
    public final synchronized K c() {
        return this.b.isEmpty() ? null : this.b.keySet().iterator().next();
    }

    public final synchronized ArrayList<Entry<K, V>> a(@Nullable i<K> predicate) {
        ArrayList<Entry<K, V>> matchingEntries;
        matchingEntries = new ArrayList(this.b.entrySet().size());
        for (Entry<K, V> entry : this.b.entrySet()) {
            if (predicate == null || predicate.a(entry.getKey())) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

    public final synchronized boolean a(K key) {
        return this.b.containsKey(key);
    }

    @Nullable
    public final synchronized V b(K key) {
        return this.b.get(key);
    }

    @Nullable
    public final synchronized V a(K key, V value) {
        V oldValue;
        oldValue = this.b.remove(key);
        this.c -= d(oldValue);
        this.b.put(key, value);
        this.c += d(value);
        return oldValue;
    }

    @Nullable
    public final synchronized V c(K key) {
        V oldValue;
        oldValue = this.b.remove(key);
        this.c -= d(oldValue);
        return oldValue;
    }

    public final synchronized ArrayList<V> b(@Nullable i<K> predicate) {
        ArrayList<V> oldValues;
        oldValues = new ArrayList();
        Iterator<Entry<K, V>> iterator = this.b.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = (Entry) iterator.next();
            if (predicate == null || predicate.a(entry.getKey())) {
                oldValues.add(entry.getValue());
                this.c -= d(entry.getValue());
                iterator.remove();
            }
        }
        return oldValues;
    }

    private int d(V value) {
        return value == null ? 0 : this.a.a(value);
    }
}
