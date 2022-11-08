package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class s implements Creator<PatternItem> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        int i = 0;
        Float f = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    f = a.l(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new PatternItem(i, f);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PatternItem[i];
    }
}
