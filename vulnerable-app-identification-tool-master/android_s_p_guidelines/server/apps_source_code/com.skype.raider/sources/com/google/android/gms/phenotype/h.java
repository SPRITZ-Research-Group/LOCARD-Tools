package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class h implements Creator<zzi> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int a = a.a(parcel);
        long j = 0;
        double d = 0.0d;
        int i2 = 0;
        String str = null;
        boolean z = false;
        String str2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str2 = a.p(parcel, readInt);
                    break;
                case 3:
                    j = a.h(parcel, readInt);
                    break;
                case 4:
                    z = a.c(parcel, readInt);
                    break;
                case 5:
                    d = a.m(parcel, readInt);
                    break;
                case 6:
                    str = a.p(parcel, readInt);
                    break;
                case 7:
                    bArr = a.s(parcel, readInt);
                    break;
                case 8:
                    i2 = a.f(parcel, readInt);
                    break;
                case 9:
                    i = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzi(str2, j, z, d, str, bArr, i2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzi[i];
    }
}
