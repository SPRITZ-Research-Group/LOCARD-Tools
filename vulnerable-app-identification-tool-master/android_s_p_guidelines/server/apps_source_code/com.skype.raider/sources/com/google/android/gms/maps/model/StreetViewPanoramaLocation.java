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

@Class(creator = "StreetViewPanoramaLocationCreator")
@Reserved({1})
public class StreetViewPanoramaLocation extends AbstractSafeParcelable {
    public static final Creator<StreetViewPanoramaLocation> CREATOR = new y();
    @Field(id = 2)
    public final StreetViewPanoramaLink[] a;
    @Field(id = 3)
    public final LatLng b;
    @Field(id = 4)
    public final String c;

    @Constructor
    public StreetViewPanoramaLocation(@Param(id = 2) StreetViewPanoramaLink[] streetViewPanoramaLinkArr, @Param(id = 3) LatLng latLng, @Param(id = 4) String str) {
        this.a = streetViewPanoramaLinkArr;
        this.b = latLng;
        this.c = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
        return this.c.equals(streetViewPanoramaLocation.c) && this.b.equals(streetViewPanoramaLocation.b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.c});
    }

    public String toString() {
        return z.a(this).a("panoId", this.c).a("position", this.b.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b, i);
        b.a(parcel, 4, this.c);
        b.a(parcel, a);
    }
}
