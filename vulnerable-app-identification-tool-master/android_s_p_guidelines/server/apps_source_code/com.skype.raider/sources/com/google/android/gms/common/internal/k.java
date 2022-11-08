package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.a;

public final class k implements Creator<GetServiceRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        Feature[] featureArr = null;
        int a = a.a(parcel);
        Feature[] featureArr2 = null;
        Account account = null;
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i = 0;
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
                    str = a.p(parcel, readInt);
                    break;
                case 5:
                    iBinder = a.q(parcel, readInt);
                    break;
                case 6:
                    scopeArr = (Scope[]) a.b(parcel, readInt, Scope.CREATOR);
                    break;
                case 7:
                    bundle = a.r(parcel, readInt);
                    break;
                case 8:
                    account = (Account) a.a(parcel, readInt, Account.CREATOR);
                    break;
                case 10:
                    featureArr2 = (Feature[]) a.b(parcel, readInt, Feature.CREATOR);
                    break;
                case 11:
                    featureArr = (Feature[]) a.b(parcel, readInt, Feature.CREATOR);
                    break;
                case 12:
                    z = a.c(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new GetServiceRequest(i3, i2, i, str, iBinder, scopeArr, bundle, account, featureArr2, featureArr, z);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
