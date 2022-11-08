package org.a.a;

public final class h extends d {
    protected int f = 0;

    public h(x tokenSource) {
        super(tokenSource);
    }

    public final void a() {
        if (this.d == -1) {
            e();
        }
        do {
            this.d++;
            d(this.d);
        } while (((w) this.b.get(this.d)).e() != this.f);
    }

    protected final w f(int k) {
        if (k == 0 || this.d - k < 0) {
            return null;
        }
        int i = this.d;
        for (int n = 1; n <= k; n++) {
            i--;
            while (i >= 0 && ((w) this.b.get(i)).e() != this.f) {
                i--;
            }
        }
        if (i < 0) {
            return null;
        }
        return (w) this.b.get(i);
    }

    public final w g(int k) {
        if (this.d == -1) {
            e();
        }
        if (k == 0) {
            return null;
        }
        if (k < 0) {
            return f(-k);
        }
        int i = this.d;
        for (int n = 1; n < k; n++) {
            i++;
            d(i);
            while (((w) this.b.get(i)).e() != this.f) {
                i++;
                d(i);
            }
        }
        if (i > this.e) {
            this.e = i;
        }
        return (w) this.b.get(i);
    }

    protected final void e() {
        this.d = 0;
        d(0);
        int i = 0;
        while (((w) this.b.get(i)).e() != this.f) {
            i++;
            d(i);
        }
        this.d = i;
    }
}
