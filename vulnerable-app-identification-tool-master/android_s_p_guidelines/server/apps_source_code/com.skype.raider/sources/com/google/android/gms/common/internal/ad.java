package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ad implements Creator<ResolveAccountResponse> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int a = a.a(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    iBinder = a.q(parcel, readInt);
                    break;
                case 3:
                    connectionResult = (ConnectionResult) a.a(parcel, readInt, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z2 = a.c(parcel, readInt);
                    break;
                case 5:
                    z = a.c(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ResolveAccountResponse(i, iBinder, connectionResult, z2, z);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ResolveAccountResponse[i];
    }
}
