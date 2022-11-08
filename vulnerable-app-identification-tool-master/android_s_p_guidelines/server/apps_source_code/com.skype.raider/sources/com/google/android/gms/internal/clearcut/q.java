package com.google.android.gms.internal.clearcut;

public abstract class q {
    private static volatile boolean d;
    private int a;
    private int b;
    private boolean c;

    static {
        d = false;
        d = true;
    }

    private q() {
        this.a = 100;
        this.b = Integer.MAX_VALUE;
        this.c = false;
    }

    /* synthetic */ q(byte b) {
        this();
    }

    static q a(byte[] bArr, int i) {
        q rVar = new r(bArr, i, (byte) 0);
        try {
            rVar.a(i);
            return rVar;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int a();

    public abstract int a(int i) throws an;
}
