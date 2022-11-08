package org.b.a.b;

import org.b.a.c;

public class f {
    public c d;
    public final int e;
    public final int f;

    public f(c scope, int outputStartChar, int outputStopChar) {
        this.d = scope;
        this.e = outputStartChar;
        this.f = outputStopChar;
    }

    public String toString() {
        return getClass().getSimpleName() + "{self=" + this.d.b + ", start=" + this.e + ", stop=" + this.f + '}';
    }
}
