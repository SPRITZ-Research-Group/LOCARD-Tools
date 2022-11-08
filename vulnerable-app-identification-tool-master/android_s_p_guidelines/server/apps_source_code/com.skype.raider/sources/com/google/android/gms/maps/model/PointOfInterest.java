package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "PointOfInterestCreator")
@Reserved({1})
public final class PointOfInterest extends AbstractSafeParcelable {
    public static final Creator<PointOfInterest> CREATOR = new t();
    @Field(id = 2)
    public final LatLng a;
    @Field(id = 3)
    public final String b;
    @Field(id = 4)
    public final String c;

    @Constructor
    public PointOfInterest(@Param(id = 2) LatLng latLng, @Param(id = 3) String str, @Param(id = 4) String str2) {
        this.a = latLng;
        this.b = str;
        this.c = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, a);
    }
}
