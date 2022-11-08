package com.google.android.gms.internal.measurement;

final class o implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ long b;
    private final /* synthetic */ n c;

    o(n nVar, String str, long j) {
        this.c = nVar;
        this.a = str;
        this.b = j;
    }

    public final void run() {
        n.a(this.c, this.a, this.b);
    }
}
