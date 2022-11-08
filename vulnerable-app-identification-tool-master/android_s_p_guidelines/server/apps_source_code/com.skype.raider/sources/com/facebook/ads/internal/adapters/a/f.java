package com.facebook.ads.internal.adapters.a;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public final class f implements Serializable {
    private final byte[] a;
    private final String b;
    private final List<String> c;
    private String d;

    f(byte[] bArr, String str, List<String> list) {
        this.a = bArr;
        this.b = str;
        this.c = list;
    }

    public final String a() {
        return this.d;
    }

    final void a(String str) {
        this.d = str;
    }

    public final byte[] b() {
        return this.a;
    }

    public final String c() {
        return this.b;
    }

    public final List<String> d() {
        return Collections.unmodifiableList(this.c);
    }
}
