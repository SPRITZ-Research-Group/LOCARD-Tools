package com.microsoft.b.a;

public abstract class c extends RuntimeException {
    i a;

    protected abstract i a(Throwable th);

    public c(Throwable cause) {
        super(cause);
        this.a = a(cause);
    }

    public static i b(Throwable th) {
        i errorCode = i.SQLITE_ERROR;
        if (th instanceof c) {
            return ((c) th).a;
        }
        return errorCode;
    }
}
