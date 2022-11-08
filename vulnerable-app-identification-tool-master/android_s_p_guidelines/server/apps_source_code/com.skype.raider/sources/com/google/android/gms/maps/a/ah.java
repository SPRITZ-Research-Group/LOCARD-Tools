package com.google.android.gms.maps.a;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.b.b.a;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.k;

public abstract class ah extends j implements ag {
    public ah() {
        super("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        switch (i) {
            case 1:
                a((Bitmap) k.a(parcel, Bitmap.CREATOR));
                break;
            case 2:
                a(a.a(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
