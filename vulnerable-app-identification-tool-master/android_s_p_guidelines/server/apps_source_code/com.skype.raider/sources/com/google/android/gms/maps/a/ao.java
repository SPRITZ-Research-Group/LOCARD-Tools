package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;

public abstract class ao extends j implements an {
    public ao() {
        super("com.google.android.gms.maps.internal.ICancelableCallback");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        switch (i) {
            case 1:
                a();
                break;
            case 2:
                b();
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
