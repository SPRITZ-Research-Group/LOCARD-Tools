package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;

class bd extends BroadcastReceiver {
    @VisibleForTesting
    private static final String a = bd.class.getName();
    private final eo b;
    private boolean c;
    private boolean d;

    bd(eo eoVar) {
        ab.a((Object) eoVar);
        this.b = eoVar;
    }

    @WorkerThread
    public final void a() {
        this.b.I();
        this.b.x();
        if (!this.c) {
            this.b.k().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.d = this.b.F().u();
            this.b.q().C().a("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.d));
            this.c = true;
        }
    }

    @WorkerThread
    public final void b() {
        this.b.I();
        this.b.x();
        this.b.x();
        if (this.c) {
            this.b.q().C().a("Unregistering connectivity change receiver");
            this.c = false;
            this.d = false;
            try {
                this.b.k().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.b.q().v().a("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        this.b.I();
        String action = intent.getAction();
        this.b.q().C().a("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean u = this.b.F().u();
            if (this.d != u) {
                this.d = u;
                this.b.p().a(new be(this, u));
                return;
            }
            return;
        }
        this.b.q().y().a("NetworkBroadcastReceiver received unknown action", action);
    }
}
