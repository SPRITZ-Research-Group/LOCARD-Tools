package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;

public final class m implements Creator<CircleOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list = null;
        float f = 0.0f;
        boolean z = false;
        int a = a.a(parcel);
        double d = 0.0d;
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        LatLng latLng = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    latLng = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    d = a.m(parcel, readInt);
                    break;
                case 4:
                    f2 = a.k(parcel, readInt);
                    break;
                case 5:
                    i2 = a.f(parcel, readInt);
                    break;
                case 6:
                    i = a.f(parcel, readInt);
                    break;
                case 7:
                    f = a.k(parcel, readInt);
                    break;
                case 8:
                    z2 = a.c(parcel, readInt);
                    break;
                case 9:
                    z = a.c(parcel, readInt);
                    break;
                case 10:
                    list = a.c(parcel, readInt, PatternItem.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new CircleOptions(latLng, d, f2, i2, i, f, z2, z, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CircleOptions[i];
    }
}
