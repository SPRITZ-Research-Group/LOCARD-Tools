package android.support.v4.util;

public final class i<F, S> {
    public final F a;
    public final S b;

    public i(F first, S second) {
        this.a = first;
        this.b = second;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof i)) {
            return false;
        }
        i<?, ?> p = (i) o;
        if (a(p.a, this.a) && a(p.b, this.b)) {
            return true;
        }
        return false;
    }

    private static boolean a(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
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
        return "Pair{" + String.valueOf(this.a) + " " + String.valueOf(this.b) + "}";
    }
}
