package org.a.a;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class d implements y {
    protected x a;
    protected List<w> b = new ArrayList(100);
    protected int c;
    protected int d = -1;
    protected int e = -1;

    public d(x tokenSource) {
        this.a = tokenSource;
    }

    public final int b() {
        return this.d;
    }

    public final int d() {
        if (this.d == -1) {
            e();
        }
        this.c = this.d;
        return this.c;
    }

    public final void c(int marker) {
    }

    public final int c() {
        return this.b.size();
    }

    public void a() {
        if (this.d == -1) {
            e();
        }
        this.d++;
        d(this.d);
    }

    protected final void d(int i) {
        int n = (i - this.b.size()) + 1;
        if (n > 0) {
            int i2 = 1;
            while (i2 <= n) {
                w a = this.a.a();
                a.f(this.b.size());
                this.b.add(a);
                if (a.a() != -1) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public final w e(int i) {
        if (i >= 0 && i < this.b.size()) {
            return (w) this.b.get(i);
        }
        throw new NoSuchElementException("token index " + i + " out of range 0.." + (this.b.size() - 1));
    }

    public final int a(int i) {
        return g(i).a();
    }

    protected w f(int k) {
        if (this.d - k < 0) {
            return null;
        }
        return (w) this.b.get(this.d - k);
    }

    public w g(int k) {
        if (this.d == -1) {
            e();
        }
        if (k == 0) {
            return null;
        }
        if (k < 0) {
            return f(-k);
        }
        int i = (this.d + k) - 1;
        d(i);
        if (i >= this.b.size()) {
            return (w) this.b.get(this.b.size() - 1);
        }
        if (i > this.e) {
            this.e = i;
        }
        return (w) this.b.get(i);
    }

    protected void e() {
        d(0);
        this.d = 0;
    }

    public final String g() {
        return this.a.b();
    }

    public String toString() {
        int i;
        int i2;
        if (this.d == -1) {
            e();
        }
        if (this.d == -1) {
            e();
        }
        if (((w) this.b.get(this.d)).a() != -1) {
            i = this.d + 1;
            d(i);
            while (true) {
                i2 = i;
                if (((w) this.b.get(i2)).a() == -1) {
                    break;
                }
                i = i2 + 1;
                d(i);
            }
        }
        i = this.b.size() - 1;
        if (i < 0) {
            return null;
        }
        if (this.d == -1) {
            e();
        }
        if (i >= this.b.size()) {
            i2 = this.b.size() - 1;
        } else {
            i2 = i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i3 = 0; i3 <= i2; i3++) {
            w wVar = (w) this.b.get(i3);
            if (wVar.a() == -1) {
                break;
            }
            stringBuilder.append(wVar.b());
        }
        return stringBuilder.toString();
    }

    public final void b(int marker) {
        this.d = marker;
    }
}
