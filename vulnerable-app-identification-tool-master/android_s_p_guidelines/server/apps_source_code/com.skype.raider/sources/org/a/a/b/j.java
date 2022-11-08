package org.a.a.b;

import java.util.ArrayList;
import java.util.List;

public abstract class j {
    protected int a;
    protected Object b;
    protected List<Object> c;
    protected boolean d;
    protected String e;
    protected n f;

    protected abstract Object b(Object obj);

    public j(n adaptor, String elementDescription) {
        this.a = 0;
        this.d = false;
        this.e = elementDescription;
        this.f = adaptor;
    }

    public j(n adaptor, String elementDescription, Object oneElement) {
        this(adaptor, elementDescription);
        a(oneElement);
    }

    public j(n adaptor, String elementDescription, List<Object> elements) {
        this(adaptor, elementDescription);
        this.b = null;
        this.c = elements;
    }

    public final void a() {
        this.a = 0;
        this.d = true;
    }

    public final void a(Object el) {
        if (el != null) {
            if (this.c != null) {
                this.c.add(el);
            } else if (this.b == null) {
                this.b = el;
            } else {
                this.c = new ArrayList(5);
                this.c.add(this.b);
                this.b = null;
                this.c.add(el);
            }
        }
    }

    public final Object b() {
        int n = e();
        if (this.d || (this.a >= n && n == 1)) {
            return b(c());
        }
        return c();
    }

    protected final Object c() {
        int n = e();
        if (n == 0) {
            throw new i(this.e);
        } else if (this.a >= n) {
            if (n == 1) {
                return c(this.b);
            }
            throw new g(this.e);
        } else if (this.b != null) {
            this.a++;
            return c(this.b);
        } else {
            Object o = c(this.c.get(this.a));
            this.a++;
            return o;
        }
    }

    protected Object c(Object el) {
        return el;
    }

    public final boolean d() {
        return (this.b != null && this.a <= 0) || (this.c != null && this.a < this.c.size());
    }

    private int e() {
        int n = 0;
        if (this.b != null) {
            n = 1;
        }
        if (this.c != null) {
            return this.c.size();
        }
        return n;
    }
}
