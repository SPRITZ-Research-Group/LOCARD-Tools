package com.google.android.gms.maps.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;

public abstract class t extends j implements s {
    public t() {
        super("com.google.android.gms.maps.internal.IOnMapReadyCallback");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        b bVar;
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            bVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            bVar = queryLocalInterface instanceof b ? (b) queryLocalInterface : new ar(readStrongBinder);
        }
        a(bVar);
        parcel2.writeNoException();
        return true;
    }
}
