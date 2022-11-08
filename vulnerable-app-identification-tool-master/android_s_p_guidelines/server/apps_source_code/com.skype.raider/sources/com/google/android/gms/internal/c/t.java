package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;

public abstract class t extends j implements s {
    public static s a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        return queryLocalInterface instanceof s ? (s) queryLocalInterface : new u(iBinder);
    }
}
