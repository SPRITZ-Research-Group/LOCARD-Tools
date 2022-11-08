package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;

public abstract class b extends j implements y {
    public static y a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
        return queryLocalInterface instanceof y ? (y) queryLocalInterface : new c(iBinder);
    }
}
