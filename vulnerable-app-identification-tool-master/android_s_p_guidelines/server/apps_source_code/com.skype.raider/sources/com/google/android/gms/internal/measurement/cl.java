package com.google.android.gms.internal.measurement;

final class cl implements Runnable {
    private final /* synthetic */ zzeu a;
    private final /* synthetic */ String b;
    private final /* synthetic */ bz c;

    cl(bz bzVar, zzeu zzeu, String str) {
        this.c = bzVar;
        this.a = zzeu;
        this.b = str;
    }

    public final void run() {
        this.c.a.K();
        this.c.a.a(this.a, this.b);
    }
}
