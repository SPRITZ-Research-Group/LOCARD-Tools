package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ef implements Creator<zzr> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int a = a.a(parcel);
        boolean z = true;
        boolean z2 = false;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
        String str4 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str4 = a.p(parcel, readInt);
                    break;
                case 3:
                    i3 = a.f(parcel, readInt);
                    break;
                case 4:
                    i2 = a.f(parcel, readInt);
                    break;
                case 5:
                    str3 = a.p(parcel, readInt);
                    break;
                case 6:
                    str2 = a.p(parcel, readInt);
                    break;
                case 7:
                    z = a.c(parcel, readInt);
                    break;
                case 8:
                    str = a.p(parcel, readInt);
                    break;
                case 9:
                    z2 = a.c(parcel, readInt);
                    break;
                case 10:
                    i = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzr(str4, i3, i2, str3, str2, z, str, z2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }
}
