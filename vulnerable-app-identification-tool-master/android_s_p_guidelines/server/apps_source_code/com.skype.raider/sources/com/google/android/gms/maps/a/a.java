package com.google.android.gms.maps.a;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface a extends IInterface {
    b a(LatLng latLng) throws RemoteException;

    b a(LatLngBounds latLngBounds, int i) throws RemoteException;

    b a(LatLngBounds latLngBounds, int i, int i2) throws RemoteException;

    b b(LatLng latLng) throws RemoteException;
}
