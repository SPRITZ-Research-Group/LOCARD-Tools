package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class a implements Creator<ConnectionEvent> {
    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ConnectionEvent[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
        int i = 0;
        long j = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 2:
                    j = com.google.android.gms.common.internal.safeparcel.a.h(parcel, readInt);
                    break;
                case 4:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 5:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 6:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 7:
                    str4 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 8:
                    str5 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 10:
                    j2 = com.google.android.gms.common.internal.safeparcel.a.h(parcel, readInt);
                    break;
                case 11:
                    j3 = com.google.android.gms.common.internal.safeparcel.a.h(parcel, readInt);
                    break;
                case 12:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 13:
                    str6 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, readInt);
                    break;
            }
        }
        com.google.android.gms.common.internal.safeparcel.a.B(parcel, a);
        return new ConnectionEvent(i, j, i2, str, str2, str3, str4, str5, str6, j2, j3);
    }
}
