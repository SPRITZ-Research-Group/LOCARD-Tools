package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.a.b;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.internal.c;
import com.google.android.gms.location.GeofencingRequest;

final class f extends h {
    private final /* synthetic */ GeofencingRequest b;
    private final /* synthetic */ PendingIntent c;

    f(e eVar, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        this.b = geofencingRequest;
        this.c = pendingIntent;
        super(eVar);
    }

    protected final /* synthetic */ void b(b bVar) throws RemoteException {
        ((w) bVar).a(this.b, this.c, (c.b) this);
    }
}
