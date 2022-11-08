package com.google.android.gms.maps.a;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.c.k;
import com.google.android.gms.internal.c.t;

public abstract class at extends j implements as {
    public at() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        IInterface a;
        switch (i) {
            case 1:
                a = a(t.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                k.a(parcel2, a);
                break;
            case 2:
                a = b(t.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                k.a(parcel2, a);
                break;
            default:
                return false;
        }
        return true;
    }
}
