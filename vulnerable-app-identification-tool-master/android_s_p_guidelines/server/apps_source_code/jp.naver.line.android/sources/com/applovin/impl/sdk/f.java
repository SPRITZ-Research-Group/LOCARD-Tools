package com.applovin.impl.sdk;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

class f extends LinkedHashMap<String, e> {
    final /* synthetic */ c a;

    private f(c cVar) {
        this.a = cVar;
    }

    /* synthetic */ f(c cVar, d dVar) {
        this(cVar);
    }

    protected boolean removeEldestEntry(Entry<String, e> entry) {
        return size() > ((Integer) this.a.a.get(ea.dK)).intValue();
    }
}
