package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class s implements Creator<zzdz> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        long j = 0;
        long j2 = 0;
        String str5 = null;
        boolean z = true;
        boolean z2 = false;
        long j3 = -2147483648L;
        String str6 = null;
        long j4 = 0;
        long j5 = 0;
        int i = 0;
        boolean z3 = true;
        boolean z4 = true;
        boolean z5 = false;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = a.p(parcel, readInt);
                    break;
                case 3:
                    str2 = a.p(parcel, readInt);
                    break;
                case 4:
                    str3 = a.p(parcel, readInt);
                    break;
                case 5:
                    str4 = a.p(parcel, readInt);
                    break;
                case 6:
                    j = a.h(parcel, readInt);
                    break;
                case 7:
                    j2 = a.h(parcel, readInt);
                    break;
                case 8:
                    str5 = a.p(parcel, readInt);
                    break;
                case 9:
                    z = a.c(parcel, readInt);
                    break;
                case 10:
                    z2 = a.c(parcel, readInt);
                    break;
                case 11:
                    j3 = a.h(parcel, readInt);
                    break;
                case 12:
                    str6 = a.p(parcel, readInt);
                    break;
                case 13:
                    j4 = a.h(parcel, readInt);
                    break;
                case 14:
                    j5 = a.h(parcel, readInt);
                    break;
                case 15:
                    i = a.f(parcel, readInt);
                    break;
                case 16:
                    z3 = a.c(parcel, readInt);
                    break;
                case 17:
                    z4 = a.c(parcel, readInt);
                    break;
                case 18:
                    z5 = a.c(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzdz(str, str2, str3, str4, j, j2, str5, z, z2, j3, str6, j4, j5, i, z3, z4, z5);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdz[i];
    }
}
