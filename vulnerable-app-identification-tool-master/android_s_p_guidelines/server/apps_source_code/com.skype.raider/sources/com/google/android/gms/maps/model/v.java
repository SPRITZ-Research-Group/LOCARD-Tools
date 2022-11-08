package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;

public final class v implements Creator<PolylineOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        float f = 0.0f;
        List list = null;
        int i = 0;
        int a = a.a(parcel);
        Cap cap = null;
        Cap cap2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        float f2 = 0.0f;
        List list2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    list2 = a.c(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    f2 = a.k(parcel, readInt);
                    break;
                case 4:
                    i2 = a.f(parcel, readInt);
                    break;
                case 5:
                    f = a.k(parcel, readInt);
                    break;
                case 6:
                    z3 = a.c(parcel, readInt);
                    break;
                case 7:
                    z2 = a.c(parcel, readInt);
                    break;
                case 8:
                    z = a.c(parcel, readInt);
                    break;
                case 9:
                    cap2 = (Cap) a.a(parcel, readInt, Cap.CREATOR);
                    break;
                case 10:
                    cap = (Cap) a.a(parcel, readInt, Cap.CREATOR);
                    break;
                case 11:
                    i = a.f(parcel, readInt);
                    break;
                case 12:
                    list = a.c(parcel, readInt, PatternItem.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new PolylineOptions(list2, f2, i2, f, z3, z2, z, cap2, cap, i, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PolylineOptions[i];
    }
}
