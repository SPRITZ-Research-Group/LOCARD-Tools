package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;
import java.util.List;

public final class u implements Creator<PolygonOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        List list = null;
        List arrayList = new ArrayList();
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        List list2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    list = a.c(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    ClassLoader classLoader = getClass().getClassLoader();
                    readInt = a.a(parcel, readInt);
                    int dataPosition = parcel.dataPosition();
                    if (readInt == 0) {
                        break;
                    }
                    parcel.readList(arrayList, classLoader);
                    parcel.setDataPosition(readInt + dataPosition);
                    break;
                case 4:
                    f = a.k(parcel, readInt);
                    break;
                case 5:
                    i = a.f(parcel, readInt);
                    break;
                case 6:
                    i2 = a.f(parcel, readInt);
                    break;
                case 7:
                    f2 = a.k(parcel, readInt);
                    break;
                case 8:
                    z = a.c(parcel, readInt);
                    break;
                case 9:
                    z2 = a.c(parcel, readInt);
                    break;
                case 10:
                    z3 = a.c(parcel, readInt);
                    break;
                case 11:
                    i3 = a.f(parcel, readInt);
                    break;
                case 12:
                    list2 = a.c(parcel, readInt, PatternItem.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new PolygonOptions(list, arrayList, f, i, i2, f2, z, z2, z3, i3, list2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PolygonOptions[i];
    }
}
