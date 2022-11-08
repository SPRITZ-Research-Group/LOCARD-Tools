package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "AuthAccountResultCreator")
public class AuthAccountResult extends AbstractSafeParcelable implements i {
    public static final Creator<AuthAccountResult> CREATOR = new a();
    @VersionField(id = 1)
    private final int a;
    @Field(getter = "getConnectionResultCode", id = 2)
    private int b;
    @Field(getter = "getRawAuthResolutionIntent", id = 3)
    private Intent c;

    public AuthAccountResult() {
        this((byte) 0);
    }

    private AuthAccountResult(byte b) {
        this(2, 0, null);
    }

    @Constructor
    AuthAccountResult(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) Intent intent) {
        this.a = i;
        this.b = i2;
        this.c = intent;
    }

    public final Status a() {
        return this.b == 0 ? Status.a : Status.e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c, i);
        b.a(parcel, a);
    }
}
