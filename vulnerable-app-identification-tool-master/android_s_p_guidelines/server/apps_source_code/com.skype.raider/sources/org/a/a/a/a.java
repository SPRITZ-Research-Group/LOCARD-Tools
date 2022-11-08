package org.a.a.a;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class a<T> {
    protected List<T> a = new ArrayList();
    protected int b = 0;
    protected int c = -1;

    public final void e() {
        this.b = 0;
        this.a.clear();
    }

    public T f() {
        T o = d(0);
        this.b++;
        if (this.b == this.a.size()) {
            e();
        }
        return o;
    }

    public final void a(T o) {
        this.a.add(o);
    }

    public int c() {
        return this.a.size() - this.b;
    }

    public final T d(int i) {
        int absIndex = this.b + i;
        if (absIndex >= this.a.size()) {
            throw new NoSuchElementException("queue index " + absIndex + " > last index " + (this.a.size() - 1));
        } else if (absIndex < 0) {
            throw new NoSuchElementException("queue index " + absIndex + " < 0");
        } else {
            if (absIndex > this.c) {
                this.c = absIndex;
            }
            return this.a.get(absIndex);
        }
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        int n = c();
        for (int i = 0; i < n; i++) {
            buf.append(d(i));
            if (i + 1 < n) {
                buf.append(" ");
            }
        }
        return buf.toString();
    }
}
