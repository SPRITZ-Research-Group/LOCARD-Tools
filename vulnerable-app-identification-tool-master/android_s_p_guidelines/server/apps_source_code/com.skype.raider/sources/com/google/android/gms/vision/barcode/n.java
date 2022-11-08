package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.vision.barcode.Barcode.WiFi;

public final class n implements Creator<WiFi> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = a.a(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str2 = a.p(parcel, readInt);
                    break;
                case 3:
                    str = a.p(parcel, readInt);
                    break;
                case 4:
                    i = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new WiFi(str2, str, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new WiFi[i];
    }
}
