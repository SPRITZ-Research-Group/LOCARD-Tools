package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.IInterface;

public abstract class w extends j implements v {
    public static v a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
        return queryLocalInterface instanceof v ? (v) queryLocalInterface : new x(iBinder);
    }
}
