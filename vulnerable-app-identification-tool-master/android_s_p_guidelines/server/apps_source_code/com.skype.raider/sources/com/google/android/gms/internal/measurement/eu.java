package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class eu implements Creator<zzjx> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Double d = null;
        int a = a.a(parcel);
        int i = 0;
        long j = 0;
        String str = null;
        String str2 = null;
        Float f = null;
        Long l = null;
        String str3 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    str3 = a.p(parcel, readInt);
                    break;
                case 3:
                    j = a.h(parcel, readInt);
                    break;
                case 4:
                    l = a.i(parcel, readInt);
                    break;
                case 5:
                    f = a.l(parcel, readInt);
                    break;
                case 6:
                    str2 = a.p(parcel, readInt);
                    break;
                case 7:
                    str = a.p(parcel, readInt);
                    break;
                case 8:
                    d = a.n(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzjx(i, str3, j, l, f, str2, str, d);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzjx[i];
    }
}
