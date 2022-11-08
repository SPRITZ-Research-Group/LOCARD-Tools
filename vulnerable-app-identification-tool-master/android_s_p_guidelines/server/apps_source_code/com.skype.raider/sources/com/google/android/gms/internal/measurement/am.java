package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class am<V> {
    private final ga<V> a;
    private final String b;

    private am(String str, ga<V> gaVar) {
        ab.a((Object) gaVar);
        this.a = gaVar;
        this.b = str;
    }

    static am<Double> a(String str) {
        return new am(str, new gh(al.V, str, Double.valueOf(-3.0d)));
    }

    static am<Integer> a(String str, int i) {
        return new am(str, new gf(al.V, str, Integer.valueOf(i)));
    }

    static am<Long> a(String str, long j) {
        return new am(str, new ge(al.V, str, Long.valueOf(j)));
    }

    static am<String> a(String str, String str2) {
        return new am(str, new gi(al.V, str, str2));
    }

    static am<Boolean> a(String str, boolean z) {
        return new am(str, new gg(al.V, str, Boolean.valueOf(z)));
    }

    public final V a(V v) {
        return v != null ? v : this.a.a();
    }

    public final String a() {
        return this.b;
    }

    public final V b() {
        return this.a.a();
    }
}
