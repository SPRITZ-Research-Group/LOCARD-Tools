package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class dm implements Runnable {
    private final /* synthetic */ de a;
    private final /* synthetic */ di b;

    dm(di diVar, de deVar) {
        this.b = diVar;
        this.a = deVar;
    }

    public final void run() {
        an e = this.b.b;
        if (e == null) {
            this.b.q().v().a("Failed to send current screen to service");
            return;
        }
        try {
            if (this.a == null) {
                e.a(0, null, null, this.b.k().getPackageName());
            } else {
                e.a(this.a.c, this.a.a, this.a.b, this.b.k().getPackageName());
            }
            this.b.A();
        } catch (RemoteException e2) {
            this.b.q().v().a("Failed to send current screen to the service", e2);
        }
    }
}
