package com.google.android.gms.maps.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.c.a;
import com.google.android.gms.internal.c.b;
import com.google.android.gms.internal.c.d;
import com.google.android.gms.internal.c.e;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.internal.c.p;
import com.google.android.gms.internal.c.q;
import com.google.android.gms.internal.c.s;
import com.google.android.gms.internal.c.t;
import com.google.android.gms.internal.c.v;
import com.google.android.gms.internal.c.w;
import com.google.android.gms.internal.c.y;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class ar extends a implements b {
    ar(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final d a(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) tileOverlayOptions);
        u_ = a(13, u_);
        d a = e.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final p a(CircleOptions circleOptions) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) circleOptions);
        u_ = a(35, u_);
        p a = q.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final s a(MarkerOptions markerOptions) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) markerOptions);
        u_ = a(11, u_);
        s a = t.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final v a(PolygonOptions polygonOptions) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) polygonOptions);
        u_ = a(10, u_);
        v a = w.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final y a(PolylineOptions polylineOptions) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) polylineOptions);
        u_ = a(9, u_);
        y a = b.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final CameraPosition a() throws RemoteException {
        Parcel a = a(1, u_());
        CameraPosition cameraPosition = (CameraPosition) k.a(a, CameraPosition.CREATOR);
        a.recycle();
        return cameraPosition;
    }

    public final void a(int i) throws RemoteException {
        Parcel u_ = u_();
        u_.writeInt(i);
        b(16, u_);
    }

    public final void a(int i, int i2, int i3, int i4) throws RemoteException {
        Parcel u_ = u_();
        u_.writeInt(i);
        u_.writeInt(i2);
        u_.writeInt(i3);
        u_.writeInt(i4);
        b(39, u_);
    }

    public final void a(com.google.android.gms.b.b bVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        b(4, u_);
    }

    public final void a(com.google.android.gms.b.b bVar, int i, an anVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        u_.writeInt(i);
        k.a(u_, (IInterface) anVar);
        b(7, u_);
    }

    public final void a(ab abVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) abVar);
        b(87, u_);
    }

    public final void a(ag agVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) agVar);
        k.a(u_, null);
        b(38, u_);
    }

    public final void a(as asVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) asVar);
        b(33, u_);
    }

    public final void a(av avVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) avVar);
        b(27, u_);
    }

    public final void a(k kVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) kVar);
        b(32, u_);
    }

    public final void a(m mVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) mVar);
        b(28, u_);
    }

    public final void a(o oVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) oVar);
        b(42, u_);
    }

    public final void a(q qVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) qVar);
        b(29, u_);
    }

    public final void a(u uVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) uVar);
        b(30, u_);
    }

    public final void a(w wVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) wVar);
        b(31, u_);
    }

    public final void a(z zVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) zVar);
        b(85, u_);
    }

    public final void a(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(18, u_);
    }

    public final boolean a(MapStyleOptions mapStyleOptions) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) mapStyleOptions);
        u_ = a(91, u_);
        boolean a = k.a(u_);
        u_.recycle();
        return a;
    }

    public final g b() throws RemoteException {
        g gVar;
        Parcel a = a(25, u_());
        IBinder readStrongBinder = a.readStrongBinder();
        if (readStrongBinder == null) {
            gVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            gVar = queryLocalInterface instanceof g ? (g) queryLocalInterface : new ak(readStrongBinder);
        }
        a.recycle();
        return gVar;
    }

    public final void b(com.google.android.gms.b.b bVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        b(5, u_);
    }

    public final boolean b(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        u_ = a(20, u_);
        boolean a = k.a(u_);
        u_.recycle();
        return a;
    }

    public final d c() throws RemoteException {
        d dVar;
        Parcel a = a(26, u_());
        IBinder readStrongBinder = a.readStrongBinder();
        if (readStrongBinder == null) {
            dVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            dVar = queryLocalInterface instanceof d ? (d) queryLocalInterface : new af(readStrongBinder);
        }
        a.recycle();
        return dVar;
    }

    public final void c(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(22, u_);
    }

    public final void d(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(41, u_);
    }
}
