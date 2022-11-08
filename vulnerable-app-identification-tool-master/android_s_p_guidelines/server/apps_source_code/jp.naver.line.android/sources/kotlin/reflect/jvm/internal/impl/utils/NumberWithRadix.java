package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acry;

public final class NumberWithRadix {
    private final String number;
    private final int radix;

    public final String component1() {
        return this.number;
    }

    public final int component2() {
        return this.radix;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NumberWithRadix) {
                NumberWithRadix numberWithRadix = (NumberWithRadix) obj;
                if (acry.a(this.number, numberWithRadix.number)) {
                    if ((this.radix == numberWithRadix.radix ? 1 : null) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        String str = this.number;
        return ((str != null ? str.hashCode() : 0) * 31) + this.radix;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("NumberWithRadix(number=");
        stringBuilder.append(this.number);
        stringBuilder.append(", radix=");
        stringBuilder.append(this.radix);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public NumberWithRadix(String str, int i) {
        this.number = str;
        this.radix = i;
    }
}
