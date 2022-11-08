package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.safeparcel.a;

public final class i implements Creator<LocationAvailability> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 1;
        int a = a.a(parcel);
        int i2 = Constants.ONE_SECOND;
        long j = 0;
        zzaj[] zzajArr = null;
        int i3 = 1;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i3 = a.f(parcel, readInt);
                    break;
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    j = a.h(parcel, readInt);
                    break;
                case 4:
                    i2 = a.f(parcel, readInt);
                    break;
                case 5:
                    zzajArr = (zzaj[]) a.b(parcel, readInt, zzaj.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new LocationAvailability(i2, i3, i, j, zzajArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationAvailability[i];
    }
}
