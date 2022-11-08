package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "LocationSettingsResultCreator")
@Reserved({1000})
public final class LocationSettingsResult extends AbstractSafeParcelable implements i {
    public static final Creator<LocationSettingsResult> CREATOR = new o();
    @Field(getter = "getStatus", id = 1)
    private final Status a;
    @Field(getter = "getLocationSettingsStates", id = 2)
    private final LocationSettingsStates b;

    @Constructor
    public LocationSettingsResult(@Param(id = 1) Status status, @Param(id = 2) LocationSettingsStates locationSettingsStates) {
        this.a = status;
        this.b = locationSettingsStates;
    }

    public final Status a() {
        return this.a;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a, i);
        b.a(parcel, 2, this.b, i);
        b.a(parcel, a);
    }
}
