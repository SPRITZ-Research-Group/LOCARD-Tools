package com.google.android.gms.internal.measurement;

final class co implements Runnable {
    private final /* synthetic */ zzjx a;
    private final /* synthetic */ zzdz b;
    private final /* synthetic */ bz c;

    co(bz bzVar, zzjx zzjx, zzdz zzdz) {
        this.c = bzVar;
        this.a = zzjx;
        this.b = zzdz;
    }

    public final void run() {
        this.c.a.K();
        this.c.a.a(this.a, this.b);
    }
}
