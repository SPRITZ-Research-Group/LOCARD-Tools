package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Arrays;

@Class(creator = "LocationAvailabilityCreator")
@Reserved({1000})
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<LocationAvailability> CREATOR = new i();
    @Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 1)
    @Deprecated
    private int a;
    @Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 2)
    @Deprecated
    private int b;
    @Field(defaultValueUnchecked = "0", id = 3)
    private long c;
    @Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNSUCCESSFUL", id = 4)
    private int d;
    @Field(id = 5)
    private zzaj[] e;

    @Constructor
    LocationAvailability(@Param(id = 4) int i, @Param(id = 1) int i2, @Param(id = 2) int i3, @Param(id = 3) long j, @Param(id = 5) zzaj[] zzajArr) {
        this.d = i;
        this.a = i2;
        this.b = i3;
        this.c = j;
        this.e = zzajArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocationAvailability locationAvailability = (LocationAvailability) obj;
        return this.a == locationAvailability.a && this.b == locationAvailability.b && this.c == locationAvailability.c && this.d == locationAvailability.d && Arrays.equals(this.e, locationAvailability.e);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.d), Integer.valueOf(this.a), Integer.valueOf(this.b), Long.valueOf(this.c), this.e});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.e, i);
        b.a(parcel, a);
    }

    public final String toString() {
        return "LocationAvailability[isLocationAvailable: " + (this.d < Constants.ONE_SECOND) + "]";
    }
}
