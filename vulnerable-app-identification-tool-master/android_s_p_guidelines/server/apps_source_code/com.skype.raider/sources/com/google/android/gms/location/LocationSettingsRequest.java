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
import java.util.Collections;
import java.util.List;

@Class(creator = "LocationSettingsRequestCreator")
@Reserved({1000})
public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public static final Creator<LocationSettingsRequest> CREATOR = new n();
    @Field(getter = "getLocationRequests", id = 1)
    private final List<LocationRequest> a;
    @Field(defaultValue = "false", getter = "alwaysShow", id = 2)
    private final boolean b;
    @Field(getter = "needBle", id = 3)
    private final boolean c;
    @Field(getter = "getConfiguration", id = 5)
    private zzae d;

    @Constructor
    LocationSettingsRequest(@Param(id = 1) List<LocationRequest> list, @Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 5) zzae zzae) {
        this.a = list;
        this.b = z;
        this.c = z2;
        this.d = zzae;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.b(parcel, 1, Collections.unmodifiableList(this.a));
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, 5, this.d, i);
        b.a(parcel, a);
    }
}
