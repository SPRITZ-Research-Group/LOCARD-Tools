package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.SignInResponse;

final class ag implements Runnable {
    private final /* synthetic */ SignInResponse a;
    private final /* synthetic */ ad b;

    ag(ad adVar, SignInResponse signInResponse) {
        this.b = adVar;
        this.a = signInResponse;
    }

    public final void run() {
        ad.a(this.b, this.a);
    }
}
