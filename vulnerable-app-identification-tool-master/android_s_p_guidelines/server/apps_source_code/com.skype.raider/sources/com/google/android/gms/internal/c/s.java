package com.google.android.gms.internal.c;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.maps.model.LatLng;

public interface s extends IInterface {
    void a(float f) throws RemoteException;

    void a(float f, float f2) throws RemoteException;

    void a(b bVar) throws RemoteException;

    void a(LatLng latLng) throws RemoteException;

    void a(String str) throws RemoteException;

    void a(boolean z) throws RemoteException;

    boolean a(s sVar) throws RemoteException;

    LatLng b() throws RemoteException;

    void b(float f) throws RemoteException;

    void b(float f, float f2) throws RemoteException;

    void b(String str) throws RemoteException;

    void b(boolean z) throws RemoteException;

    void c() throws RemoteException;

    void c(float f) throws RemoteException;

    void d() throws RemoteException;

    int e() throws RemoteException;

    void p_() throws RemoteException;
}
