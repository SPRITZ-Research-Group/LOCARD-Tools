package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;

public final class r extends a implements p {
    r(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    public final void a(double d) throws RemoteException {
        Parcel u_ = u_();
        u_.writeDouble(d);
        b(5, u_);
    }

    public final void a(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(7, u_);
    }

    public final void a(int i) throws RemoteException {
        Parcel u_ = u_();
        u_.writeInt(i);
        b(9, u_);
    }

    public final void a(LatLng latLng) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) latLng);
        b(3, u_);
    }

    public final boolean a(p pVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) pVar);
        u_ = a(17, u_);
        boolean a = k.a(u_);
        u_.recycle();
        return a;
    }

    public final int b() throws RemoteException {
        Parcel a = a(18, u_());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final void b(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(13, u_);
    }

    public final void b(int i) throws RemoteException {
        Parcel u_ = u_();
        u_.writeInt(i);
        b(11, u_);
    }

    public final void o_() throws RemoteException {
        b(1, u_());
    }
}
