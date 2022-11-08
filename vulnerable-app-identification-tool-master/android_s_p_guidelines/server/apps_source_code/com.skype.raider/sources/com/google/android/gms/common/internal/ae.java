package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ae implements Creator<SignInButtonConfig> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = a.a(parcel);
        Scope[] scopeArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i3 = a.f(parcel, readInt);
                    break;
                case 2:
                    i2 = a.f(parcel, readInt);
                    break;
                case 3:
                    i = a.f(parcel, readInt);
                    break;
                case 4:
                    scopeArr = (Scope[]) a.b(parcel, readInt, Scope.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new SignInButtonConfig(i3, i2, i, scopeArr);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SignInButtonConfig[i];
    }
}
