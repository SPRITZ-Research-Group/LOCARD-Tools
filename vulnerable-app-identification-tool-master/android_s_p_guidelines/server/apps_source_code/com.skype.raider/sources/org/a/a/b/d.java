package org.a.a.b;

import org.a.a.g;
import org.a.a.w;

public final class d extends b {
    public final Object g(Object t) {
        if (t == null) {
            return null;
        }
        return ((m) t).g();
    }

    public final Object b(w payload) {
        return new c(payload);
    }

    public final w b(int tokenType, String text) {
        return new g(tokenType, text);
    }

    public final w a(w fromToken) {
        return new g(fromToken);
    }

    public final void a(Object t, w startToken, w stopToken) {
        if (t != null) {
            int start = 0;
            int stop = 0;
            if (startToken != null) {
                start = startToken.h();
            }
            if (stopToken != null) {
                stop = stopToken.h();
            }
            ((m) t).c(start);
            ((m) t).d(stop);
        }
    }

    public final String e(Object t) {
        if (t == null) {
            return null;
        }
        return ((m) t).i();
    }

    public final int d(Object t) {
        if (t == null) {
            return 0;
        }
        return ((m) t).h();
    }

    public final w h(Object t) {
        if (t instanceof c) {
            return ((c) t).b;
        }
        return null;
    }

    public final Object a(Object t, int i) {
        if (t == null) {
            return null;
        }
        return ((m) t).a(i);
    }

    public final int f(Object t) {
        if (t == null) {
            return 0;
        }
        return ((m) t).a();
    }

    public final Object i(Object t) {
        if (t == null) {
            return null;
        }
        return ((m) t).d();
    }

    public final void c(Object t, Object parent) {
        if (t != null) {
            ((m) t).b((m) parent);
        }
    }

    public final int j(Object t) {
        if (t == null) {
            return 0;
        }
        return ((m) t).c();
    }

    public final void b(Object t, int index) {
        if (t != null) {
            ((m) t).b(index);
        }
    }
}
