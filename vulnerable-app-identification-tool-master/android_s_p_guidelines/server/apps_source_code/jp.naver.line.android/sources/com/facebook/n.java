package com.facebook;

public class n extends RuntimeException {
    static final long serialVersionUID = 1;

    public n(String str) {
        super(str);
    }

    public n(String str, Object... objArr) {
        this(String.format(str, objArr));
    }

    public n(String str, Throwable th) {
        super(str, th);
    }

    public n(Throwable th) {
        super(th);
    }

    public String toString() {
        return getMessage();
    }
}
