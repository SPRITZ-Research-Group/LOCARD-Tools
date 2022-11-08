package com.google.android.gms.internal.a;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class g extends a implements e {
    g(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    public final String a() throws RemoteException {
        Parcel a = a(1, l_());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final boolean b() throws RemoteException {
        Parcel l_ = l_();
        c.b(l_);
        l_ = a(2, l_);
        boolean a = c.a(l_);
        l_.recycle();
        return a;
    }
}
