package com.google.android.gms.internal.measurement;

final class ck implements Runnable {
    private final /* synthetic */ zzeu a;
    private final /* synthetic */ zzdz b;
    private final /* synthetic */ bz c;

    ck(bz bzVar, zzeu zzeu, zzdz zzdz) {
        this.c = bzVar;
        this.a = zzeu;
        this.b = zzdz;
    }

    public final void run() {
        this.c.a.K();
        this.c.a.a(this.a, this.b);
    }
}
