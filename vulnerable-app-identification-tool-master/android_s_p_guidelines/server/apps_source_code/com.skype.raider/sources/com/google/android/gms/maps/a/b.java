package com.google.android.gms.maps.a;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.internal.c.d;
import com.google.android.gms.internal.c.p;
import com.google.android.gms.internal.c.s;
import com.google.android.gms.internal.c.v;
import com.google.android.gms.internal.c.y;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public interface b extends IInterface {
    d a(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    p a(CircleOptions circleOptions) throws RemoteException;

    s a(MarkerOptions markerOptions) throws RemoteException;

    v a(PolygonOptions polygonOptions) throws RemoteException;

    y a(PolylineOptions polylineOptions) throws RemoteException;

    CameraPosition a() throws RemoteException;

    void a(int i) throws RemoteException;

    void a(int i, int i2, int i3, int i4) throws RemoteException;

    void a(com.google.android.gms.b.b bVar) throws RemoteException;

    void a(com.google.android.gms.b.b bVar, int i, an anVar) throws RemoteException;

    void a(ab abVar) throws RemoteException;

    void a(ag agVar) throws RemoteException;

    void a(as asVar) throws RemoteException;

    void a(av avVar) throws RemoteException;

    void a(k kVar) throws RemoteException;

    void a(m mVar) throws RemoteException;

    void a(o oVar) throws RemoteException;

    void a(q qVar) throws RemoteException;

    void a(u uVar) throws RemoteException;

    void a(w wVar) throws RemoteException;

    void a(z zVar) throws RemoteException;

    void a(boolean z) throws RemoteException;

    boolean a(MapStyleOptions mapStyleOptions) throws RemoteException;

    g b() throws RemoteException;

    void b(com.google.android.gms.b.b bVar) throws RemoteException;

    boolean b(boolean z) throws RemoteException;

    d c() throws RemoteException;

    void c(boolean z) throws RemoteException;

    void d(boolean z) throws RemoteException;
}
