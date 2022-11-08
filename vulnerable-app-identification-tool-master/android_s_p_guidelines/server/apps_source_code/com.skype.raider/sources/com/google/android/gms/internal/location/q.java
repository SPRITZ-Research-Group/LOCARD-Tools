package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.h;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.ag;

final class q extends ag {
    private final h<Object> a;

    public final void a(LocationAvailability locationAvailability) {
        this.a.a(new s(locationAvailability));
    }

    public final void a(LocationResult locationResult) {
        this.a.a(new r(locationResult));
    }
}
