package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.maps.model.LatLng;

public abstract class n extends j implements m {
    public n() {
        super("com.google.android.gms.maps.internal.IOnMapClickListener");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a((LatLng) k.a(parcel, LatLng.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
