package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class m implements Creator<zzn> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = a.a(parcel);
        float f = 0.0f;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i4 = a.f(parcel, readInt);
                    break;
                case 3:
                    i3 = a.f(parcel, readInt);
                    break;
                case 4:
                    i2 = a.f(parcel, readInt);
                    break;
                case 5:
                    i = a.f(parcel, readInt);
                    break;
                case 6:
                    f = a.k(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzn(i4, i3, i2, i, f);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzn[i];
    }
}
