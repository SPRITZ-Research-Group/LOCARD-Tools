package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.a.b;
import com.google.android.gms.common.api.e;
import com.google.android.gms.location.LocationRequest;

final class am extends b {
    private final /* synthetic */ LocationRequest b;
    private final /* synthetic */ PendingIntent c;

    am(e eVar, LocationRequest locationRequest, PendingIntent pendingIntent) {
        this.b = locationRequest;
        this.c = pendingIntent;
        super(eVar);
    }

    protected final /* synthetic */ void b(b bVar) throws RemoteException {
        ((w) bVar).a(this.b, this.c, new c(this));
    }
}
