package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

final class dr implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ boolean e;
    private final /* synthetic */ zzdz f;
    private final /* synthetic */ di g;

    dr(di diVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z, zzdz zzdz) {
        this.g = diVar;
        this.a = atomicReference;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = zzdz;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        synchronized (this.a) {
            try {
                an e = this.g.b;
                if (e == null) {
                    this.g.q().v().a("Failed to get user properties", av.a(this.b), this.c, this.d);
                    this.a.set(Collections.emptyList());
                } else {
                    if (TextUtils.isEmpty(this.b)) {
                        this.a.set(e.a(this.c, this.d, this.e, this.f));
                    } else {
                        this.a.set(e.a(this.b, this.c, this.d, this.e));
                    }
                    this.g.A();
                    this.a.notify();
                }
            } catch (RemoteException e2) {
                this.g.q().v().a("Failed to get user properties", av.a(this.b), this.c, e2);
                this.a.set(Collections.emptyList());
            } finally {
                this.a.notify();
            }
        }
    }
}
