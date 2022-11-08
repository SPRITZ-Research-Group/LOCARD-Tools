package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.maps.model.CameraPosition;

public abstract class aw extends j implements av {
    public aw() {
        super("com.google.android.gms.maps.internal.IOnCameraChangeListener");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a((CameraPosition) k.a(parcel, CameraPosition.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
