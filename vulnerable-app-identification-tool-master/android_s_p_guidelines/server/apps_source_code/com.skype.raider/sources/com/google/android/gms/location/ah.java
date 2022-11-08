package com.google.android.gms.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.location.a;
import com.google.android.gms.internal.location.af;

public final class ah extends a implements af {
    ah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationCallback");
    }

    public final void a(LocationAvailability locationAvailability) throws RemoteException {
        Parcel a = a();
        af.a(a, (Parcelable) locationAvailability);
        b(2, a);
    }

    public final void a(LocationResult locationResult) throws RemoteException {
        Parcel a = a();
        af.a(a, (Parcelable) locationResult);
        b(1, a);
    }
}
