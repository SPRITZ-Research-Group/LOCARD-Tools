package com.facebook.ads.internal.adapters.a;

import java.io.Serializable;

public final class e implements Serializable {
    private final String a;
    private final String b;

    e(String str, String str2) {
        this.a = a(str);
        this.b = a(str2);
    }

    private static String a(String str) {
        return "null".equalsIgnoreCase(str) ? "" : str;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }
}
