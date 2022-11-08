package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class g implements Creator<GoogleCertificatesQuery> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int a = a.a(parcel);
        boolean z = false;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = a.p(parcel, readInt);
                    break;
                case 2:
                    iBinder = a.q(parcel, readInt);
                    break;
                case 3:
                    z = a.c(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new GoogleCertificatesQuery(str, iBinder, z);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new GoogleCertificatesQuery[i];
    }
}
