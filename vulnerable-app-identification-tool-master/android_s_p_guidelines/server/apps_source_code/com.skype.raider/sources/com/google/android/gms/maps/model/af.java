package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class af implements Creator<VisibleRegion> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int a = a.a(parcel);
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    latLng4 = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    latLng3 = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 4:
                    latLng2 = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 5:
                    latLng = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) a.a(parcel, readInt, LatLngBounds.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new VisibleRegion(latLng4, latLng3, latLng2, latLng, latLngBounds);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new VisibleRegion[i];
    }
}
