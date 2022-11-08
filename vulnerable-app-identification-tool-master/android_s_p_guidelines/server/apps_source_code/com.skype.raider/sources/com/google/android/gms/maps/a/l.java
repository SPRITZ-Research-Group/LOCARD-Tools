package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.t;

public abstract class l extends j implements k {
    public l() {
        super("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a(t.a(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
