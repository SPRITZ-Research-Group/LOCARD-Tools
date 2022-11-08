package androidx.appcompat.widget;

import android.content.ComponentName;
import java.math.BigDecimal;

public final class r {
    public final ComponentName a;
    public final long b;
    public final float c;

    public r(String str, long j, float f) {
        this(ComponentName.unflattenFromString(str), j, f);
    }

    public r(ComponentName componentName, long j, float f) {
        this.a = componentName;
        this.b = j;
        this.c = f;
    }

    public final int hashCode() {
        return (((((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + Float.floatToIntBits(this.c);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        if (this.a == null) {
            if (rVar.a != null) {
                return false;
            }
        } else if (!this.a.equals(rVar.a)) {
            return false;
        }
        return this.b == rVar.b && Float.floatToIntBits(this.c) == Float.floatToIntBits(rVar.c);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append("; activity:");
        stringBuilder.append(this.a);
        stringBuilder.append("; time:");
        stringBuilder.append(this.b);
        stringBuilder.append("; weight:");
        stringBuilder.append(new BigDecimal((double) this.c));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
