package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class x implements Creator<StreetViewPanoramaLink> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        String str = null;
        float f = 0.0f;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = a.p(parcel, readInt);
                    break;
                case 3:
                    f = a.k(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new StreetViewPanoramaLink(str, f);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new StreetViewPanoramaLink[i];
    }
}
