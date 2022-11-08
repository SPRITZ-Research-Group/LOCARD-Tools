package com.facebook.drawee.a;

public final class c {
    private boolean a;
    private int b;
    private int c;

    public c() {
        a();
    }

    public final void a() {
        this.a = false;
        this.b = 4;
        this.c = 0;
    }

    public final void b() {
        this.c = 0;
    }

    public final void a(boolean tapToRetryEnabled) {
        this.a = tapToRetryEnabled;
    }

    public final boolean c() {
        return this.a && this.c < this.b;
    }

    public final void d() {
        this.c++;
    }
}
