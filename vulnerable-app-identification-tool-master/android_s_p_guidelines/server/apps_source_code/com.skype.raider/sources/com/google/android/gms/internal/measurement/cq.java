package com.google.android.gms.internal.measurement;

final class cq implements Runnable {
    private final /* synthetic */ zzdz a;
    private final /* synthetic */ bz b;

    cq(bz bzVar, zzdz zzdz) {
        this.b = bzVar;
        this.a = zzdz;
    }

    public final void run() {
        this.b.a.K();
        this.b.a.c(this.a);
    }
}
