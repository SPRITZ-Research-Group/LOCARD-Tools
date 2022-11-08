package defpackage;

/* renamed from: aczw */
final class aczw<V> {
    static final aczw<Object> a = new aczw();
    private final long b;
    private final V c;
    private final aczw<V> d;
    private final aczw<V> e;
    private final int f;

    private aczw() {
        this.f = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    private aczw(long j, V v, aczw<V> aczw, aczw<V> aczw2) {
        this.b = j;
        this.c = v;
        this.d = aczw;
        this.e = aczw2;
        this.f = (aczw.f + 1) + aczw2.f;
    }

    private aczw<V> b(long j) {
        if (this.f == 0 || j == this.b) {
            return this;
        }
        return new aczw(j, this.c, this.d, this.e);
    }

    final V a(long j) {
        long j2 = j;
        aczw aczw = this;
        while (aczw.f != 0) {
            aczw aczw2;
            long j3;
            if (j2 < aczw.b) {
                aczw2 = aczw.d;
                j3 = aczw.b;
            } else if (j2 <= aczw.b) {
                return aczw.c;
            } else {
                aczw2 = aczw.e;
                j3 = aczw.b;
            }
            j2 -= j3;
            aczw = aczw2;
        }
        return null;
    }

    final aczw<V> a(long j, V v) {
        if (this.f == 0) {
            return new aczw(j, v, this, this);
        }
        if (j < this.b) {
            return a(this.d.a(j - this.b, (Object) v), this.e);
        }
        if (j > this.b) {
            return a(this.d, this.e.a(j - this.b, (Object) v));
        }
        if (v == this.c) {
            return this;
        }
        return new aczw(j, v, this.d, this.e);
    }

    private aczw<V> a(aczw<V> aczw, aczw<V> aczw2) {
        if (aczw == this.d && aczw2 == this.e) {
            return this;
        }
        return aczw.a(this.b, this.c, aczw, aczw2);
    }

    private static <V> aczw<V> a(long j, V v, aczw<V> aczw, aczw<V> aczw2) {
        aczw<V> aczw3 = aczw;
        aczw<V> aczw4 = aczw2;
        if (aczw3.f + aczw4.f > 1) {
            aczw aczw5;
            if (aczw3.f >= aczw4.f * 5) {
                aczw aczw6 = aczw3.d;
                aczw5 = aczw3.e;
                if (aczw5.f < aczw6.f * 2) {
                    return new aczw(aczw3.b + j, aczw3.c, aczw6, new aczw(-aczw3.b, v, aczw5.b(aczw5.b + aczw3.b), aczw2));
                }
                aczw aczw7 = aczw5.d;
                aczw aczw8 = aczw5.e;
                return new aczw((aczw5.b + aczw3.b) + j, aczw5.c, new aczw(-aczw5.b, aczw3.c, aczw6, aczw7.b(aczw7.b + aczw5.b)), new aczw((-aczw3.b) - aczw5.b, v, aczw8.b((aczw8.b + aczw5.b) + aczw3.b), aczw2));
            } else if (aczw4.f >= aczw3.f * 5) {
                aczw aczw9 = aczw4.d;
                aczw aczw10 = aczw4.e;
                if (aczw9.f < aczw10.f * 2) {
                    return new aczw(aczw4.b + j, aczw4.c, new aczw(-aczw4.b, v, aczw, aczw9.b(aczw9.b + aczw4.b)), aczw10);
                }
                aczw5 = aczw9.d;
                aczw aczw11 = aczw9.e;
                long j2 = (aczw9.b + aczw4.b) + j;
                return new aczw(j2, aczw9.c, new aczw((-aczw4.b) - aczw9.b, v, aczw, aczw5.b((aczw5.b + aczw9.b) + aczw4.b)), new aczw(-aczw9.b, aczw4.c, aczw11.b(aczw11.b + aczw9.b), aczw10));
            }
        }
        return new aczw(j, v, aczw, aczw2);
    }
}
