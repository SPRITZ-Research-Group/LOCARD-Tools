package com.facebook.ads.internal.r;

import android.text.TextUtils;

public final class c {
    private final a a;
    private final String b;

    public c(int i, String str) {
        this(a.a(i), str);
    }

    public c(a aVar, String str) {
        if (TextUtils.isEmpty(str)) {
            str = aVar.b();
        }
        this.a = aVar;
        this.b = str;
    }

    public static c a(a aVar, String str) {
        return new c(aVar, str);
    }

    public static c a(d dVar) {
        return new c(dVar.a(), dVar.b());
    }

    public final a a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }
}
