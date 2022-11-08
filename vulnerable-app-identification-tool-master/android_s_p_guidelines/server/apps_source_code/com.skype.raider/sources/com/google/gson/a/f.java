package com.google.gson.a;

import java.math.BigDecimal;

public final class f extends Number {
    private final String a;

    public f(String value) {
        this.a = value;
    }

    public final int intValue() {
        try {
            return Integer.parseInt(this.a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.a);
            } catch (NumberFormatException e2) {
                return new BigDecimal(this.a).intValue();
            }
        }
    }

    public final long longValue() {
        try {
            return Long.parseLong(this.a);
        } catch (NumberFormatException e) {
            return new BigDecimal(this.a).longValue();
        }
    }

    public final float floatValue() {
        return Float.parseFloat(this.a);
    }

    public final double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public final String toString() {
        return this.a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f other = (f) obj;
        if (this.a == other.a || this.a.equals(other.a)) {
            return true;
        }
        return false;
    }
}
