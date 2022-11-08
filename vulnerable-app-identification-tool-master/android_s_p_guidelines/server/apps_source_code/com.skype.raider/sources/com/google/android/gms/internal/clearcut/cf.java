package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map.Entry;

final class cf extends cl {
    private final /* synthetic */ cc a;

    private cf(cc ccVar) {
        this.a = ccVar;
        super(ccVar, (byte) 0);
    }

    /* synthetic */ cf(cc ccVar, byte b) {
        this(ccVar);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new ce(this.a, (byte) 0);
    }
}
