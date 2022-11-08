package org.b.a.c;

import org.b.a.c;
import org.b.a.d;

public final class s extends o {
    final d a;
    public final int b;
    public final c c;

    public s(d interp, g error, int ip, c scope) {
        this(interp, error, ip, scope, null);
    }

    public s(d interp, g error, int ip, c scope, Object arg) {
        this(interp, error, ip, scope, null, arg, null);
    }

    public s(d interp, g error, int ip, c scope, Throwable e, Object arg) {
        this(interp, error, ip, scope, e, arg, null);
    }

    public s(d interp, g error, int ip, c scope, Throwable e, Object arg, Object arg2) {
        this(interp, error, ip, scope, e, arg, arg2, null);
    }

    public s(d interp, g error, int ip, c scope, Throwable e, Object arg, Object arg2, Object arg3) {
        super(error, scope != null ? scope.b : null, e, arg, arg2, arg3);
        this.a = interp;
        this.b = ip;
        this.c = scope;
    }

    public final String toString() {
        String loc = null;
        StringBuilder buf = new StringBuilder();
        if (this.b >= 0 && this.d.b != null) {
            h hVar = this.d.b.r[this.b];
            if (hVar != null) {
                loc = j.a(this.d.b.c, hVar.a).toString();
            }
        }
        if (this.d != null) {
            buf.append("context [");
            if (this.a != null) {
                buf.append(d.a(this.c));
            }
            buf.append("]");
        }
        if (loc != null) {
            buf.append(" " + loc);
        }
        buf.append(" " + super.toString());
        return buf.toString();
    }
}
