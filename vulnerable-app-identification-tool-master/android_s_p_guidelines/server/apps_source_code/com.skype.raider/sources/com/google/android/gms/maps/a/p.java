package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;

public abstract class p extends j implements o {
    public p() {
        super("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a();
        parcel2.writeNoException();
        return true;
    }
}
