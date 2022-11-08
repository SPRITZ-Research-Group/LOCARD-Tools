package com.google.android.gms.internal.measurement;

final class ds implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ zzjx b;
    private final /* synthetic */ zzdz c;
    private final /* synthetic */ di d;

    ds(di diVar, boolean z, zzjx zzjx, zzdz zzdz) {
        this.d = diVar;
        this.a = z;
        this.b = zzjx;
        this.c = zzdz;
    }

    public final void run() {
        an e = this.d.b;
        if (e == null) {
            this.d.q().v().a("Discarding data. Failed to set user attribute");
            return;
        }
        this.d.a(e, this.a ? null : this.b, this.c);
        this.d.A();
    }
}
