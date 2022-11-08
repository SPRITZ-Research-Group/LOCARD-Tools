package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "RecordConsentRequestCreator")
public class RecordConsentRequest extends AbstractSafeParcelable {
    public static final Creator<RecordConsentRequest> CREATOR = new f();
    @VersionField(id = 1)
    private final int a;
    @Field(getter = "getAccount", id = 2)
    private final Account b;
    @Field(getter = "getScopesToConsent", id = 3)
    private final Scope[] c;
    @Field(getter = "getServerClientId", id = 4)
    private final String d;

    @Constructor
    RecordConsentRequest(@Param(id = 1) int i, @Param(id = 2) Account account, @Param(id = 3) Scope[] scopeArr, @Param(id = 4) String str) {
        this.a = i;
        this.b = account;
        this.c = scopeArr;
        this.d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b, i);
        b.a(parcel, 3, this.c, i);
        b.a(parcel, 4, this.d);
        b.a(parcel, a);
    }
}
