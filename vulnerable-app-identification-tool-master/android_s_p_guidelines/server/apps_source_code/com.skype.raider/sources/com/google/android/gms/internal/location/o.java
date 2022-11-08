package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.zzal;

public final class o extends a implements n {
    o(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public final void a(zzbf zzbf) throws RemoteException {
        Parcel a = a();
        af.a(a, (Parcelable) zzbf);
        a(59, a);
    }

    public final void a(zzo zzo) throws RemoteException {
        Parcel a = a();
        af.a(a, (Parcelable) zzo);
        a(75, a);
    }

    public final void a(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, l lVar) throws RemoteException {
        Parcel a = a();
        af.a(a, (Parcelable) geofencingRequest);
        af.a(a, (Parcelable) pendingIntent);
        af.a(a, (IInterface) lVar);
        a(57, a);
    }

    public final void a(zzal zzal, l lVar) throws RemoteException {
        Parcel a = a();
        af.a(a, (Parcelable) zzal);
        af.a(a, (IInterface) lVar);
        a(74, a);
    }

    public final void m_() throws RemoteException {
        Parcel a = a();
        af.a(a);
        a(12, a);
    }
}
