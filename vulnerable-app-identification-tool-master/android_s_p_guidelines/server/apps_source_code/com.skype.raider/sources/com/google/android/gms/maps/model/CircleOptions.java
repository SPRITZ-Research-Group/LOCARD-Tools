package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.List;

@Class(creator = "CircleOptionsCreator")
@Reserved({1})
public final class CircleOptions extends AbstractSafeParcelable {
    public static final Creator<CircleOptions> CREATOR = new m();
    @Field(getter = "getCenter", id = 2)
    private LatLng a = null;
    @Field(getter = "getRadius", id = 3)
    private double b = 0.0d;
    @Field(getter = "getStrokeWidth", id = 4)
    private float c = 10.0f;
    @Field(getter = "getStrokeColor", id = 5)
    private int d = -16777216;
    @Field(getter = "getFillColor", id = 6)
    private int e = 0;
    @Field(getter = "getZIndex", id = 7)
    private float f = 0.0f;
    @Field(getter = "isVisible", id = 8)
    private boolean g = true;
    @Field(getter = "isClickable", id = 9)
    private boolean h = false;
    @Nullable
    @Field(getter = "getStrokePattern", id = 10)
    private List<PatternItem> i = null;

    @Constructor
    CircleOptions(@Param(id = 2) LatLng latLng, @Param(id = 3) double d, @Param(id = 4) float f, @Param(id = 5) int i, @Param(id = 6) int i2, @Param(id = 7) float f2, @Param(id = 8) boolean z, @Param(id = 9) boolean z2, @Nullable @Param(id = 10) List<PatternItem> list) {
        this.a = latLng;
        this.b = d;
        this.c = f;
        this.d = i;
        this.e = i2;
        this.f = f2;
        this.g = z;
        this.h = z2;
        this.i = list;
    }

    public final CircleOptions a(double d) {
        this.b = d;
        return this;
    }

    public final CircleOptions a(float f) {
        this.c = f;
        return this;
    }

    public final CircleOptions a(int i) {
        this.d = i;
        return this;
    }

    public final CircleOptions a(LatLng latLng) {
        this.a = latLng;
        return this;
    }

    public final CircleOptions b(float f) {
        this.f = f;
        return this;
    }

    public final CircleOptions b(int i) {
        this.e = i;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e);
        b.a(parcel, 7, this.f);
        b.a(parcel, 8, this.g);
        b.a(parcel, 9, this.h);
        b.b(parcel, 10, this.i);
        b.a(parcel, a);
    }
}
