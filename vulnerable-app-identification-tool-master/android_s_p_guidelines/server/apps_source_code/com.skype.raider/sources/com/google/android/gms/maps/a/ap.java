package com.google.android.gms.maps.a;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.internal.c.m;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public interface ap extends IInterface {
    a a() throws RemoteException;

    c a(b bVar, GoogleMapOptions googleMapOptions) throws RemoteException;

    f a(b bVar, StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException;

    void a(b bVar, int i) throws RemoteException;

    m b() throws RemoteException;
}
