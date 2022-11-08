package org.b.a.b;

public class b {
    public Throwable c = new Throwable();

    public final StackTraceElement a() {
        StackTraceElement[] trace = this.c.getStackTrace();
        for (StackTraceElement e : trace) {
            if (!e.toString().startsWith("org.stringtemplate.v4")) {
                return e;
            }
        }
        return trace[0];
    }
}
