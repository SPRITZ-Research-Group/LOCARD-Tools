package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.a;

public final class d implements Creator<AuthAccountRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Account account = null;
        int a = a.a(parcel);
        int i = 0;
        Integer num = null;
        Integer num2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    iBinder = a.q(parcel, readInt);
                    break;
                case 3:
                    scopeArr = (Scope[]) a.b(parcel, readInt, Scope.CREATOR);
                    break;
                case 4:
                    num2 = a.g(parcel, readInt);
                    break;
                case 5:
                    num = a.g(parcel, readInt);
                    break;
                case 6:
                    account = (Account) a.a(parcel, readInt, Account.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new AuthAccountRequest(i, iBinder, scopeArr, num2, num, account);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new AuthAccountRequest[i];
    }
}
