package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class fv extends fp implements ft {
    fv(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    public final Bundle a(Bundle bundle) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) bundle);
        Parcel a2 = a(1, a);
        Bundle bundle2 = (Bundle) fr.a(a2, Bundle.CREATOR);
        a2.recycle();
        return bundle2;
    }
}
