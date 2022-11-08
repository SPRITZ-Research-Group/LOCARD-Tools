package com.google.android.gms.internal.measurement;

final class cj implements Runnable {
    private final /* synthetic */ zzdz a;
    private final /* synthetic */ bz b;

    cj(bz bzVar, zzdz zzdz) {
        this.b = bzVar;
        this.a = zzdz;
    }

    public final void run() {
        this.b.a.K();
        this.b.a.b(this.a);
    }
}
