package com.google.android.gms.common.api.internal;

import com.google.android.gms.c.h;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.api.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class l {
    private final Map<BasePendingResult<?>, Boolean> a = Collections.synchronizedMap(new WeakHashMap());
    private final Map<h<?>, Boolean> b = Collections.synchronizedMap(new WeakHashMap());

    private final void a(boolean z, Status status) {
        synchronized (this.a) {
            Map hashMap = new HashMap(this.a);
        }
        synchronized (this.b) {
            Map hashMap2 = new HashMap(this.b);
        }
        for (Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).b(status);
            }
        }
        for (Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((h) entry2.getKey()).b(new b(status));
            }
        }
    }

    final <TResult> void a(h<TResult> hVar, boolean z) {
        this.b.put(hVar, Boolean.valueOf(z));
        hVar.a().a(new n(this, hVar));
    }

    final void a(BasePendingResult<? extends i> basePendingResult, boolean z) {
        this.a.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.a(new m(this, basePendingResult));
    }

    final boolean a() {
        return (this.a.isEmpty() && this.b.isEmpty()) ? false : true;
    }

    public final void b() {
        a(false, d.a);
    }

    public final void c() {
        a(true, al.a);
    }
}
