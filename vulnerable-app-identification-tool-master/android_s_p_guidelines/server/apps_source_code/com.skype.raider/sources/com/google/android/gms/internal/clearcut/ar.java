package com.google.android.gms.internal.clearcut;

import java.util.Map.Entry;

final class ar<K> implements Entry<K, Object> {
    private Entry<K, aq> a;

    private ar(Entry<K, aq> entry) {
        this.a = entry;
    }

    /* synthetic */ ar(Entry entry, byte b) {
        this(entry);
    }

    public final aq a() {
        return (aq) this.a.getValue();
    }

    public final K getKey() {
        return this.a.getKey();
    }

    public final Object getValue() {
        return ((aq) this.a.getValue()) == null ? null : aq.a();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof bk) {
            return ((aq) this.a.getValue()).a((bk) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
