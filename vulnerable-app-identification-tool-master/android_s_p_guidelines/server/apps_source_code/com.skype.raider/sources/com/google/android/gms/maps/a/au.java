package com.google.android.gms.maps.a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.internal.c.a;
import com.google.android.gms.internal.c.k;

public final class au extends a implements c {
    au(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    public final void a() throws RemoteException {
        b(3, u_());
    }

    public final void a(Bundle bundle) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) bundle);
        b(2, u_);
    }

    public final void a(s sVar) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (IInterface) sVar);
        b(9, u_);
    }

    public final void b() throws RemoteException {
        b(4, u_());
    }

    public final void c() throws RemoteException {
        b(5, u_());
    }

    public final b d() throws RemoteException {
        Parcel a = a(8, u_());
        b a2 = b.a.a(a.readStrongBinder());
        a.recycle();
        return a2;
    }
}
