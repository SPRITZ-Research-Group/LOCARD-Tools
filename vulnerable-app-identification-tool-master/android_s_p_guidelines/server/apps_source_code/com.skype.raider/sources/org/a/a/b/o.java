package org.a.a.b;

import java.util.Iterator;
import org.a.a.a.a;

public final class o implements Iterator<Object> {
    protected n a;
    protected Object b;
    protected Object c;
    protected boolean d = true;
    public Object e;
    public Object f;
    public Object g;
    protected a<Object> h;

    public o(n adaptor, Object tree) {
        this.a = adaptor;
        this.c = tree;
        this.b = tree;
        this.h = new a();
        this.f = adaptor.a(2, "DOWN");
        this.e = adaptor.a(3, "UP");
        this.g = adaptor.a(-1, "EOF");
    }

    public final boolean hasNext() {
        if (this.d) {
            if (this.b != null) {
                return true;
            }
            return false;
        } else if (this.h != null && this.h.c() > 0) {
            return true;
        } else {
            if (this.c == null) {
                return false;
            }
            if (this.a.f(this.c) > 0 || this.a.i(this.c) != null) {
                return true;
            }
            return false;
        }
    }

    public final Object next() {
        if (this.d) {
            this.d = false;
            if (this.a.f(this.c) != 0) {
                return this.c;
            }
            this.h.a(this.g);
            return this.c;
        } else if (this.h != null && this.h.c() > 0) {
            return this.h.f();
        } else {
            if (this.c == null) {
                return this.g;
            }
            if (this.a.f(this.c) > 0) {
                this.c = this.a.a(this.c, 0);
                this.h.a(this.c);
                return this.f;
            }
            Object parent;
            while (true) {
                parent = this.a.i(this.c);
                if (parent != null && this.a.j(this.c) + 1 >= this.a.f(parent)) {
                    this.h.a(this.e);
                    this.c = parent;
                } else if (parent != null) {
                    this.c = null;
                    this.h.a(this.g);
                    return this.h.f();
                } else {
                    this.c = this.a.a(parent, this.a.j(this.c) + 1);
                    this.h.a(this.c);
                    return this.h.f();
                }
            }
            if (parent != null) {
                this.c = this.a.a(parent, this.a.j(this.c) + 1);
                this.h.a(this.c);
                return this.h.f();
            }
            this.c = null;
            this.h.a(this.g);
            return this.h.f();
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
