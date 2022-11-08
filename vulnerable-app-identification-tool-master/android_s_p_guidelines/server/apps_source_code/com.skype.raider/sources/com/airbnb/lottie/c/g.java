package com.airbnb.lottie.c;

import android.support.annotation.Nullable;
import android.support.v4.util.i;

public final class g<T> {
    @Nullable
    T a;
    @Nullable
    T b;

    public final void a(T first, T second) {
        this.a = first;
        this.b = second;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof i)) {
            return false;
        }
        i<?, ?> p = (i) o;
        if (b(p.a, this.a) && b(p.b, this.b)) {
            return true;
        }
        return false;
    }

    private static boolean b(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.a == null ? 0 : this.a.hashCode();
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        return "Pair{" + String.valueOf(this.a) + " " + String.valueOf(this.b) + "}";
    }
}
