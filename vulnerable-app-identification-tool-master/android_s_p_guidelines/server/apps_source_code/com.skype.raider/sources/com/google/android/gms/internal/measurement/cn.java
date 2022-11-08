package com.google.android.gms.internal.measurement;

final class cn implements Runnable {
    private final /* synthetic */ zzjx a;
    private final /* synthetic */ zzdz b;
    private final /* synthetic */ bz c;

    cn(bz bzVar, zzjx zzjx, zzdz zzdz) {
        this.c = bzVar;
        this.a = zzjx;
        this.b = zzdz;
    }

    public final void run() {
        this.c.a.K();
        this.c.a.b(this.a, this.b);
    }
}
