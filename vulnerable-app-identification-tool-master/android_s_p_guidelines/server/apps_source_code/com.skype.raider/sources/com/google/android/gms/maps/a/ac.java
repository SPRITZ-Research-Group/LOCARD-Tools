package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.b;
import com.google.android.gms.internal.c.j;

public abstract class ac extends j implements ab {
    public ac() {
        super("com.google.android.gms.maps.internal.IOnPolylineClickListener");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a(b.a(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
