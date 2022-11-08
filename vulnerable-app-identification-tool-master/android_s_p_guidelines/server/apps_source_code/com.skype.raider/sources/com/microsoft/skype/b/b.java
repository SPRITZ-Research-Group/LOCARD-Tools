package com.microsoft.skype.b;

import java.util.Arrays;

public final class b<A, B, C> {
    public final A a;
    public final B b;
    public final C c;

    public b(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public final int hashCode() {
        return ((((this.a.hashCode() + 31) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
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
        b<?, ?, ?> other = (b) obj;
        if (this.a.equals(other.a) && this.b.equals(other.b) && this.c.equals(other.c)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return Arrays.asList(new Object[]{this.a, this.b, this.c}).toString();
    }
}
