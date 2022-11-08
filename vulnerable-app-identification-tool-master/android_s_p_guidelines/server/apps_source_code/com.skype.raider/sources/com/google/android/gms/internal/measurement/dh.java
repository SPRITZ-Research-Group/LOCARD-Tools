package com.google.android.gms.internal.measurement;

final class dh implements Runnable {
    private final /* synthetic */ de a;
    private final /* synthetic */ df b;

    dh(df dfVar, de deVar) {
        this.b = dfVar;
        this.a = deVar;
    }

    public final void run() {
        df.a(this.b, this.a);
        this.b.a = null;
        this.b.h().a(null);
    }
}
