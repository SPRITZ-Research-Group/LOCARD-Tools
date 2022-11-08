package com.google.android.gms.internal.measurement;

final class ec implements Runnable {
    private final /* synthetic */ eo a;
    private final /* synthetic */ Runnable b;

    ec(eo eoVar, Runnable runnable) {
        this.a = eoVar;
        this.b = runnable;
    }

    public final void run() {
        this.a.K();
        this.a.a(this.b);
        this.a.J();
    }
}
