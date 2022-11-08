package org.a.a.b;

import org.a.a.a.b;
import org.a.a.w;
import org.a.a.y;

public final class e extends b<Object> implements f<Object>, p {
    protected Object j;
    protected y k;
    n l;
    protected o m;
    protected boolean n;
    protected int o;
    protected Object p;

    public e(Object tree) {
        this(new d(), tree);
    }

    private e(n adaptor, Object tree) {
        this.n = false;
        this.o = 0;
        this.j = tree;
        this.l = adaptor;
        this.m = new o(adaptor, this.j);
    }

    public final Object h() {
        Object t = this.m.next();
        if (t == this.m.e) {
            this.o--;
            if (this.o == 0 && this.n) {
                return this.m.next();
            }
        } else if (t == this.m.f) {
            this.o++;
        }
        if (this.o == 0 && this.l.a(t)) {
            this.n = true;
            this.m.next();
            this.o++;
            t = this.m.next();
        }
        return t;
    }

    public final Object f() {
        Object result = super.f();
        if (this.b == 0 && c(this.e)) {
            this.p = this.e;
        }
        return result;
    }

    public final boolean b(Object o) {
        return this.l.d(o) == -1;
    }

    public final y i() {
        return this.k;
    }

    public final void a(y tokens) {
        this.k = tokens;
    }

    public final n j() {
        return this.l;
    }

    public final int a(int i) {
        return this.l.d(e(i));
    }

    public final Object a(boolean allowApproximateLocation) {
        Object node = this.a.get(this.b);
        if (c(node)) {
            return node;
        }
        if (!allowApproximateLocation) {
            return null;
        }
        for (int index = this.b - 1; index >= 0; index--) {
            node = this.a.get(index);
            if (c(node)) {
                return node;
            }
        }
        return this.p;
    }

    private boolean c(Object node) {
        w token = this.l.h(node);
        if (token != null && token.c() > 0) {
            return true;
        }
        return false;
    }

    public final String g() {
        return this.k.g();
    }
}
