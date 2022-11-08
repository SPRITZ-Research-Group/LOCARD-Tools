package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class r implements Creator<MarkerOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        float f = 0.0f;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.5f;
        float f5 = 0.0f;
        float f6 = 1.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    latLng = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    str = a.p(parcel, readInt);
                    break;
                case 4:
                    str2 = a.p(parcel, readInt);
                    break;
                case 5:
                    iBinder = a.q(parcel, readInt);
                    break;
                case 6:
                    f = a.k(parcel, readInt);
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
                    f3 = a.k(parcel, readInt);
                    break;
                case 12:
                    f4 = a.k(parcel, readInt);
                    break;
                case 13:
                    f5 = a.k(parcel, readInt);
                    break;
                case 14:
                    f6 = a.k(parcel, readInt);
                    break;
                case 15:
                    f7 = a.k(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new MarkerOptions(latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6, f7);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MarkerOptions[i];
    }
}
