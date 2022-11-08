package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.location.a;
import com.google.android.gms.internal.location.af;

public final class ak extends a implements ai {
    ak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationListener");
    }

    public final void a(Location location) throws RemoteException {
        Parcel a = a();
        af.a(a, (Parcelable) location);
        b(1, a);
    }
}
