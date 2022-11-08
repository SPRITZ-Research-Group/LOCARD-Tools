package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.h;
import com.google.android.gms.location.aj;

final class u extends aj {
    private final h<Object> a;

    public final synchronized void a(Location location) {
        this.a.a(new v(location));
    }
}
