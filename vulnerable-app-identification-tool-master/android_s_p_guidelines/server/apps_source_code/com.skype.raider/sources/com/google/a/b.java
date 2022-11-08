package com.google.a;

public final class b {
    private final int a;
    private final int b;

    public final int a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final boolean equals(Object other) {
        if (!(other instanceof b)) {
            return false;
        }
        b d = (b) other;
        if (this.a == d.a && this.b == d.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.a * 32713) + this.b;
    }

    public final String toString() {
        return this.a + "x" + this.b;
    }
}
