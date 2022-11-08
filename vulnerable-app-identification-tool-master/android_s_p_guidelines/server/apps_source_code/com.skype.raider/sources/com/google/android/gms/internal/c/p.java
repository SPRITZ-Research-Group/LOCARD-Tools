package com.google.android.gms.internal.c;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;

public interface p extends IInterface {
    void a(double d) throws RemoteException;

    void a(float f) throws RemoteException;

    void a(int i) throws RemoteException;

    void a(LatLng latLng) throws RemoteException;

    boolean a(p pVar) throws RemoteException;

    int b() throws RemoteException;

    void b(float f) throws RemoteException;

    void b(int i) throws RemoteException;

    void o_() throws RemoteException;
}
