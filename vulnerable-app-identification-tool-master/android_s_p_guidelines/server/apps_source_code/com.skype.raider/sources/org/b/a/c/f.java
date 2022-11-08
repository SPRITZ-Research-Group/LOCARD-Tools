package org.b.a.c;

import org.a.a.e;
import org.a.a.t;
import org.a.a.w;
import org.b.a.c;
import org.b.a.d;
import org.b.a.g;

public final class f {
    public static g a = new g() {
        public final void a(o msg) {
            System.err.println(msg);
        }

        public final void b(o msg) {
            if (msg.e != g.NO_SUCH_PROPERTY) {
                System.err.println(msg);
            }
        }

        public final void c(o msg) {
            System.err.println(msg);
        }

        public final void d(o msg) {
            System.err.println(msg);
        }
    };
    public final g b;

    public f() {
        this(a);
    }

    private f(g listener) {
        this.b = listener;
    }

    public final void a(g error, w templateToken, w t) {
        e input = t.i();
        String srcName = null;
        if (input != null) {
            srcName = input.g();
            if (srcName != null) {
                srcName = j.b(srcName);
            }
        }
        this.b.a(new m(error, srcName, templateToken, t, t.b()));
    }

    public final void a(String srcName, String msg, w templateToken, t e) {
        if (srcName != null) {
            srcName = j.b(srcName);
        }
        this.b.a(new n(srcName, msg, templateToken, e));
    }

    public final void a(g error, w templateToken, w t, Object arg) {
        String srcName = t.i().g();
        if (srcName != null) {
            srcName = j.b(srcName);
        }
        this.b.a(new m(error, srcName, templateToken, t, arg));
    }

    public final void a(d interp, c scope, g error, Object arg) {
        this.b.b(new s(interp, error, scope != null ? scope.c : 0, scope, arg));
    }

    public final void a(d interp, c scope, g error, Object arg, Object arg2) {
        this.b.b(new s(interp, error, scope != null ? scope.c : 0, scope, null, arg, arg2));
    }

    public final void a(d interp, c scope, g error, Object arg, Object arg2, Object arg3) {
        this.b.b(new s(interp, error, scope != null ? scope.c : 0, scope, null, arg, arg2, arg3));
    }

    public final void a(org.b.a.f self, g error, Throwable e) {
        this.b.c(new o(error, self, e));
    }

    public final void a(org.b.a.f self, String msg, Throwable e) {
        this.b.d(new o(g.INTERNAL_ERROR, self, e, msg));
    }
}
