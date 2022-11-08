package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class dk implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ zzdz b;
    private final /* synthetic */ di c;

    dk(di diVar, AtomicReference atomicReference, zzdz zzdz) {
        this.c = diVar;
        this.a = atomicReference;
        this.b = zzdz;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        synchronized (this.a) {
            try {
                an e = this.c.b;
                if (e == null) {
                    this.c.q().v().a("Failed to get app instance id");
                } else {
                    this.a.set(e.c(this.b));
                    String str = (String) this.a.get();
                    if (str != null) {
                        this.c.e().a(str);
                        this.c.r().j.a(str);
                    }
                    this.c.A();
                    this.a.notify();
                }
            } catch (RemoteException e2) {
                this.c.q().v().a("Failed to get app instance id", e2);
            } finally {
                this.a.notify();
            }
        }
    }
}
