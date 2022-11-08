package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public final class x extends a implements v {
    x(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
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

    public final void a(List<LatLng> list) throws RemoteException {
        Parcel u_ = u_();
        u_.writeTypedList(list);
        b(3, u_);
    }

    public final void a(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(17, u_);
    }

    public final boolean a(v vVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) vVar);
        u_ = a(19, u_);
        boolean a = k.a(u_);
        u_.recycle();
        return a;
    }

    public final List<LatLng> b() throws RemoteException {
        Parcel a = a(4, u_());
        List createTypedArrayList = a.createTypedArrayList(LatLng.CREATOR);
        a.recycle();
        return createTypedArrayList;
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

    public final int c() throws RemoteException {
        Parcel a = a(20, u_());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final void d() throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, true);
        b(21, u_);
    }

    public final void q_() throws RemoteException {
        b(1, u_());
    }
}
