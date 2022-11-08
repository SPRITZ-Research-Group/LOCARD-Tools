package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import java.util.List;

public final class a implements Creator<GoogleSignInAccount> {
    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        long j = 0;
        String str6 = null;
        List list = null;
        String str7 = null;
        String str8 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.a.f(parcel, readInt);
                    break;
                case 2:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 3:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 4:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 5:
                    str4 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 6:
                    uri = (Uri) com.google.android.gms.common.internal.safeparcel.a.a(parcel, readInt, Uri.CREATOR);
                    break;
                case 7:
                    str5 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 8:
                    j = com.google.android.gms.common.internal.safeparcel.a.h(parcel, readInt);
                    break;
                case 9:
                    str6 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 10:
                    list = com.google.android.gms.common.internal.safeparcel.a.c(parcel, readInt, Scope.CREATOR);
                    break;
                case 11:
                    str7 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                case 12:
                    str8 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, readInt);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, readInt);
                    break;
            }
        }
        com.google.android.gms.common.internal.safeparcel.a.B(parcel, a);
        return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, list, str7, str8);
    }
}
