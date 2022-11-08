package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class h extends j implements g {
    public h() {
        super("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public static g a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        return queryLocalInterface instanceof g ? (g) queryLocalInterface : new i(iBinder);
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        Parcelable a = a(parcel.readInt(), parcel.readInt(), parcel.readInt());
        parcel2.writeNoException();
        k.b(parcel2, a);
        return true;
    }
}
