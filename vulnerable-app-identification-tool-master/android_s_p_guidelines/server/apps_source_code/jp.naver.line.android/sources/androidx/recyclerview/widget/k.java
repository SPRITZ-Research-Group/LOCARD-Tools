package androidx.recyclerview.widget;

final class k {
    long a = 0;
    k b;

    k() {
    }

    final void a(int i) {
        k kVar = this;
        while (i >= 64) {
            kVar.a();
            kVar = kVar.b;
            i -= 64;
        }
        kVar.a |= 1 << i;
    }

    private void a() {
        if (this.b == null) {
            this.b = new k();
        }
    }

    final void b(int i) {
        k kVar = this;
        while (i >= 64) {
            if (kVar.b != null) {
                kVar = kVar.b;
                i -= 64;
            } else {
                return;
            }
        }
        kVar.a &= (1 << i) ^ -1;
    }

    final boolean c(int i) {
        k kVar = this;
        while (i >= 64) {
            kVar.a();
            kVar = kVar.b;
            i -= 64;
        }
        return (kVar.a & (1 << i)) != 0;
    }

    final void a(int i, boolean z) {
        boolean z2 = z;
        k kVar = this;
        while (true) {
            if (i >= 64) {
                kVar.a();
                kVar = kVar.b;
                i -= 64;
            } else {
                boolean z3 = (kVar.a & Long.MIN_VALUE) != 0;
                long j = (1 << i) - 1;
                kVar.a = (kVar.a & j) | (((j ^ -1) & kVar.a) << 1);
                if (z2) {
                    kVar.a(i);
                } else {
                    kVar.b(i);
                }
                if (z3 || kVar.b != null) {
                    kVar.a();
                    kVar = kVar.b;
                    z2 = z3;
                    i = 0;
                } else {
                    return;
                }
            }
        }
    }

    final boolean d(int i) {
        k kVar = this;
        while (i >= 64) {
            kVar.a();
            kVar = kVar.b;
            i -= 64;
        }
        long j = 1 << i;
        boolean z = (kVar.a & j) != 0;
        kVar.a &= j ^ -1;
        j--;
        kVar.a = (kVar.a & j) | Long.rotateRight((j ^ -1) & kVar.a, 1);
        if (kVar.b != null) {
            if (kVar.b.c(0)) {
                kVar.a(63);
            }
            kVar.b.d(0);
        }
        return z;
    }

    final int e(int i) {
        if (this.b == null) {
            if (i >= 64) {
                return Long.bitCount(this.a);
            }
            return Long.bitCount(this.a & ((1 << i) - 1));
        } else if (i < 64) {
            return Long.bitCount(this.a & ((1 << i) - 1));
        } else {
            return this.b.e(i - 64) + Long.bitCount(this.a);
        }
    }

    public final String toString() {
        if (this.b == null) {
            return Long.toBinaryString(this.a);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.b.toString());
        stringBuilder.append("xx");
        stringBuilder.append(Long.toBinaryString(this.a));
        return stringBuilder.toString();
    }
}
