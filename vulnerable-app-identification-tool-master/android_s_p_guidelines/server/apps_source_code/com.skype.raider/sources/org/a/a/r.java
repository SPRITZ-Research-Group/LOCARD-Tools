package org.a.a;

public class r extends b {
    public y b;

    public r(y input, u state) {
        super(state);
        this.b = input;
    }

    protected final Object b(k input) {
        return ((y) input).g(1);
    }

    protected final Object a(k input, int expectedTokenType) {
        String tokenText;
        if (expectedTokenType == -1) {
            tokenText = "<missing EOF>";
        } else {
            tokenText = "<missing " + b()[expectedTokenType] + ">";
        }
        g t = new g(expectedTokenType, tokenText);
        w current = ((y) input).g(1);
        if (current.a() == -1) {
            current = ((y) input).g(-1);
        }
        t.b = current.c();
        t.c = current.d();
        t.d = 0;
        t.e = current.i();
        return t;
    }

    public final String d() {
        return this.b.g();
    }
}
