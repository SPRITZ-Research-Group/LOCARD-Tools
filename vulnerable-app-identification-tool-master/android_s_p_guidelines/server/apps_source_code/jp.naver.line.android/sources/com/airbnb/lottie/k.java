package com.airbnb.lottie;

import java.util.Arrays;

public final class k<V> {
    private final V a;
    private final Throwable b;

    public k(V v) {
        this.a = v;
        this.b = null;
    }

    public k(Throwable th) {
        this.b = th;
        this.a = null;
    }

    public final V a() {
        return this.a;
    }

    public final Throwable b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (this.a != null && this.a.equals(kVar.a)) {
            return true;
        }
        if (this.b == null || kVar.b == null) {
            return false;
        }
        return this.b.toString().equals(this.b.toString());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b});
    }
}
