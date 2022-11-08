package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public final class aa implements Creator<zzbd> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int a = a.a(parcel);
        List list = zzbd.a;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        LocationRequest locationRequest = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    locationRequest = (LocationRequest) a.a(parcel, readInt, LocationRequest.CREATOR);
                    break;
                case 5:
                    list = a.c(parcel, readInt, ClientIdentity.CREATOR);
                    break;
                case 6:
                    str2 = a.p(parcel, readInt);
                    break;
                case 7:
                    z3 = a.c(parcel, readInt);
                    break;
                case 8:
                    z2 = a.c(parcel, readInt);
                    break;
                case 9:
                    z = a.c(parcel, readInt);
                    break;
                case 10:
                    str = a.p(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzbd(locationRequest, list, str2, z3, z2, z, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbd[i];
    }
}
