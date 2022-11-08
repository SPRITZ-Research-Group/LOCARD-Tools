package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.e.a;
import com.google.android.gms.common.api.e.b;
import com.google.android.gms.common.api.internal.c;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.g;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzal;
import javax.annotation.Nullable;

public final class w extends ah {
    private final p f;

    public w(Context context, Looper looper, a aVar, b bVar, String str, @Nullable g gVar) {
        super(context, looper, aVar, bVar, str, gVar);
        this.f = new p(context, this.e);
    }

    public final void a() {
        synchronized (this.f) {
            if (b()) {
                try {
                    this.f.a();
                    this.f.b();
                } catch (Exception e) {
                }
            }
            super.a();
        }
    }

    public final void a(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, c.b<Status> bVar) throws RemoteException {
        o();
        ab.a((Object) geofencingRequest, (Object) "geofencingRequest can't be null.");
        ab.a((Object) pendingIntent, (Object) "PendingIntent must be specified.");
        ab.a((Object) bVar, (Object) "ResultHolder not provided.");
        ((n) q()).a(geofencingRequest, pendingIntent, new y(bVar));
    }

    public final void a(LocationRequest locationRequest, PendingIntent pendingIntent, i iVar) throws RemoteException {
        this.f.a(locationRequest, pendingIntent, iVar);
    }

    public final void a(zzal zzal, c.b<Status> bVar) throws RemoteException {
        o();
        ab.a((Object) zzal, (Object) "removeGeofencingRequest can't be null.");
        ab.a((Object) bVar, (Object) "ResultHolder not provided.");
        ((n) q()).a(zzal, new z(bVar));
    }
}
