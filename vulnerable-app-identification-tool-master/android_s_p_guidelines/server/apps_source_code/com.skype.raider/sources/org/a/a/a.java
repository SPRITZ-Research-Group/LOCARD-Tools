package org.a.a;

import java.util.ArrayList;
import java.util.List;

public final class a implements e {
    protected char[] a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected List<f> g;
    protected int h;
    public String i;

    public a() {
        this.c = 0;
        this.d = 1;
        this.e = 0;
        this.f = 0;
    }

    public a(String input) {
        this();
        this.a = input.toCharArray();
        this.b = input.length();
    }

    public final void a() {
        if (this.c < this.b) {
            this.e++;
            if (this.a[this.c] == 10) {
                this.d++;
                this.e = 0;
            }
            this.c++;
        }
    }

    public final int a(int i) {
        if (i == 0) {
            return 0;
        }
        if (i < 0) {
            i++;
            if ((this.c + i) - 1 < 0) {
                return -1;
            }
        }
        if ((this.c + i) - 1 < this.b) {
            return this.a[(this.c + i) - 1];
        }
        return -1;
    }

    public final int b() {
        return this.c;
    }

    public final int c() {
        return this.b;
    }

    public final int d() {
        f state;
        if (this.g == null) {
            this.g = new ArrayList();
            this.g.add(null);
        }
        this.f++;
        if (this.f >= this.g.size()) {
            state = new f();
            this.g.add(state);
        } else {
            state = (f) this.g.get(this.f);
        }
        state.a = this.c;
        state.b = this.d;
        state.c = this.e;
        this.h = this.f;
        return this.f;
    }

    public final void b(int m) {
        f state = (f) this.g.get(m);
        int i = state.a;
        if (i <= this.c) {
            this.c = i;
        } else {
            while (this.c < i) {
                a();
            }
        }
        this.d = state.b;
        this.e = state.c;
        c(m);
    }

    public final void c(int marker) {
        this.f = marker;
        this.f--;
    }

    public final String a(int start, int stop) {
        return new String(this.a, start, (stop - start) + 1);
    }

    public final int e() {
        return this.d;
    }

    public final int f() {
        return this.e;
    }

    public final String g() {
        return this.i;
    }

    public final String toString() {
        return new String(this.a);
    }
}
