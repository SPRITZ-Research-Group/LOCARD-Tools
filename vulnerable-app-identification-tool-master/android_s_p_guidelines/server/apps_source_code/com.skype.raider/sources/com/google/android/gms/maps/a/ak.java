package com.google.android.gms.maps.a;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.a;
import com.google.android.gms.internal.c.k;

public final class ak extends a implements g {
    ak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final void a(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(2, u_);
    }

    public final boolean a() throws RemoteException {
        Parcel a = a(12, u_());
        boolean a2 = k.a(a);
        a.recycle();
        return a2;
    }

    public final void b(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(3, u_);
    }

    public final void c(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(4, u_);
    }

    public final void d(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(5, u_);
    }

    public final void e(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(6, u_);
    }

    public final void f(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(7, u_);
    }

    public final void g(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(16, u_);
    }

    public final void h(boolean z) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, z);
        b(18, u_);
    }
}
