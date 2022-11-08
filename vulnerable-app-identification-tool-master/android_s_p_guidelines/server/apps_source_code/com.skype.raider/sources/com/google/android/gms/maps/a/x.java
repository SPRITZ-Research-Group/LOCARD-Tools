package com.google.android.gms.maps.a;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.t;

public abstract class x extends j implements w {
    public x() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        switch (i) {
            case 1:
                a(t.a(parcel.readStrongBinder()));
                break;
            case 2:
                b(t.a(parcel.readStrongBinder()));
                break;
            case 3:
                c(t.a(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
