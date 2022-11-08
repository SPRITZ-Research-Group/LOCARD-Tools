package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

final class ep implements ba {
    private final /* synthetic */ String a;
    private final /* synthetic */ eo b;

    ep(eo eoVar, String str) {
        this.b = eoVar;
        this.a = str;
    }

    public final void a(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.b.a(i, th, bArr, this.a);
    }
}
