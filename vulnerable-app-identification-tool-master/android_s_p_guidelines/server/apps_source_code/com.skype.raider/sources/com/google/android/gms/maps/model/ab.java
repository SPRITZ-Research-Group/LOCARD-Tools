package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ab implements Creator<Tile> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = a.a(parcel);
        byte[] bArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i2 = a.f(parcel, readInt);
                    break;
                case 3:
                    i = a.f(parcel, readInt);
                    break;
                case 4:
                    bArr = a.s(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new Tile(i2, i, bArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Tile[i];
    }
}
