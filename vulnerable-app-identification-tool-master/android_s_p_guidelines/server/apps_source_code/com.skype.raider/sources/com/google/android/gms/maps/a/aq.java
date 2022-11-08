package com.google.android.gms.maps.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.internal.c.a;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.internal.c.m;
import com.google.android.gms.internal.c.n;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public final class aq extends a implements ap {
    aq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICreator");
    }

    public final a a() throws RemoteException {
        a aVar;
        Parcel a = a(4, u_());
        IBinder readStrongBinder = a.readStrongBinder();
        if (readStrongBinder == null) {
            aVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            aVar = queryLocalInterface instanceof a ? (a) queryLocalInterface : new y(readStrongBinder);
        }
        a.recycle();
        return aVar;
    }

    public final c a(b bVar, GoogleMapOptions googleMapOptions) throws RemoteException {
        c cVar;
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        k.a(u_, (Parcelable) googleMapOptions);
        Parcel a = a(3, u_);
        IBinder readStrongBinder = a.readStrongBinder();
        if (readStrongBinder == null) {
            cVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
            cVar = queryLocalInterface instanceof c ? (c) queryLocalInterface : new au(readStrongBinder);
        }
        a.recycle();
        return cVar;
    }

    public final f a(b bVar, StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException {
        f fVar;
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        k.a(u_, (Parcelable) streetViewPanoramaOptions);
        Parcel a = a(7, u_);
        IBinder readStrongBinder = a.readStrongBinder();
        if (readStrongBinder == null) {
            fVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            fVar = queryLocalInterface instanceof f ? (f) queryLocalInterface : new aj(readStrongBinder);
        }
        a.recycle();
        return fVar;
    }

    public final void a(b bVar, int i) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        u_.writeInt(i);
        b(6, u_);
    }

    public final m b() throws RemoteException {
        Parcel a = a(5, u_());
        m a2 = n.a(a.readStrongBinder());
        a.recycle();
        return a2;
    }
}
