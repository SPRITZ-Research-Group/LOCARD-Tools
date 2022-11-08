package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class p implements Creator<LatLng> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        double d = 0.0d;
        int a = a.a(parcel);
        double d2 = 0.0d;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    d2 = a.m(parcel, readInt);
                    break;
                case 3:
                    d = a.m(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new LatLng(d2, d);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LatLng[i];
    }
}
