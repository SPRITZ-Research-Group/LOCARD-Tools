package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class q implements Creator<zzaj> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        long j = -1;
        int i = 1;
        int a = a.a(parcel);
        long j2 = -1;
        int i2 = 1;
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
                    j2 = a.h(parcel, readInt);
                    break;
                case 4:
                    j = a.h(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzaj(i2, i, j2, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaj[i];
    }
}
