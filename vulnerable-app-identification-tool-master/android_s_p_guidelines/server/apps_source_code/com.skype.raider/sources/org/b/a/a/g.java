package org.b.a.a;

import org.a.a.w;

public final class g {
    public String a;
    public int b;
    public w c;
    public Object d;
    public e e;

    public g(String name) {
        this.a = name;
    }

    public final int hashCode() {
        return this.a.hashCode() + this.c.hashCode();
    }

    public final boolean equals(Object o) {
        if (o == null || !(o instanceof g)) {
            return false;
        }
        g other = (g) o;
        if (!this.a.equals(other.a)) {
            return false;
        }
        if (this.c != null && other.c == null) {
            return false;
        }
        if (this.c != null || other.c == null) {
            return true;
        }
        return false;
    }

    public final String toString() {
        if (this.c != null) {
            return this.a + "=" + this.c.b();
        }
        return this.a;
    }
}
