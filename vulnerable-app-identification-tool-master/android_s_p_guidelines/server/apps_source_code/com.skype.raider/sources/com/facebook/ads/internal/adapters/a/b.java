package com.facebook.ads.internal.adapters.a;

import android.support.annotation.Nullable;
import java.io.Serializable;

public final class b implements Serializable {
    private final String a;
    private final int b;
    private final int c;
    private final boolean d;
    private final boolean e;
    private final String f;
    private final int g;
    private final int h;
    @Nullable
    private final j i;
    private String j;

    static class a {
        private String a;
        private int b;
        private int c;
        private boolean d;
        private boolean e;
        private String f;
        private int g;
        private int h;
        private j i;

        a() {
        }

        final a a(int i) {
            this.b = i;
            return this;
        }

        final a a(@Nullable j jVar) {
            this.i = jVar;
            return this;
        }

        final a a(String str) {
            this.a = str;
            return this;
        }

        final a a(boolean z) {
            this.d = z;
            return this;
        }

        final b a() {
            return new b();
        }

        final a b(int i) {
            this.c = i;
            return this;
        }

        final a b(String str) {
            this.f = str;
            return this;
        }

        final a b(boolean z) {
            this.e = z;
            return this;
        }

        final a c(int i) {
            this.g = i;
            return this;
        }

        final a d(int i) {
            this.h = i;
            return this;
        }
    }

    private b(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
    }

    /* synthetic */ b(a aVar, byte b) {
        this(aVar);
    }

    public final String a() {
        return this.a;
    }

    final void a(String str) {
        this.j = str;
    }

    public final String b() {
        return this.j;
    }

    public final int c() {
        return this.b;
    }

    public final boolean d() {
        return this.d;
    }

    public final boolean e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }

    public final int g() {
        return this.g;
    }

    public final int h() {
        return this.h;
    }

    @Nullable
    public final j i() {
        return this.i;
    }
}
