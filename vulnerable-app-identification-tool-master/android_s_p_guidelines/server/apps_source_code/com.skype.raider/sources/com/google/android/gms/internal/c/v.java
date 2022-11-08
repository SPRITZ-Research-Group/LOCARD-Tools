package com.google.android.gms.internal.c;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public interface v extends IInterface {
    void a(float f) throws RemoteException;

    void a(int i) throws RemoteException;

    void a(List<LatLng> list) throws RemoteException;

    void a(boolean z) throws RemoteException;

    boolean a(v vVar) throws RemoteException;

    List<LatLng> b() throws RemoteException;

    void b(float f) throws RemoteException;

    void b(int i) throws RemoteException;

    int c() throws RemoteException;

    void d() throws RemoteException;

    void q_() throws RemoteException;
}
