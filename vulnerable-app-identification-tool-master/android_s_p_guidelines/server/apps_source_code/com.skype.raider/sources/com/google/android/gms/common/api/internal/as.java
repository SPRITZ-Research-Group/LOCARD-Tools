package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ab;

final class as {
    private final int a;
    private final ConnectionResult b;

    as(ConnectionResult connectionResult, int i) {
        ab.a((Object) connectionResult);
        this.b = connectionResult;
        this.a = i;
    }

    final int a() {
        return this.a;
    }

    final ConnectionResult b() {
        return this.b;
    }
}
