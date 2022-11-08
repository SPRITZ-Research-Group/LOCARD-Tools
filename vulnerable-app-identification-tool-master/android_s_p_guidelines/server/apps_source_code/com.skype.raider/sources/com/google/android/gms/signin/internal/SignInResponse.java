package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "SignInResponseCreator")
public class SignInResponse extends AbstractSafeParcelable {
    public static final Creator<SignInResponse> CREATOR = new i();
    @VersionField(id = 1)
    private final int a;
    @Field(getter = "getConnectionResult", id = 2)
    private final ConnectionResult b;
    @Field(getter = "getResolveAccountResponse", id = 3)
    private final ResolveAccountResponse c;

    public SignInResponse() {
        this(new ConnectionResult(8, null));
    }

    @Constructor
    SignInResponse(@Param(id = 1) int i, @Param(id = 2) ConnectionResult connectionResult, @Param(id = 3) ResolveAccountResponse resolveAccountResponse) {
        this.a = i;
        this.b = connectionResult;
        this.c = resolveAccountResponse;
    }

    private SignInResponse(ConnectionResult connectionResult) {
        this(1, connectionResult, null);
    }

    public final ConnectionResult a() {
        return this.b;
    }

    public final ResolveAccountResponse b() {
        return this.c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b, i);
        b.a(parcel, 3, this.c, i);
        b.a(parcel, a);
    }
}
