package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.maps.model.LatLng;

public abstract class r extends j implements q {
    public r() {
        super("com.google.android.gms.maps.internal.IOnMapLongClickListener");
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
