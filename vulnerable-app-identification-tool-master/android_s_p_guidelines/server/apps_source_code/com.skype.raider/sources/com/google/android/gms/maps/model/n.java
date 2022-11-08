package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class n implements Creator<GroundOverlayOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        IBinder iBinder = null;
        LatLng latLng = null;
        float f = 0.0f;
        float f2 = 0.0f;
        LatLngBounds latLngBounds = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        boolean z = false;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        boolean z2 = false;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    iBinder = a.q(parcel, readInt);
                    break;
                case 3:
                    latLng = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 4:
                    f = a.k(parcel, readInt);
                    break;
                case 5:
                    f2 = a.k(parcel, readInt);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) a.a(parcel, readInt, LatLngBounds.CREATOR);
                    break;
                case 7:
                    f3 = a.k(parcel, readInt);
                    break;
                case 8:
                    f4 = a.k(parcel, readInt);
                    break;
                case 9:
                    z = a.c(parcel, readInt);
                    break;
                case 10:
                    f5 = a.k(parcel, readInt);
                    break;
                case 11:
                    f6 = a.k(parcel, readInt);
                    break;
                case 12:
                    f7 = a.k(parcel, readInt);
                    break;
                case 13:
                    z2 = a.c(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new GroundOverlayOptions(iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7, z2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GroundOverlayOptions[i];
    }
}
