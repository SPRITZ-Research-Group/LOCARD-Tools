package defpackage;

/* renamed from: ouj */
public class ouj {
    protected final String q;

    public ouj(String str) {
        this(str, false);
    }

    protected ouj(String str, boolean z) {
        if (!(str == null || z)) {
            str = str.toLowerCase();
        }
        this.q = str;
    }

    public final String c() {
        return this.q;
    }

    public String toString() {
        return this.q;
    }

    public int hashCode() {
        return (this.q == null ? 0 : this.q.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ouj ouj = (ouj) obj;
        if (this.q == null) {
            if (ouj.q != null) {
                return false;
            }
        } else if (!this.q.equals(ouj.q)) {
            return false;
        }
        return true;
    }
}
