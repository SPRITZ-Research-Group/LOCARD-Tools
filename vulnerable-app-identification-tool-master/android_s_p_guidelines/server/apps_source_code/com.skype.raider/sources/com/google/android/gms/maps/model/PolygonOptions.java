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
import java.util.ArrayList;
import java.util.List;

@Class(creator = "PolygonOptionsCreator")
@Reserved({1})
public final class PolygonOptions extends AbstractSafeParcelable {
    public static final Creator<PolygonOptions> CREATOR = new u();
    @Field(getter = "getPoints", id = 2)
    private final List<LatLng> a;
    @Field(getter = "getHolesForParcel", id = 3, type = "java.util.List")
    private final List<List<LatLng>> b;
    @Field(getter = "getStrokeWidth", id = 4)
    private float c;
    @Field(getter = "getStrokeColor", id = 5)
    private int d;
    @Field(getter = "getFillColor", id = 6)
    private int e;
    @Field(getter = "getZIndex", id = 7)
    private float f;
    @Field(getter = "isVisible", id = 8)
    private boolean g;
    @Field(getter = "isGeodesic", id = 9)
    private boolean h;
    @Field(getter = "isClickable", id = 10)
    private boolean i;
    @Field(getter = "getStrokeJointType", id = 11)
    private int j;
    @Nullable
    @Field(getter = "getStrokePattern", id = 12)
    private List<PatternItem> k;

    public PolygonOptions() {
        this.c = 10.0f;
        this.d = -16777216;
        this.e = 0;
        this.f = 0.0f;
        this.g = true;
        this.h = false;
        this.i = false;
        this.j = 0;
        this.k = null;
        this.a = new ArrayList();
        this.b = new ArrayList();
    }

    @Constructor
    PolygonOptions(@Param(id = 2) List<LatLng> list, @Param(id = 3) List list2, @Param(id = 4) float f, @Param(id = 5) int i, @Param(id = 6) int i2, @Param(id = 7) float f2, @Param(id = 8) boolean z, @Param(id = 9) boolean z2, @Param(id = 10) boolean z3, @Param(id = 11) int i3, @Nullable @Param(id = 12) List<PatternItem> list3) {
        this.c = 10.0f;
        this.d = -16777216;
        this.e = 0;
        this.f = 0.0f;
        this.g = true;
        this.h = false;
        this.i = false;
        this.j = 0;
        this.k = null;
        this.a = list;
        this.b = list2;
        this.c = f;
        this.d = i;
        this.e = i2;
        this.f = f2;
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.j = i3;
        this.k = list3;
    }

    public final PolygonOptions a(float f) {
        this.c = f;
        return this;
    }

    public final PolygonOptions a(int i) {
        this.d = i;
        return this;
    }

    public final PolygonOptions a(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.a.add(add);
        }
        return this;
    }

    public final PolygonOptions a(boolean z) {
        this.h = z;
        return this;
    }

    public final PolygonOptions b(float f) {
        this.f = f;
        return this;
    }

    public final PolygonOptions b(int i) {
        this.e = i;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.b(parcel, 2, this.a);
        b.a(parcel, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e);
        b.a(parcel, 7, this.f);
        b.a(parcel, 8, this.g);
        b.a(parcel, 9, this.h);
        b.a(parcel, 10, this.i);
        b.a(parcel, 11, this.j);
        b.b(parcel, 12, this.k);
        b.a(parcel, a);
    }
}
