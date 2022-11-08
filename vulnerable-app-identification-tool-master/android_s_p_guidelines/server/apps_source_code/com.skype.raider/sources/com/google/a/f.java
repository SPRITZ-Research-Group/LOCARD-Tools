package com.google.a;

public abstract class f extends Exception {
    protected static final boolean a;
    protected static final StackTraceElement[] b = new StackTraceElement[0];

    static {
        boolean z;
        if (System.getProperty("surefire.test.class.path") != null) {
            z = true;
        } else {
            z = false;
        }
        a = z;
    }

    f() {
    }

    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
