package com.bumptech.glide.load;

import defpackage.alt;
import java.security.MessageDigest;

public final class i<T> {
    private static final j<Object> a = new j<Object>() {
        public final void a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    };
    private final T b;
    private final j<T> c;
    private final String d;
    private volatile byte[] e;

    public static <T> i<T> a(String str) {
        return new i(str, null, a);
    }

    public static <T> i<T> a(String str, T t) {
        return new i(str, t, a);
    }

    public static <T> i<T> a(String str, T t, j<T> jVar) {
        return new i(str, t, jVar);
    }

    private i(String str, T t, j<T> jVar) {
        this.d = alt.a(str);
        this.b = t;
        this.c = (j) alt.a((Object) jVar, "Argument must not be null");
    }

    public final T a() {
        return this.b;
    }

    public final void a(T t, MessageDigest messageDigest) {
        j jVar = this.c;
        if (this.e == null) {
            this.e = this.d.getBytes(g.a);
        }
        jVar.a(this.e, t, messageDigest);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof i)) {
            return false;
        }
        return this.d.equals(((i) obj).d);
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("Option{key='");
        stringBuilder.append(this.d);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
