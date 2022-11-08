package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;

public final class b implements Creator<GoogleSignInOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        ArrayList arrayList = null;
        int a = a.a(parcel);
        String str = null;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        Account account = null;
        ArrayList arrayList2 = null;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    arrayList2 = a.c(parcel, readInt, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) a.a(parcel, readInt, Account.CREATOR);
                    break;
                case 4:
                    z3 = a.c(parcel, readInt);
                    break;
                case 5:
                    z2 = a.c(parcel, readInt);
                    break;
                case 6:
                    z = a.c(parcel, readInt);
                    break;
                case 7:
                    str2 = a.p(parcel, readInt);
                    break;
                case 8:
                    str = a.p(parcel, readInt);
                    break;
                case 9:
                    arrayList = a.c(parcel, readInt, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new GoogleSignInOptions(i, arrayList2, account, z3, z2, z, str2, str, arrayList);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
