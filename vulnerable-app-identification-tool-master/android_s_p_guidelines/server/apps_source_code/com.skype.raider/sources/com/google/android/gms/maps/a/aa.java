package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.w;

public abstract class aa extends j implements z {
    public aa() {
        super("com.google.android.gms.maps.internal.IOnPolygonClickListener");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a(w.a(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
