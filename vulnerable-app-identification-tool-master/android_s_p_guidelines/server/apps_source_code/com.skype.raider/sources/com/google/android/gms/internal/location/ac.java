package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ac implements Creator<zzbh> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        String str = null;
        int i = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i2 = 0;
        int i3 = -1;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = a.p(parcel, readInt);
                    break;
                case 2:
                    j = a.h(parcel, readInt);
                    break;
                case 3:
                    s = a.e(parcel, readInt);
                    break;
                case 4:
                    d = a.m(parcel, readInt);
                    break;
                case 5:
                    d2 = a.m(parcel, readInt);
                    break;
                case 6:
                    f = a.k(parcel, readInt);
                    break;
                case 7:
                    i = a.f(parcel, readInt);
                    break;
                case 8:
                    i2 = a.f(parcel, readInt);
                    break;
                case 9:
                    i3 = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzbh(str, i, s, d, d2, f, j, i2, i3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbh[i];
    }
}
