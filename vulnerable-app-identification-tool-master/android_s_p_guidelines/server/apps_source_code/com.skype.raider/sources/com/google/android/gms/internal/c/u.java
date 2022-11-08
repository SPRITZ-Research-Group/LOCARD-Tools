package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.maps.model.LatLng;

public final class u extends a implements s {
    u(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    public final void a(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(22, u_);
    }

    public final void a(float f, float f2) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        u_.writeFloat(f2);
        b(19, u_);
    }

    public final void a(b bVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) bVar);
        b(18, u_);
    }

    public final void a(LatLng latLng) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) latLng);
        b(3, u_);
    }

    public final void a(String str) throws RemoteException {
        Parcel u_ = u_();
        u_.writeString(str);
        b(5, u_);
    }

    public final void a(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(9, u_);
    }

    public final boolean a(s sVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) sVar);
        u_ = a(16, u_);
        boolean a = k.a(u_);
        u_.recycle();
        return a;
    }

    public final LatLng b() throws RemoteException {
        Parcel a = a(4, u_());
        LatLng latLng = (LatLng) k.a(a, LatLng.CREATOR);
        a.recycle();
        return latLng;
    }

    public final void b(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(25, u_);
    }

    public final void b(float f, float f2) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        u_.writeFloat(f2);
        b(24, u_);
    }

    public final void b(String str) throws RemoteException {
        Parcel u_ = u_();
        u_.writeString(str);
        b(7, u_);
    }

    public final void b(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(20, u_);
    }

    public final void c() throws RemoteException {
        b(11, u_());
    }

    public final void c(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(27, u_);
    }

    public final void d() throws RemoteException {
        b(12, u_());
    }

    public final int e() throws RemoteException {
        Parcel a = a(17, u_());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final void p_() throws RemoteException {
        b(1, u_());
    }
}
