package com.google.android.gms.maps.a;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public interface d extends IInterface {
    b a(LatLng latLng) throws RemoteException;

    LatLng a(b bVar) throws RemoteException;

    VisibleRegion a() throws RemoteException;
}
