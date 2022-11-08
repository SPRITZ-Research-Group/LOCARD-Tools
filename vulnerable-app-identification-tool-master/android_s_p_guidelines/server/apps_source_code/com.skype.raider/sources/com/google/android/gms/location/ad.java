package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.x;

public class ad extends x implements ac {
    public static ac a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.IDeviceOrientationListener");
        return queryLocalInterface instanceof ac ? (ac) queryLocalInterface : new ae(iBinder);
    }

    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        throw new NoSuchMethodError();
    }
}
