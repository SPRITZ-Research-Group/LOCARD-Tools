package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class c implements Creator<zzc> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        long j = 0;
        int a = a.a(parcel);
        boolean z = false;
        long j2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    z = a.c(parcel, readInt);
                    break;
                case 2:
                    j = a.h(parcel, readInt);
                    break;
                case 3:
                    j2 = a.h(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzc(z, j2, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzc[i];
    }
}
