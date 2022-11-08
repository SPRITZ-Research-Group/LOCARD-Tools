package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

public final class m implements Creator<StreetViewPanoramaOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        StreetViewSource streetViewSource = null;
        byte b = (byte) 0;
        int a = a.a(parcel);
        byte b2 = (byte) 0;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        Integer num = null;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) a.a(parcel, readInt, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    str = a.p(parcel, readInt);
                    break;
                case 4:
                    latLng = (LatLng) a.a(parcel, readInt, LatLng.CREATOR);
                    break;
                case 5:
                    num = a.g(parcel, readInt);
                    break;
                case 6:
                    b5 = a.d(parcel, readInt);
                    break;
                case 7:
                    b4 = a.d(parcel, readInt);
                    break;
                case 8:
                    b3 = a.d(parcel, readInt);
                    break;
                case 9:
                    b2 = a.d(parcel, readInt);
                    break;
                case 10:
                    b = a.d(parcel, readInt);
                    break;
                case 11:
                    streetViewSource = (StreetViewSource) a.a(parcel, readInt, StreetViewSource.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new StreetViewPanoramaOptions(streetViewPanoramaCamera, str, latLng, num, b5, b4, b3, b2, b, streetViewSource);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new StreetViewPanoramaOptions[i];
    }
}
