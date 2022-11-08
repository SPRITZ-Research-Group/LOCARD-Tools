package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class a implements Creator<PlaceReport> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
        String str2 = null;
        int i = 0;
        String str3 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 2:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 3:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 4:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, readInt);
                    break;
            }
        }
        com.google.android.gms.common.internal.safeparcel.a.B(parcel, a);
        return new PlaceReport(i, str2, str3, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PlaceReport[i];
    }
}
