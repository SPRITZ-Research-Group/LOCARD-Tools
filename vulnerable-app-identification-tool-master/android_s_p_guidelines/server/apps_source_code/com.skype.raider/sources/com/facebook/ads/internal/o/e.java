package com.facebook.ads.internal.o;

import com.facebook.ads.internal.h.c;

class e {
    private final a a;
    private final c b;
    private final String c;
    private final String d;
    private final String e;

    enum a {
        UNKNOWN,
        ERROR,
        ADS
    }

    e(a aVar) {
        this(aVar, null, null, null, null);
    }

    e(a aVar, c cVar, String str, String str2, String str3) {
        this.a = aVar;
        this.b = cVar;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public c a() {
        return this.b;
    }

    final a b() {
        return this.a;
    }

    final String c() {
        return this.c;
    }

    final String d() {
        return this.d;
    }

    final String e() {
        return this.e;
    }
}
