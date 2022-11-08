package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;

public abstract class q extends j implements p {
    public static p a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        return queryLocalInterface instanceof p ? (p) queryLocalInterface : new r(iBinder);
    }
}
