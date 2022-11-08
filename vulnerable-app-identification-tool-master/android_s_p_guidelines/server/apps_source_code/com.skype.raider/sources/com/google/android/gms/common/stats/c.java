package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;

public final class c implements Creator<WakeLockEvent> {
    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new WakeLockEvent[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        int i = 0;
        long j = 0;
        int i2 = 0;
        String str = null;
        int i3 = 0;
        List list = null;
        String str2 = null;
        long j2 = 0;
        int i4 = 0;
        String str3 = null;
        String str4 = null;
        float f = 0.0f;
        long j3 = 0;
        String str5 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    j = a.h(parcel, readInt);
                    break;
                case 4:
                    str = a.p(parcel, readInt);
                    break;
                case 5:
                    i3 = a.f(parcel, readInt);
                    break;
                case 6:
                    list = a.y(parcel, readInt);
                    break;
                case 8:
                    j2 = a.h(parcel, readInt);
                    break;
                case 10:
                    str3 = a.p(parcel, readInt);
                    break;
                case 11:
                    i2 = a.f(parcel, readInt);
                    break;
                case 12:
                    str2 = a.p(parcel, readInt);
                    break;
                case 13:
                    str4 = a.p(parcel, readInt);
                    break;
                case 14:
                    i4 = a.f(parcel, readInt);
                    break;
                case 15:
                    f = a.k(parcel, readInt);
                    break;
                case 16:
                    j3 = a.h(parcel, readInt);
                    break;
                case 17:
                    str5 = a.p(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new WakeLockEvent(i, j, i2, str, i3, list, str2, j2, i4, str3, str4, f, j3, str5);
    }
}
