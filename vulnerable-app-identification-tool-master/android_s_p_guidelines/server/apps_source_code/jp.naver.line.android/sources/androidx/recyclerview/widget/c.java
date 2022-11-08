package androidx.recyclerview.widget;

import com.google.android.gms.analytics.ecommerce.ProductAction;

final class c {
    int a;
    int b;
    Object c;
    int d;

    c(int i, int i2, int i3, Object obj) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.c = obj;
    }

    public final String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append("[");
        int i = this.a;
        if (i == 4) {
            str = "up";
        } else if (i != 8) {
            switch (i) {
                case 1:
                    str = ProductAction.ACTION_ADD;
                    break;
                case 2:
                    str = "rm";
                    break;
                default:
                    str = "??";
                    break;
            }
        } else {
            str = "mv";
        }
        stringBuilder.append(str);
        stringBuilder.append(",s:");
        stringBuilder.append(this.b);
        stringBuilder.append("c:");
        stringBuilder.append(this.d);
        stringBuilder.append(",p:");
        stringBuilder.append(this.c);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a != cVar.a) {
            return false;
        }
        if (this.a == 8 && Math.abs(this.d - this.b) == 1 && this.d == cVar.b && this.b == cVar.d) {
            return true;
        }
        if (this.d != cVar.d || this.b != cVar.b) {
            return false;
        }
        if (this.c != null) {
            if (!this.c.equals(cVar.c)) {
                return false;
            }
        } else if (cVar.c != null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((this.a * 31) + this.b) * 31) + this.d;
    }
}
