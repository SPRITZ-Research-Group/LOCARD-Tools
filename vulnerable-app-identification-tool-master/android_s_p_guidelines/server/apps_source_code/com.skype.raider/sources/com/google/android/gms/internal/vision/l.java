package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class l implements Creator<zzk> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = a.a(parcel);
        long j = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i4 = a.f(parcel, readInt);
                    break;
                case 3:
                    i3 = a.f(parcel, readInt);
                    break;
                case 4:
                    i2 = a.f(parcel, readInt);
                    break;
                case 5:
                    j = a.h(parcel, readInt);
                    break;
                case 6:
                    i = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzk(i4, i3, i2, j, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzk[i];
    }
}
