package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import defpackage.acry;

public final class ApproximationBounds<T> {
    private final T lower;
    private final T upper;

    public final T component1() {
        return this.lower;
    }

    public final T component2() {
        return this.upper;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ApproximationBounds) {
                ApproximationBounds approximationBounds = (ApproximationBounds) obj;
                if (acry.a(this.lower, approximationBounds.lower)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.lower;
        int i = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.upper;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("ApproximationBounds(lower=");
        stringBuilder.append(this.lower);
        stringBuilder.append(", upper=");
        stringBuilder.append(this.upper);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public ApproximationBounds(T t, T t2) {
        this.lower = t;
        this.upper = t2;
    }

    public final T getLower() {
        return this.lower;
    }

    public final T getUpper() {
        return this.upper;
    }
}
