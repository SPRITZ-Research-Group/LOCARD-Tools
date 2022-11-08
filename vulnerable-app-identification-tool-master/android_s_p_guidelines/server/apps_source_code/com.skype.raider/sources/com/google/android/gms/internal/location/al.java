package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.f;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.a;

@VisibleForTesting
public final class al implements a {
    public final f<Status> a(e eVar, LocationRequest locationRequest, PendingIntent pendingIntent) {
        return eVar.a(new am(eVar, locationRequest, pendingIntent));
    }
}
