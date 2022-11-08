package org.a.a.a;

public abstract class b<T> extends a<T> {
    static final /* synthetic */ boolean i = (!b.class.desiredAssertionStatus());
    protected int d = 0;
    protected T e;
    public T f = null;
    protected int g;
    protected int h = 0;

    public abstract boolean b(T t);

    public abstract T h();

    public T f() {
        T o = d(0);
        this.b++;
        if (this.b == this.a.size() && this.h == 0) {
            this.e = o;
            e();
        }
        return o;
    }

    public final void a() {
        a(1);
        f();
        this.d++;
    }

    private void a(int need) {
        int n = (((this.b + need) - 1) - this.a.size()) + 1;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                Object h = h();
                if (b(h)) {
                    this.f = h;
                }
                this.a.add(h);
            }
        }
    }

    public final int c() {
        throw new UnsupportedOperationException("streams are of unknown size");
    }

    public final T e(int k) {
        if (k == 0) {
            return null;
        }
        if (k < 0) {
            int i = -k;
            if (i || i > 0) {
                i = this.b - i;
                if (i == -1) {
                    return this.e;
                }
                if (i >= 0) {
                    return this.a.get(i);
                }
                if (i < -1) {
                    throw new UnsupportedOperationException("can't look more than one token before the beginning of this stream's buffer");
                }
                throw new UnsupportedOperationException("can't look past the end of this stream's buffer using LB(int)");
            }
            throw new AssertionError();
        }
        a(k);
        if ((this.b + k) - 1 > this.a.size()) {
            return this.f;
        }
        return d(k - 1);
    }

    public final int b() {
        return this.d;
    }

    public final int d() {
        this.h++;
        this.g = this.b;
        return this.g;
    }

    public final void c(int marker) {
    }

    public final void b(int marker) {
        this.h--;
        this.d -= this.b - marker;
        this.b = marker;
    }
}
