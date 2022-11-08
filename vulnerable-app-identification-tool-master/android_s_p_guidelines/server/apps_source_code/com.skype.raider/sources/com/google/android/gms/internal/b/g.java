package com.google.android.gms.internal.b;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

public final class g extends a implements e {
    g(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.iid.IMessengerCompat");
    }

    public final void a(Message message) throws RemoteException {
        Parcel a = a();
        c.a(a, message);
        a(a);
    }
}
