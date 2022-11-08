package com.google.android.gms.internal.measurement;

final class cc implements Runnable {
    private final /* synthetic */ zzed a;
    private final /* synthetic */ zzdz b;
    private final /* synthetic */ bz c;

    cc(bz bzVar, zzed zzed, zzdz zzdz) {
        this.c = bzVar;
        this.a = zzed;
        this.b = zzdz;
    }

    public final void run() {
        this.c.a.K();
        this.c.a.a(this.a, this.b);
    }
}
