package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class j extends x implements i {
    public j() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a((zzad) af.a(parcel, zzad.CREATOR));
        return true;
    }
}
