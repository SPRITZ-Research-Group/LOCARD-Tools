package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class a implements Creator<AuthAccountResult> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 2:
                    i = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 3:
                    intent = (Intent) com.google.android.gms.common.internal.safeparcel.a.a(parcel, readInt, Intent.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, readInt);
                    break;
            }
        }
        com.google.android.gms.common.internal.safeparcel.a.B(parcel, a);
        return new AuthAccountResult(i2, i, intent);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new AuthAccountResult[i];
    }
}
