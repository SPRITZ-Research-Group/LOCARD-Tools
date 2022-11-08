package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class d implements Creator<Feature> {
    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new Feature[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        String str = null;
        int i = 0;
        long j = -1;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = a.p(parcel, readInt);
                    break;
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    j = a.h(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new Feature(str, i, j);
    }
}
