package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;

public final class c implements Creator<CheckServerAuthResult> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        int a = a.a(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    z = a.c(parcel, readInt);
                    break;
                case 3:
                    list = a.c(parcel, readInt, Scope.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new CheckServerAuthResult(i, z, list);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new CheckServerAuthResult[i];
    }
}
