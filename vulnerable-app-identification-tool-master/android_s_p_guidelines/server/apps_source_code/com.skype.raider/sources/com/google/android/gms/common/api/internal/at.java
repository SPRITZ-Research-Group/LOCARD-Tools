package com.google.android.gms.common.api.internal;

import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class at implements Runnable {
    final /* synthetic */ ar a;
    private final as b;

    at(ar arVar, as asVar) {
        this.a = arVar;
        this.b = asVar;
    }

    @MainThread
    public final void run() {
        if (this.a.b) {
            ConnectionResult b = this.b.b();
            if (b.a()) {
                GoogleApiActivity.a(this.a.a(), b.d(), this.b.a(), false);
            } else if (this.a.d.a(b.c())) {
                this.a.d.a(this.a.a(), this.a.a, b.c(), this.a);
            } else if (b.c() == 18) {
                GoogleApiAvailability.a(this.a.a().getApplicationContext(), new au(this, GoogleApiAvailability.a(this.a.a(), this.a)));
            } else {
                this.a.a(b, this.b.a());
            }
        }
    }
}
