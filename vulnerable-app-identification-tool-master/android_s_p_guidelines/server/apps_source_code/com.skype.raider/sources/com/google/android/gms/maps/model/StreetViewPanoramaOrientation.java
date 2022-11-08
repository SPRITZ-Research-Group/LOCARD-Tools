package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import java.util.Arrays;

@Class(creator = "StreetViewPanoramaOrientationCreator")
@Reserved({1})
public class StreetViewPanoramaOrientation extends AbstractSafeParcelable {
    public static final Creator<StreetViewPanoramaOrientation> CREATOR = new z();
    @Field(id = 2)
    public final float a;
    @Field(id = 3)
    public final float b;

    public static final class a {
        public float a;
        public float b;
    }

    @Constructor
    public StreetViewPanoramaOrientation(@Param(id = 2) float f, @Param(id = 3) float f2) {
        boolean z = -90.0f <= f && f <= 90.0f;
        ab.b(z, "Tilt needs to be between -90 and 90 inclusive: " + f);
        this.a = 0.0f + f;
        if (((double) f2) <= 0.0d) {
            f2 = (f2 % 360.0f) + 360.0f;
        }
        this.b = f2 % 360.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaOrientation)) {
            return false;
        }
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) obj;
        return Float.floatToIntBits(this.a) == Float.floatToIntBits(streetViewPanoramaOrientation.a) && Float.floatToIntBits(this.b) == Float.floatToIntBits(streetViewPanoramaOrientation.b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.a), Float.valueOf(this.b)});
    }

    public String toString() {
        return z.a(this).a("tilt", Float.valueOf(this.a)).a("bearing", Float.valueOf(this.b)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, a);
    }
}
