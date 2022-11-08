package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "ConnectionInfoCreator")
public class ConnectionInfo extends AbstractSafeParcelable {
    public static final Creator<ConnectionInfo> CREATOR = new i();
    @Field(id = 1)
    private Bundle a;
    @Field(id = 2)
    private Feature[] b;

    @Constructor
    ConnectionInfo(@Param(id = 1) Bundle bundle, @Param(id = 2) Feature[] featureArr) {
        this.a = bundle;
        this.b = featureArr;
    }

    public final Bundle a() {
        return this.a;
    }

    public final Feature[] b() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b, i);
        b.a(parcel, a);
    }
}
