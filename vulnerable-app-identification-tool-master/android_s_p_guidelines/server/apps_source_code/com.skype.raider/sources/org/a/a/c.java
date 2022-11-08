package org.a.a;

public final class c implements Cloneable {
    protected long[] a;

    public c() {
        this((byte) 0);
    }

    public c(long[] bits_) {
        this.a = bits_;
    }

    private c(byte b) {
        this.a = new long[1];
    }

    public final void a(c a) {
        if (a != null) {
            if (a.a.length > this.a.length) {
                int length = a.a.length;
                Object obj = new long[length];
                System.arraycopy(this.a, 0, obj, 0, Math.min(length, this.a.length));
                this.a = obj;
            }
            for (int i = Math.min(this.a.length, a.a.length) - 1; i >= 0; i--) {
                long[] jArr = this.a;
                jArr[i] = jArr[i] | a.a[i];
            }
        }
    }

    private static final long b(int bitNumber) {
        return 1 << (bitNumber & 63);
    }

    public final Object clone() {
        try {
            c s = (c) super.clone();
            s.a = new long[this.a.length];
            System.arraycopy(this.a, 0, s.a, 0, this.a.length);
            return s;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    public final boolean equals(Object other) {
        if (other == null || !(other instanceof c)) {
            return false;
        }
        int i;
        c otherSet = (c) other;
        int n = Math.min(this.a.length, otherSet.a.length);
        for (i = 0; i < n; i++) {
            if (this.a[i] != otherSet.a[i]) {
                return false;
            }
        }
        if (this.a.length > n) {
            for (i = n + 1; i < this.a.length; i++) {
                if (this.a[i] != 0) {
                    return false;
                }
            }
        } else if (otherSet.a.length > n) {
            for (i = n + 1; i < otherSet.a.length; i++) {
                if (otherSet.a[i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public final boolean a(int el) {
        if (el < 0) {
            return false;
        }
        int n = el >> 6;
        if (n >= this.a.length || (this.a[n] & b(el)) == 0) {
            return false;
        }
        return true;
    }

    public final void a() {
        if (this.a.length > 0) {
            long[] jArr = this.a;
            jArr[0] = jArr[0] & (b(1) ^ -1);
        }
    }

    public final String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String str = ",";
        stringBuilder.append('{');
        int i2 = 0;
        while (i < (this.a.length << 6)) {
            if (a(i)) {
                if (i > 0 && i2 != 0) {
                    stringBuilder.append(str);
                }
                stringBuilder.append(i);
                i2 = 1;
            }
            i++;
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
