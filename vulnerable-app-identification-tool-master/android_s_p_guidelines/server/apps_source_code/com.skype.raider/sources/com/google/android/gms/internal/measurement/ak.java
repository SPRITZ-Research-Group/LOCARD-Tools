package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ak implements Creator<zzeu> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = a.a(parcel);
        long j = 0;
        zzer zzer = null;
        String str2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str2 = a.p(parcel, readInt);
                    break;
                case 3:
                    zzer = (zzer) a.a(parcel, readInt, zzer.CREATOR);
                    break;
                case 4:
                    str = a.p(parcel, readInt);
                    break;
                case 5:
                    j = a.h(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzeu(str2, zzer, str, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzeu[i];
    }
}
