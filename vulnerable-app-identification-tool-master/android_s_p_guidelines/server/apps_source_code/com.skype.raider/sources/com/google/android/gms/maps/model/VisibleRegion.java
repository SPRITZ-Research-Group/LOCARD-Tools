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
import com.google.android.gms.common.internal.z;
import java.util.Arrays;

@Class(creator = "VisibleRegionCreator")
@Reserved({1})
public final class VisibleRegion extends AbstractSafeParcelable {
    public static final Creator<VisibleRegion> CREATOR = new af();
    @Field(id = 2)
    public final LatLng a;
    @Field(id = 3)
    public final LatLng b;
    @Field(id = 4)
    public final LatLng c;
    @Field(id = 5)
    public final LatLng d;
    @Field(id = 6)
    public final LatLngBounds e;

    @Constructor
    public VisibleRegion(@Param(id = 2) LatLng latLng, @Param(id = 3) LatLng latLng2, @Param(id = 4) LatLng latLng3, @Param(id = 5) LatLng latLng4, @Param(id = 6) LatLngBounds latLngBounds) {
        this.a = latLng;
        this.b = latLng2;
        this.c = latLng3;
        this.d = latLng4;
        this.e = latLngBounds;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        return this.a.equals(visibleRegion.a) && this.b.equals(visibleRegion.b) && this.c.equals(visibleRegion.c) && this.d.equals(visibleRegion.d) && this.e.equals(visibleRegion.e);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.c, this.d, this.e});
    }

    public final String toString() {
        return z.a(this).a("nearLeft", this.a).a("nearRight", this.b).a("farLeft", this.c).a("farRight", this.d).a("latLngBounds", this.e).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b, i);
        b.a(parcel, 4, this.c, i);
        b.a(parcel, 5, this.d, i);
        b.a(parcel, 6, this.e, i);
        b.a(parcel, a);
    }
}
