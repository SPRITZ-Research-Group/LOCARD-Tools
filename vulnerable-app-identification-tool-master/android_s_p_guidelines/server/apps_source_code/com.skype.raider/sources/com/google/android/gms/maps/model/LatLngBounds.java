package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
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
import java.util.Arrays;

@Class(creator = "LatLngBoundsCreator")
@Reserved({1})
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Creator<LatLngBounds> CREATOR = new o();
    @Field(id = 2)
    public final LatLng a;
    @Field(id = 3)
    public final LatLng b;

    public static final class a {
        private double a = Double.POSITIVE_INFINITY;
        private double b = Double.NEGATIVE_INFINITY;
        private double c = Double.NaN;
        private double d = Double.NaN;

        public final a a(LatLng latLng) {
            Object obj = 1;
            this.a = Math.min(this.a, latLng.a);
            this.b = Math.max(this.b, latLng.a);
            double d = latLng.b;
            if (Double.isNaN(this.c)) {
                this.c = d;
            } else {
                if (this.c <= this.d) {
                    if (this.c > d || d > this.d) {
                        obj = null;
                    }
                } else if (this.c > d && d > this.d) {
                    obj = null;
                }
                if (obj == null) {
                    if ((((this.c - d) + 360.0d) % 360.0d) < (((d - this.d) + 360.0d) % 360.0d)) {
                        this.c = d;
                    }
                }
                return this;
            }
            this.d = d;
            return this;
        }

        public final LatLngBounds a() {
            ab.a(!Double.isNaN(this.c), (Object) "no included points");
            return new LatLngBounds(new LatLng(this.a, this.c), new LatLng(this.b, this.d));
        }
    }

    @Constructor
    public LatLngBounds(@Param(id = 2) LatLng latLng, @Param(id = 3) LatLng latLng2) {
        ab.a((Object) latLng, (Object) "null southwest");
        ab.a((Object) latLng2, (Object) "null northeast");
        ab.a(latLng2.a >= latLng.a, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.a), Double.valueOf(latLng2.a));
        this.a = latLng;
        this.b = latLng2;
    }

    public final LatLng a() {
        double d = (this.a.a + this.b.a) / 2.0d;
        double d2 = this.b.b;
        double d3 = this.a.b;
        return new LatLng(d, d3 <= d2 ? (d2 + d3) / 2.0d : ((d2 + 360.0d) + d3) / 2.0d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.a.equals(latLngBounds.a) && this.b.equals(latLngBounds.b);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b});
    }

    public final String toString() {
        return z.a(this).a("southwest", this.a).a("northeast", this.b).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b, i);
        b.a(parcel, a);
    }
}
