package androidx.appcompat.widget;

import android.content.pm.ResolveInfo;
import java.math.BigDecimal;

public final class p implements Comparable<p> {
    public final ResolveInfo a;
    public float b;

    public final /* synthetic */ int compareTo(Object obj) {
        return Float.floatToIntBits(((p) obj).b) - Float.floatToIntBits(this.b);
    }

    public p(ResolveInfo resolveInfo) {
        this.a = resolveInfo;
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.b) + 31;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Float.floatToIntBits(this.b) == Float.floatToIntBits(((p) obj).b);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append("resolveInfo:");
        stringBuilder.append(this.a.toString());
        stringBuilder.append("; weight:");
        stringBuilder.append(new BigDecimal((double) this.b));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
