package com.google.android.gms.internal.measurement;

final class cd implements Runnable {
    private final /* synthetic */ zzed a;
    private final /* synthetic */ bz b;

    cd(bz bzVar, zzed zzed) {
        this.b = bzVar;
        this.a = zzed;
    }

    public final void run() {
        this.b.a.K();
        eo a = this.b.a;
        zzed zzed = this.a;
        zzdz a2 = a.a(zzed.a);
        if (a2 != null) {
            a.b(zzed, a2);
        }
    }
}
