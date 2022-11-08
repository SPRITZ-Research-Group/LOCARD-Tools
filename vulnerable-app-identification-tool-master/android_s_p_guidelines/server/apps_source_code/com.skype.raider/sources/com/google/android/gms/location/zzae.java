package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "LocationSettingsConfigurationCreator")
@Reserved({3, 4, 1000})
public final class zzae extends AbstractSafeParcelable {
    public static final Creator<zzae> CREATOR = new m();
    @Field(defaultValue = "", getter = "getJustificationText", id = 1)
    private final String a;
    @Field(defaultValue = "", getter = "getExperimentId", id = 2)
    private final String b;
    @Field(defaultValue = "", getter = "getTitleText", id = 5)
    private final String c;

    @Constructor
    zzae(@Param(id = 5) String str, @Param(id = 1) String str2, @Param(id = 2) String str3) {
        this.c = str;
        this.a = str2;
        this.b = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 5, this.c);
        b.a(parcel, a);
    }
}
