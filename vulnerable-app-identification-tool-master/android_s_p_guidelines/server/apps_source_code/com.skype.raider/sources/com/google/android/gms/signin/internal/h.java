package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.a;

public final class h implements Creator<SignInRequest> {
    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SignInRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        int i = 0;
        ResolveAccountRequest resolveAccountRequest = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    resolveAccountRequest = (ResolveAccountRequest) a.a(parcel, readInt, ResolveAccountRequest.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new SignInRequest(i, resolveAccountRequest);
    }
}
