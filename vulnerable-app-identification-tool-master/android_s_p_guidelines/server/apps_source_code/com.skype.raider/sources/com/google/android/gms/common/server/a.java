package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class a implements Creator<FavaDiagnosticsEntity> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 2:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 3:
                    i = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, readInt);
                    break;
            }
        }
        com.google.android.gms.common.internal.safeparcel.a.B(parcel, a);
        return new FavaDiagnosticsEntity(i2, str, i);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new FavaDiagnosticsEntity[i];
    }
}
