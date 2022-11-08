package com.google.android.gms.maps.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.internal.c.a;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class af extends a implements d {
    af(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    public final b a(LatLng latLng) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) latLng);
        u_ = a(2, u_);
        b a = b.a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final LatLng a(b bVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        Parcel a = a(1, u_);
        LatLng latLng = (LatLng) k.a(a, LatLng.CREATOR);
        a.recycle();
        return latLng;
    }

    public final VisibleRegion a() throws RemoteException {
        Parcel a = a(3, u_());
        VisibleRegion visibleRegion = (VisibleRegion) k.a(a, VisibleRegion.CREATOR);
        a.recycle();
        return visibleRegion;
    }
}
