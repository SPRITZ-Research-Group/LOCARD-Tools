package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.vision.barcode.Barcode.Email;

public final class h implements Creator<Email> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = a.a(parcel);
        String str2 = null;
        int i = 0;
        String str3 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    str2 = a.p(parcel, readInt);
                    break;
                case 4:
                    str3 = a.p(parcel, readInt);
                    break;
                case 5:
                    str = a.p(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new Email(i, str2, str3, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Email[i];
    }
}
