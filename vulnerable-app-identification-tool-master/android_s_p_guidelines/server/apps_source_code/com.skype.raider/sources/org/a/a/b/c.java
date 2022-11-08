package org.a.a.b;

import org.a.a.w;

public final class c extends a {
    public w b;
    protected int c = -1;
    protected int d = -1;
    public c e;
    public int f = -1;

    private c(c node) {
        super((byte) 0);
        this.b = node.b;
        this.c = node.c;
        this.d = node.d;
    }

    public c(w t) {
        this.b = t;
    }

    public final m g() {
        return new c(this);
    }

    public final boolean b() {
        return this.b == null;
    }

    public final int h() {
        if (this.b == null) {
            return 0;
        }
        return this.b.a();
    }

    public final String i() {
        if (this.b == null) {
            return null;
        }
        return this.b.b();
    }

    public final int e() {
        if (this.b != null && this.b.c() != 0) {
            return this.b.c();
        }
        if (a() > 0) {
            return a(0).e();
        }
        return 0;
    }

    public final int f() {
        if (this.b != null && this.b.d() != -1) {
            return this.b.d();
        }
        if (a() > 0) {
            return a(0).f();
        }
        return 0;
    }

    public final int j() {
        if (this.c != -1 || this.b == null) {
            return this.c;
        }
        return this.b.h();
    }

    public final void c(int index) {
        this.c = index;
    }

    public final int k() {
        if (this.d != -1 || this.b == null) {
            return this.d;
        }
        return this.b.h();
    }

    public final void d(int index) {
        this.d = index;
    }

    public final void l() {
        if (this.a != null) {
            for (int i = 0; i < this.a.size(); i++) {
                ((c) this.a.get(i)).l();
            }
            if ((this.c < 0 || this.d < 0) && this.a.size() > 0) {
                c lastChild = (c) this.a.get(this.a.size() - 1);
                this.c = ((c) this.a.get(0)).j();
                this.d = lastChild.k();
            }
        } else if (this.c < 0 || this.d < 0) {
            int h = this.b.h();
            this.d = h;
            this.c = h;
        }
    }

    public final int c() {
        return this.f;
    }

    public final m d() {
        return this.e;
    }

    public final void b(m t) {
        this.e = (c) t;
    }

    public final void b(int index) {
        this.f = index;
    }

    public final String toString() {
        if (b()) {
            return "nil";
        }
        if (h() == 0) {
            return "<errornode>";
        }
        if (this.b == null) {
            return null;
        }
        return this.b.b();
    }
}
