package com.google.android.exoplayer2.decoder;

public abstract class a {
    private int a;

    public void a() {
        this.a = 0;
    }

    public final boolean k_() {
        return c(Integer.MIN_VALUE);
    }

    public final boolean c() {
        return c(4);
    }

    public final boolean d() {
        return c(1);
    }

    public final void a_(int flags) {
        this.a = flags;
    }

    public final void b(int flag) {
        this.a |= flag;
    }

    public final void e() {
        this.a &= Integer.MAX_VALUE;
    }

    protected final boolean c(int flag) {
        return (this.a & flag) == flag;
    }
}
