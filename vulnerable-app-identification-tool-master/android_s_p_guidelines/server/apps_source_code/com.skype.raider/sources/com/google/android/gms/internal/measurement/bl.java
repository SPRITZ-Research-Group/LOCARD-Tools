package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.MainThread;

final class bl implements ServiceConnection {
    final /* synthetic */ bk a;

    private bl(bk bkVar) {
        this.a = bkVar;
    }

    /* synthetic */ bl(bk bkVar, byte b) {
        this(bkVar);
    }

    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.a.b.q().y().a("Install Referrer connection returned with null binder");
            return;
        }
        try {
            this.a.a = fu.a(iBinder);
            if (this.a.a == null) {
                this.a.b.q().y().a("Install Referrer Service implementation was not found");
                return;
            }
            this.a.b.q().A().a("Install Referrer Service connected");
            this.a.b.p().a(new bm(this));
        } catch (Exception e) {
            this.a.b.q().y().a("Exception occurred while calling Install Referrer API", e);
        }
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.a = null;
        this.a.b.q().A().a("Install Referrer Service disconnected");
    }
}
