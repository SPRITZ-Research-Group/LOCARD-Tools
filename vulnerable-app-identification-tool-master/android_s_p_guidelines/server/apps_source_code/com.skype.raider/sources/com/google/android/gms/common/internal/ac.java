package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ac implements Creator<ResolveAccountRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        int i = 0;
        Account account = null;
        int i2 = 0;
        GoogleSignInAccount googleSignInAccount = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = a.f(parcel, readInt);
                    break;
                case 2:
                    account = (Account) a.a(parcel, readInt, Account.CREATOR);
                    break;
                case 3:
                    i = a.f(parcel, readInt);
                    break;
                case 4:
                    googleSignInAccount = (GoogleSignInAccount) a.a(parcel, readInt, GoogleSignInAccount.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ResolveAccountRequest(i2, account, i, googleSignInAccount);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ResolveAccountRequest[i];
    }
}
