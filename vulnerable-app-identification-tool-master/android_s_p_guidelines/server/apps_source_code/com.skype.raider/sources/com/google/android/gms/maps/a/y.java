package com.google.android.gms.maps.a;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.internal.c.a;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class y extends a implements a {
    y(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    public final b a(LatLng latLng) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) latLng);
        u_ = a(8, u_);
        b a = b.a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final b a(LatLngBounds latLngBounds, int i) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) latLngBounds);
        u_.writeInt(i);
        u_ = a(10, u_);
        b a = b.a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final b a(LatLngBounds latLngBounds, int i, int i2) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) latLngBounds);
        u_.writeInt(i);
        u_.writeInt(i2);
        u_.writeInt(0);
        u_ = a(11, u_);
        b a = b.a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final b b(LatLng latLng) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) latLng);
        u_.writeFloat(10.0f);
        u_ = a(9, u_);
        b a = b.a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }
}
