package org.a.a;

import org.a.a.b.p;

public final class o extends t {
    public int a;

    public o(int expecting, p input) {
        super(input);
        this.a = expecting;
    }

    public final String toString() {
        return "MismatchedTreeNodeException(" + a() + "!=" + this.a + ")";
    }
}
