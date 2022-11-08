package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public final class c extends a implements y {
    c(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    public final void a(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(5, u_);
    }

    public final void a(int i) throws RemoteException {
        Parcel u_ = u_();
        u_.writeInt(i);
        b(7, u_);
    }

    public final void a(List<LatLng> list) throws RemoteException {
        Parcel u_ = u_();
        u_.writeTypedList(list);
        b(3, u_);
    }

    public final void a(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(13, u_);
    }

    public final boolean a(y yVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) yVar);
        u_ = a(15, u_);
        boolean a = k.a(u_);
        u_.recycle();
        return a;
    }

    public final void b() throws RemoteException {
        b(1, u_());
    }

    public final void b(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(9, u_);
    }

    public final List<LatLng> c() throws RemoteException {
        Parcel a = a(4, u_());
        List createTypedArrayList = a.createTypedArrayList(LatLng.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final int d() throws RemoteException {
        Parcel a = a(16, u_());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final void e() throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, true);
        b(17, u_);
    }
}
