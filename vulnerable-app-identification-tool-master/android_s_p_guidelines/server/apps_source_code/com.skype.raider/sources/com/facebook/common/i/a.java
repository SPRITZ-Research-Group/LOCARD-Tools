package com.facebook.common.i;

public final class a extends Exception {
    public final synchronized Throwable fillInStackTrace() {
        return this;
    }

    public a(String detailMessage) {
        super(detailMessage);
    }
}
