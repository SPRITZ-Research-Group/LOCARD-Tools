package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class v implements Creator<zzed> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        String str = null;
        String str2 = null;
        zzjx zzjx = null;
        long j = 0;
        boolean z = false;
        String str3 = null;
        zzeu zzeu = null;
        long j2 = 0;
        zzeu zzeu2 = null;
        long j3 = 0;
        zzeu zzeu3 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = a.p(parcel, readInt);
                    break;
                case 3:
                    str2 = a.p(parcel, readInt);
                    break;
                case 4:
                    zzjx = (zzjx) a.a(parcel, readInt, zzjx.CREATOR);
                    break;
                case 5:
                    j = a.h(parcel, readInt);
                    break;
                case 6:
                    z = a.c(parcel, readInt);
                    break;
                case 7:
                    str3 = a.p(parcel, readInt);
                    break;
                case 8:
                    zzeu = (zzeu) a.a(parcel, readInt, zzeu.CREATOR);
                    break;
                case 9:
                    j2 = a.h(parcel, readInt);
                    break;
                case 10:
                    zzeu2 = (zzeu) a.a(parcel, readInt, zzeu.CREATOR);
                    break;
                case 11:
                    j3 = a.h(parcel, readInt);
                    break;
                case 12:
                    zzeu3 = (zzeu) a.a(parcel, readInt, zzeu.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzed(str, str2, zzjx, j, z, str3, zzeu, j2, zzeu2, j3, zzeu3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzed[i];
    }
}
