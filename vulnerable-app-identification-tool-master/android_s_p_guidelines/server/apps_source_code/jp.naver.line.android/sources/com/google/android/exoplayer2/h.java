package com.google.android.exoplayer2;

import java.io.IOException;

public final class h extends Exception {
    public final int a;
    public final int b;
    private final Throwable c;

    public static h a(IOException iOException) {
        return new h(0, iOException, -1);
    }

    public static h a(Exception exception, int i) {
        return new h(1, exception, i);
    }

    static h a(RuntimeException runtimeException) {
        return new h(2, runtimeException, -1);
    }

    private h(int i, Throwable th, int i2) {
        super(th);
        this.a = i;
        this.c = th;
        this.b = i2;
    }
}
