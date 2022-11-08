package com.google.android.gms.maps.a;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;

public interface m extends IInterface {
    void a(LatLng latLng) throws RemoteException;
}
