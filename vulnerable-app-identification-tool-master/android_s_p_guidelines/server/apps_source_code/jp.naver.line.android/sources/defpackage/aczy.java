package defpackage;

import java.io.Serializable;

/* renamed from: aczy */
final class aczy<K, V> implements Serializable {
    public final K a;
    public final V b;

    public aczy(K k, V v) {
        this.a = k;
        this.b = v;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof aczy)) {
            return false;
        }
        aczy aczy = (aczy) obj;
        if (this.a != null ? !this.a.equals(aczy.a) : aczy.a != null) {
            if (this.b != null ? !this.b.equals(aczy.b) : aczy.b != null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.a == null ? 0 : this.a.hashCode();
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("=");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }
}
