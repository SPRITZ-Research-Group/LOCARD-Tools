package org.b.a.c;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.b.a.f;

public class o {
    public f d;
    public g e;
    public Object f;
    public Object g;
    public Object h;
    public Throwable i;

    private o(g error) {
        this.e = error;
    }

    private o(g error, f self) {
        this(error);
        this.d = self;
    }

    public o(g error, f self, Throwable cause) {
        this(error, self);
        this.i = cause;
    }

    public o(g error, f self, Throwable cause, Object arg) {
        this(error, self, cause);
        this.f = arg;
    }

    public o(g error, f self, Throwable cause, Object arg, Object arg2) {
        this(error, self, cause, arg);
        this.g = arg2;
    }

    public o(g error, f self, Throwable cause, Object arg, Object arg2, Object arg3) {
        this(error, self, cause, arg, arg2);
        this.h = arg3;
    }

    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.print(String.format(this.e.E, new Object[]{this.f, this.g, this.h}));
        if (this.i != null) {
            pw.print("\nCaused by: ");
            this.i.printStackTrace(pw);
        }
        return sw.toString();
    }
}
