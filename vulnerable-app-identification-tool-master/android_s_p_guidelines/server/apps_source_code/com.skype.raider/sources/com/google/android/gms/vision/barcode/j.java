package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.vision.barcode.Barcode.PersonName;

public final class j implements Creator<PersonName> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = a.a(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str7 = a.p(parcel, readInt);
                    break;
                case 3:
                    str6 = a.p(parcel, readInt);
                    break;
                case 4:
                    str5 = a.p(parcel, readInt);
                    break;
                case 5:
                    str4 = a.p(parcel, readInt);
                    break;
                case 6:
                    str3 = a.p(parcel, readInt);
                    break;
                case 7:
                    str2 = a.p(parcel, readInt);
                    break;
                case 8:
                    str = a.p(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new PersonName(str7, str6, str5, str4, str3, str2, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PersonName[i];
    }
}
