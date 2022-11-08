package com.facebook.ads.internal.adapters.a;

import java.io.Serializable;

public final class i implements Serializable {
    private final String a;
    private final String b;
    private final String c;
    private final String d;

    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;

        final a a(String str) {
            this.a = str;
            return this;
        }

        final i a() {
            return new i();
        }

        final a b(String str) {
            this.b = str;
            return this;
        }

        final a c(String str) {
            this.c = str;
            return this;
        }

        final a d(String str) {
            this.d = str;
            return this;
        }
    }

    private i(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
    }

    /* synthetic */ i(a aVar, byte b) {
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

    public final String d() {
        return this.d;
    }
}
