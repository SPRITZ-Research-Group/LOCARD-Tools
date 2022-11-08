package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;

public abstract class n extends j implements m {
    public static m a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
        return queryLocalInterface instanceof m ? (m) queryLocalInterface : new o(iBinder);
    }
}
