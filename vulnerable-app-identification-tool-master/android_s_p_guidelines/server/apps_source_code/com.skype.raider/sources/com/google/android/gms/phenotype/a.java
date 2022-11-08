package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class a implements Creator<Configuration> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
        zzi[] zziArr = null;
        int i = 0;
        String[] strArr = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 3:
                    zziArr = (zzi[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, readInt, zzi.CREATOR);
                    break;
                case 4:
                    strArr = com.google.android.gms.common.internal.safeparcel.a.x(parcel, readInt);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, readInt);
                    break;
            }
        }
        com.google.android.gms.common.internal.safeparcel.a.B(parcel, a);
        return new Configuration(i, zziArr, strArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Configuration[i];
    }
}
