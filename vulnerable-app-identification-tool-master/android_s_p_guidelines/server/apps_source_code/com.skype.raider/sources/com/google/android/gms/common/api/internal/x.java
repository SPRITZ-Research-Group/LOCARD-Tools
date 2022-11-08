package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.d.a;
import java.util.Collections;

final class x implements Runnable {
    private final /* synthetic */ ConnectionResult a;
    private final /* synthetic */ c b;

    x(c cVar, ConnectionResult connectionResult) {
        this.b = cVar;
        this.a = connectionResult;
    }

    public final void run() {
        if (this.a.b()) {
            this.b.f = true;
            if (this.b.b.d()) {
                this.b.a();
                return;
            } else {
                this.b.b.a(null, Collections.emptySet());
                return;
            }
        }
        ((a) this.b.a.m.get(this.b.c)).a(this.a);
    }
}
