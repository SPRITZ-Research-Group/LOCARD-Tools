package org.b.a.a;

import java.util.HashMap;
import java.util.Map;
import org.a.a.a;
import org.a.a.b.e;
import org.a.a.q;
import org.a.a.t;
import org.a.a.w;
import org.a.a.y;
import org.b.a.a.j.aa;
import org.b.a.c.g;
import org.b.a.d.c;
import org.b.a.h;

public final class f {
    public static final Map<String, c> a;
    public static final int b;
    public static final Map<String, String> c = new HashMap<String, String>() {
        {
            put("anchor", "true");
            put("wrap", "\n");
        }
    };
    public static Map<String, Short> d = new HashMap<String, Short>() {
        {
            put("first", Short.valueOf((short) 27));
            put("last", Short.valueOf((short) 28));
            put("rest", Short.valueOf((short) 29));
            put("trunc", Short.valueOf((short) 30));
            put("strip", Short.valueOf((short) 31));
            put("trim", Short.valueOf((short) 32));
            put("length", Short.valueOf((short) 33));
            put("strlen", Short.valueOf((short) 34));
            put("reverse", Short.valueOf((short) 35));
        }
    };
    public static int e = 0;
    public h f;

    static {
        Map anonymousClass1 = new HashMap<String, c>() {
            {
                put("anchor", c.ANCHOR);
                put("format", c.FORMAT);
                put("null", c.NULL);
                put("separator", c.SEPARATOR);
                put("wrap", c.WRAP);
            }
        };
        a = anonymousClass1;
        b = anonymousClass1.size();
    }

    public f() {
        this(h.q);
    }

    public f(h group) {
        this.f = group;
    }

    public final e a(String template) {
        a is = new a(template);
        is.i = null;
        y tokens = new org.a.a.h(new i(this.f.r, is, this.f.f, this.f.g));
        j p = new j(tokens, this.f.r);
        try {
            aa r = p.e();
            e impl;
            if (p.a() > 0 || r.c == null) {
                impl = new e();
                impl.a(null);
                return impl;
            }
            e nodes = new e(r.c);
            nodes.a(tokens);
            impl = null;
            try {
                impl = new c(nodes, this.f.r, template).a(null, null);
                impl.k = this.f;
                impl.c = template;
                impl.f = r.c;
                impl.f.l();
                impl.e = tokens;
                return impl;
            } catch (Throwable re) {
                this.f.r.a(null, "bad tree structure", re);
                return impl;
            }
        } catch (t re2) {
            if (re2.f.a() == -1) {
                this.f.r.a(g.SYNTAX_ERROR, null, re2.f, (Object) "premature EOF");
            } else if (re2 instanceof q) {
                this.f.r.a(g.SYNTAX_ERROR, null, re2.f, "'" + re2.f.b() + "' came as a complete surprise to me");
            } else if (tokens.b() == 0) {
                this.f.r.a(g.SYNTAX_ERROR, null, re2.f, "this doesn't look like a template: \"" + tokens + "\"");
            } else if (tokens.a(1) == 23) {
                this.f.r.a(g.SYNTAX_ERROR, null, re2.f, (Object) "doesn't look like an expression");
            } else {
                this.f.r.a(g.SYNTAX_ERROR, null, re2.f, p.a(re2, p.b()));
            }
            throw new h();
        }
    }

    public static e a(e outermostImpl, w nameToken) {
        String mangled = h.a(outermostImpl.a, nameToken.b());
        e blank = new e();
        blank.l = true;
        blank.d = nameToken;
        blank.m = org.b.a.f.c.IMPLICIT;
        blank.a = mangled;
        outermostImpl.a(blank);
        return blank;
    }

    public static String a() {
        e++;
        return "_sub" + e;
    }
}
