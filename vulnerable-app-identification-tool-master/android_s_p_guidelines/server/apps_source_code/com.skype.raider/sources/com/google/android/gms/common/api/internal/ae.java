package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class ae implements Runnable {
    private final /* synthetic */ ad a;

    ae(ad adVar) {
        this.a = adVar;
    }

    public final void run() {
        this.a.h.b(new ConnectionResult(4));
    }
}
