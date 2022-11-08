package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class b implements Creator<ConnectionResult> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        PendingIntent pendingIntent = null;
        int i = 0;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = a.f(parcel, readInt);
                    break;
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    pendingIntent = (PendingIntent) a.a(parcel, readInt, PendingIntent.CREATOR);
                    break;
                case 4:
                    str = a.p(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ConnectionResult(i2, i, pendingIntent, str);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ConnectionResult[i];
    }
}
