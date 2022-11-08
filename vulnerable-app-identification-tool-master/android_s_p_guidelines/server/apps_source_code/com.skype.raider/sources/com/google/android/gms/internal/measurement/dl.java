package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class dl implements Runnable {
    private final /* synthetic */ zzdz a;
    private final /* synthetic */ di b;

    dl(di diVar, zzdz zzdz) {
        this.b = diVar;
        this.a = zzdz;
    }

    public final void run() {
        an e = this.b.b;
        if (e == null) {
            this.b.q().v().a("Discarding data. Failed to send app launch");
            return;
        }
        try {
            e.a(this.a);
            this.b.a(e, null, this.a);
            this.b.A();
        } catch (RemoteException e2) {
            this.b.q().v().a("Failed to send app launch to the service", e2);
        }
    }
}
