package com.facebook.ads.internal.r;

public final class d extends Exception {
    private final a a;
    private final String b;

    public d(a aVar, String str) {
        this(aVar, str, null);
    }

    public d(a aVar, String str, Throwable th) {
        super(str, th);
        this.a = aVar;
        this.b = str;
    }

    public final a a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }
}
