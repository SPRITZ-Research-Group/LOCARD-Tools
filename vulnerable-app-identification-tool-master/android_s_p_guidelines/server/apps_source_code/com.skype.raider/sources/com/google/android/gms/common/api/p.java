package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.safeparcel.a;

public final class p implements Creator<Status> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int a = a.a(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    str = a.p(parcel, readInt);
                    break;
                case 3:
                    pendingIntent = (PendingIntent) a.a(parcel, readInt, PendingIntent.CREATOR);
                    break;
                case Constants.ONE_SECOND /*1000*/:
                    i2 = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new Status(i2, i, str, pendingIntent);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Status[i];
    }
}
