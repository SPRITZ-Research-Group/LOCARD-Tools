package com.a.a.a.b;

import com.a.a.a.j;
import java.io.Serializable;

public final class f implements j, Serializable {
    protected final String a;

    public f(String str) {
        this.a = str;
    }

    public final String a() {
        return this.a;
    }

    public final String toString() {
        return this.a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.a.equals(((f) obj).a);
    }
}
