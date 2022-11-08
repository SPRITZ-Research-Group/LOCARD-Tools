package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.af;
import com.google.android.gms.internal.location.x;

public abstract class ag extends x implements af {
    public static af a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return queryLocalInterface instanceof af ? (af) queryLocalInterface : new ah(iBinder);
    }

    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        switch (i) {
            case 1:
                a((LocationResult) af.a(parcel, LocationResult.CREATOR));
                break;
            case 2:
                a((LocationAvailability) af.a(parcel, LocationAvailability.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
