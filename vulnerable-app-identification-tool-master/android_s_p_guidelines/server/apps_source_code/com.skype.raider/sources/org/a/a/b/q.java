package org.a.a.b;

import java.util.regex.Pattern;
import org.a.a.b;
import org.a.a.c;
import org.a.a.g;
import org.a.a.k;
import org.a.a.o;
import org.a.a.t;
import org.a.a.u;

public class q extends b {
    static String b = ".*[^.]\\.\\.[^.].*";
    static String c = ".*\\.\\.\\.\\s+\\.\\.\\..*";
    static Pattern d = Pattern.compile(b);
    static Pattern e = Pattern.compile(c);
    protected p f;

    public q(p input, u state) {
        super(state);
        this.f = input;
    }

    public final String d() {
        return this.f.g();
    }

    protected final Object b(k input) {
        return ((p) input).e(1);
    }

    protected final Object a(k input, int expectedTokenType) {
        return ((p) null.d).j().b(new g(expectedTokenType, "<missing " + b()[expectedTokenType] + ">"));
    }

    protected final Object b(k input, int ttype, c follow) throws t {
        throw new o(ttype, (p) input);
    }

    public final String b(t e) {
        return c() + ": node from " + (e.k ? "after " : "") + "line " + e.i + ":" + e.j;
    }

    public final String a(t e, String[] tokenNames) {
        if (this instanceof q) {
            n adaptor = ((p) e.d).j();
            e.f = adaptor.h(e.g);
            if (e.f == null) {
                e.f = new g(adaptor.d(e.g), adaptor.e(e.g));
            }
        }
        return super.a(e, tokenNames);
    }
}
