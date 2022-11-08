package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;

public final class n implements Creator<LocationSettingsRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzae zzae = null;
        boolean z = false;
        int a = a.a(parcel);
        boolean z2 = false;
        List list = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    list = a.c(parcel, readInt, LocationRequest.CREATOR);
                    break;
                case 2:
                    z2 = a.c(parcel, readInt);
                    break;
                case 3:
                    z = a.c(parcel, readInt);
                    break;
                case 5:
                    zzae = (zzae) a.a(parcel, readInt, zzae.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new LocationSettingsRequest(list, z2, z, zzae);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationSettingsRequest[i];
    }
}
