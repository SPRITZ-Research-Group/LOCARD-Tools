package com.a.a.a;

import java.io.Serializable;

public final class d implements Serializable {
    public static final d a = new d("N/A");
    final long b = -1;
    final long c = -1;
    final int d = -1;
    final int e = -1;
    final transient Object f;

    private d(Object obj) {
        this.f = obj;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(80);
        stringBuilder.append("[Source: ");
        if (this.f == null) {
            stringBuilder.append("UNKNOWN");
        } else {
            stringBuilder.append(this.f.toString());
        }
        stringBuilder.append("; line: ");
        stringBuilder.append(this.d);
        stringBuilder.append(", column: ");
        stringBuilder.append(this.e);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public final int hashCode() {
        return ((((this.f == null ? 1 : this.f.hashCode()) ^ this.d) + this.e) ^ ((int) this.c)) + ((int) this.b);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (this.f == null) {
            if (dVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(dVar.f)) {
            return false;
        }
        if (this.d == dVar.d && this.e == dVar.e && this.c == dVar.c && this.b == dVar.b) {
            return true;
        }
        return false;
    }
}
