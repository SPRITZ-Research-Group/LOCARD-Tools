package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.zzal;

public interface n extends IInterface {
    void a(zzbf zzbf) throws RemoteException;

    void a(zzo zzo) throws RemoteException;

    void a(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, l lVar) throws RemoteException;

    void a(zzal zzal, l lVar) throws RemoteException;

    void m_() throws RemoteException;
}
