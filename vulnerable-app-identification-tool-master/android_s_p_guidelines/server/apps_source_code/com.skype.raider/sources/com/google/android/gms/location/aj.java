package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.af;
import com.google.android.gms.internal.location.x;

public abstract class aj extends x implements ai {
    public static ai a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        return queryLocalInterface instanceof ai ? (ai) queryLocalInterface : new ak(iBinder);
    }

    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        if (i != 1) {
            return false;
        }
        a((Location) af.a(parcel, Location.CREATOR));
        return true;
    }
}
