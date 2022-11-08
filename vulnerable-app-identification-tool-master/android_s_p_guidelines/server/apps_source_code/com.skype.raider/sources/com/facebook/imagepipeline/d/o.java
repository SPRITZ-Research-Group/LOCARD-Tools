package com.facebook.imagepipeline.d;

import com.facebook.common.f.a;
import com.facebook.common.internal.i;

public final class o<K, V> implements p<K, V> {
    private final p<K, V> a;
    private final r b;

    public o(p<K, V> delegate, r tracker) {
        this.a = delegate;
        this.b = tracker;
    }

    public final a<V> a(K key) {
        return this.a.a((Object) key);
    }

    public final a<V> a(K key, a<V> value) {
        return this.a.a(key, value);
    }

    public final int a(i<K> predicate) {
        return this.a.a((i) predicate);
    }

    public final boolean b(i<K> predicate) {
        return this.a.b(predicate);
    }
}
