package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;

final class do implements Runnable {
    private final /* synthetic */ boolean a = true;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ zzeu c;
    private final /* synthetic */ zzdz d;
    private final /* synthetic */ String e;
    private final /* synthetic */ di f;

    do(di diVar, boolean z, zzeu zzeu, zzdz zzdz, String str) {
        this.f = diVar;
        this.b = z;
        this.c = zzeu;
        this.d = zzdz;
        this.e = str;
    }

    public final void run() {
        an e = this.f.b;
        if (e == null) {
            this.f.q().v().a("Discarding data. Failed to send event to service");
            return;
        }
        if (this.a) {
            this.f.a(e, this.b ? null : this.c, this.d);
        } else {
            try {
                if (TextUtils.isEmpty(this.e)) {
                    e.a(this.c, this.d);
                } else {
                    e.a(this.c, this.e, this.f.q().E());
                }
            } catch (RemoteException e2) {
                this.f.q().v().a("Failed to send event to the service", e2);
            }
        }
        this.f.A();
    }
}
