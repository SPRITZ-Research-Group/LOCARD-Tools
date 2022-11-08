package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;

final class dp implements Runnable {
    private final /* synthetic */ boolean a = true;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ zzed c;
    private final /* synthetic */ zzdz d;
    private final /* synthetic */ zzed e;
    private final /* synthetic */ di f;

    dp(di diVar, boolean z, zzed zzed, zzdz zzdz, zzed zzed2) {
        this.f = diVar;
        this.b = z;
        this.c = zzed;
        this.d = zzdz;
        this.e = zzed2;
    }

    public final void run() {
        an e = this.f.b;
        if (e == null) {
            this.f.q().v().a("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.a) {
            this.f.a(e, this.b ? null : this.c, this.d);
        } else {
            try {
                if (TextUtils.isEmpty(this.e.a)) {
                    e.a(this.c, this.d);
                } else {
                    e.a(this.c);
                }
            } catch (RemoteException e2) {
                this.f.q().v().a("Failed to send conditional user property to the service", e2);
            }
        }
        this.f.A();
    }
}
