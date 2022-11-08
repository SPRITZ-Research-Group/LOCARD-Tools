package com.microsoft.skype.b;

import java.util.Arrays;

public final class a<A, B> {
    public final A a;
    public final B b;

    public static <A, B> a<A, B> a(A a, B b) {
        return new a(a, b);
    }

    public a(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public final int hashCode() {
        return ((this.a.hashCode() + 31) * 31) + this.b.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        a<?, ?> other = (a) obj;
        if (this.a.equals(other.a) && this.b.equals(other.b)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return Arrays.asList(new Object[]{this.a, this.b}).toString();
    }
}
