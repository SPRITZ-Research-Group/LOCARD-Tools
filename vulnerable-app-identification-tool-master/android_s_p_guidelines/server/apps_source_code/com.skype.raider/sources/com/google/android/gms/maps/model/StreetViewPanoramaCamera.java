package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation.a;
import java.util.Arrays;

@Class(creator = "StreetViewPanoramaCameraCreator")
@Reserved({1})
public class StreetViewPanoramaCamera extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<StreetViewPanoramaCamera> CREATOR = new w();
    @Field(id = 2)
    public final float a;
    @Field(id = 3)
    public final float b;
    @Field(id = 4)
    public final float c;
    @NonNull
    private final StreetViewPanoramaOrientation d;

    @Constructor
    public StreetViewPanoramaCamera(@Param(id = 2) float f, @Param(id = 3) float f2, @Param(id = 4) float f3) {
        boolean z = -90.0f <= f2 && f2 <= 90.0f;
        ab.b(z, "Tilt needs to be between -90 and 90 inclusive: " + f2);
        if (((double) f) <= 0.0d) {
            f = 0.0f;
        }
        this.a = f;
        this.b = f2 + 0.0f;
        this.c = (((double) f3) <= 0.0d ? (f3 % 360.0f) + 360.0f : f3) % 360.0f;
        a aVar = new a();
        aVar.b = f2;
        aVar.a = f3;
        this.d = new StreetViewPanoramaOrientation(aVar.b, aVar.a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaCamera)) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) obj;
        return Float.floatToIntBits(this.a) == Float.floatToIntBits(streetViewPanoramaCamera.a) && Float.floatToIntBits(this.b) == Float.floatToIntBits(streetViewPanoramaCamera.b) && Float.floatToIntBits(this.c) == Float.floatToIntBits(streetViewPanoramaCamera.c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.a), Float.valueOf(this.b), Float.valueOf(this.c)});
    }

    public String toString() {
        return z.a(this).a("zoom", Float.valueOf(this.a)).a("tilt", Float.valueOf(this.b)).a("bearing", Float.valueOf(this.c)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, a);
    }
}
