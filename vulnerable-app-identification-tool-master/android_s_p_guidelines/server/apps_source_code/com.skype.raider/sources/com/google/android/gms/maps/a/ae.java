package com.google.android.gms.maps.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;

public abstract class ae extends j implements ad {
    public ae() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        e eVar;
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            eVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
            eVar = queryLocalInterface instanceof e ? (e) queryLocalInterface : new ai(readStrongBinder);
        }
        a(eVar);
        parcel2.writeNoException();
        return true;
    }
}
