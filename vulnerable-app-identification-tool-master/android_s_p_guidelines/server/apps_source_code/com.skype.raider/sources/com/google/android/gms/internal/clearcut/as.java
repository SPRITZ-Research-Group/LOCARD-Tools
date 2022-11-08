package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map.Entry;

final class as<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> a;

    public as(Iterator<Entry<K, Object>> it) {
        this.a = it;
    }

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.a.next();
        return entry.getValue() instanceof aq ? new ar(entry, (byte) 0) : entry;
    }

    public final void remove() {
        this.a.remove();
    }
}
