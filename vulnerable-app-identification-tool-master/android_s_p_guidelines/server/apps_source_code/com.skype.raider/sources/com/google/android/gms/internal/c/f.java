package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class f extends a implements d {
    f(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    public final void a(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        b(4, u_);
    }

    public final boolean a(d dVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) dVar);
        u_ = a(8, u_);
        boolean a = k.a(u_);
        u_.recycle();
        return a;
    }

    public final void b() throws RemoteException {
        b(2, u_());
    }

    public final int c() throws RemoteException {
        Parcel a = a(9, u_());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final void n_() throws RemoteException {
        b(1, u_());
    }
}
