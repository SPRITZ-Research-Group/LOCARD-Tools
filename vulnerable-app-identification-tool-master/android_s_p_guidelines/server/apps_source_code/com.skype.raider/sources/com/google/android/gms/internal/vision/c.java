package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class c implements Creator<zzac> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = a.a(parcel);
        float f = 0.0f;
        boolean z = false;
        String str2 = null;
        zzn zzn = null;
        zzn zzn2 = null;
        zzx[] zzxArr = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    zzxArr = (zzx[]) a.b(parcel, readInt, zzx.CREATOR);
                    break;
                case 3:
                    zzn2 = (zzn) a.a(parcel, readInt, zzn.CREATOR);
                    break;
                case 4:
                    zzn = (zzn) a.a(parcel, readInt, zzn.CREATOR);
                    break;
                case 5:
                    str2 = a.p(parcel, readInt);
                    break;
                case 6:
                    f = a.k(parcel, readInt);
                    break;
                case 7:
                    str = a.p(parcel, readInt);
                    break;
                case 8:
                    z = a.c(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzac(zzxArr, zzn2, zzn, str2, f, str, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzac[i];
    }
}
