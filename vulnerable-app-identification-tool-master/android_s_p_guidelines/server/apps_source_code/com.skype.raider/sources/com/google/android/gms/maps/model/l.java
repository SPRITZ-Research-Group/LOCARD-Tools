package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class l implements Creator<Cap> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Float f = null;
        int a = a.a(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    iBinder = a.q(parcel, readInt);
                    break;
                case 4:
                    f = a.l(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new Cap(i, iBinder, f);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Cap[i];
    }
}
