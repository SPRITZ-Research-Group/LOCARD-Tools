package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.vision.barcode.Barcode.CalendarDateTime;

public final class d implements Creator<CalendarDateTime> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        int a = a.a(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i6 = a.f(parcel, readInt);
                    break;
                case 3:
                    i5 = a.f(parcel, readInt);
                    break;
                case 4:
                    i4 = a.f(parcel, readInt);
                    break;
                case 5:
                    i3 = a.f(parcel, readInt);
                    break;
                case 6:
                    i2 = a.f(parcel, readInt);
                    break;
                case 7:
                    i = a.f(parcel, readInt);
                    break;
                case 8:
                    z = a.c(parcel, readInt);
                    break;
                case 9:
                    str = a.p(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new CalendarDateTime(i6, i5, i4, i3, i2, i, z, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CalendarDateTime[i];
    }
}
