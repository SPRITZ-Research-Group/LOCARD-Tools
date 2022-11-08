package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.internal.c.t;

public abstract class v extends j implements u {
    public v() {
        super("com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        boolean a = a(t.a(parcel.readStrongBinder()));
        parcel2.writeNoException();
        k.a(parcel2, a);
        return true;
    }
}
