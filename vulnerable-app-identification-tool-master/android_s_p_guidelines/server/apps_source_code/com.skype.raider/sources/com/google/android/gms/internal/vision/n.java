package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class n implements Creator<zzt> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int a = a.a(parcel);
        float f = 0.0f;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        String str2 = null;
        zzn zzn = null;
        zzn zzn2 = null;
        zzn zzn3 = null;
        zzac[] zzacArr = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    zzacArr = (zzac[]) a.b(parcel, readInt, zzac.CREATOR);
                    break;
                case 3:
                    zzn3 = (zzn) a.a(parcel, readInt, zzn.CREATOR);
                    break;
                case 4:
                    zzn2 = (zzn) a.a(parcel, readInt, zzn.CREATOR);
                    break;
                case 5:
                    zzn = (zzn) a.a(parcel, readInt, zzn.CREATOR);
                    break;
                case 6:
                    str2 = a.p(parcel, readInt);
                    break;
                case 7:
                    f = a.k(parcel, readInt);
                    break;
                case 8:
                    str = a.p(parcel, readInt);
                    break;
                case 9:
                    i3 = a.f(parcel, readInt);
                    break;
                case 10:
                    z = a.c(parcel, readInt);
                    break;
                case 11:
                    i2 = a.f(parcel, readInt);
                    break;
                case 12:
                    i = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzt(zzacArr, zzn3, zzn2, zzn, str2, f, str, i3, z, i2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzt[i];
    }
}
