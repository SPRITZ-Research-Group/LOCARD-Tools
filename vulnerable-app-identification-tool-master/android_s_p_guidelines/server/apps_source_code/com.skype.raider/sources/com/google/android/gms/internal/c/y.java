package com.google.android.gms.internal.c;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public interface y extends IInterface {
    void a(float f) throws RemoteException;

    void a(int i) throws RemoteException;

    void a(List<LatLng> list) throws RemoteException;

    void a(boolean z) throws RemoteException;

    boolean a(y yVar) throws RemoteException;

    void b() throws RemoteException;

    void b(float f) throws RemoteException;

    List<LatLng> c() throws RemoteException;

    int d() throws RemoteException;

    void e() throws RemoteException;
}
