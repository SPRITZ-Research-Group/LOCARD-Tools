package com.facebook.ads.internal.adapters.a;

import java.io.Serializable;

public final class c implements Serializable {
    private final String a;
    private final String b;
    private final String c;

    public static class a {
        private String a;
        private String b;
        private String c;

        final a a(String str) {
            this.a = str;
            return this;
        }

        final c a() {
            return new c();
        }

        final a b(String str) {
            this.b = str;
            return this;
        }

        final a c(String str) {
            this.c = str;
            return this;
        }
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
    }

    /* synthetic */ c(a aVar, byte b) {
        this(aVar);
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }
}
