package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class m extends x implements l {
    public m() {
        super("com.google.android.gms.location.internal.IGeofencerCallbacks");
    }

    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        int readInt;
        switch (i) {
            case 1:
                readInt = parcel.readInt();
                parcel.createStringArray();
                a(readInt);
                break;
            case 2:
                readInt = parcel.readInt();
                parcel.createStringArray();
                b(readInt);
                break;
            case 3:
                readInt = parcel.readInt();
                af.a(parcel, PendingIntent.CREATOR);
                c(readInt);
                break;
            default:
                return false;
        }
        return true;
    }
}
