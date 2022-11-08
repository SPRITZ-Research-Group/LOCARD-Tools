package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;

public abstract class e extends j implements d {
    public static d a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
        return queryLocalInterface instanceof d ? (d) queryLocalInterface : new f(iBinder);
    }
}
