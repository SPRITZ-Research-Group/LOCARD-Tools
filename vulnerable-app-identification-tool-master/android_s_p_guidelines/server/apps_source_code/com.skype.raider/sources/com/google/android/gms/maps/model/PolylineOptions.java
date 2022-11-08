package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
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

@Class(creator = "PolylineOptionsCreator")
@Reserved({1})
public final class PolylineOptions extends AbstractSafeParcelable {
    public static final Creator<PolylineOptions> CREATOR = new v();
    @Field(getter = "getPoints", id = 2)
    private final List<LatLng> a;
    @Field(getter = "getWidth", id = 3)
    private float b;
    @Field(getter = "getColor", id = 4)
    private int c;
    @Field(getter = "getZIndex", id = 5)
    private float d;
    @Field(getter = "isVisible", id = 6)
    private boolean e;
    @Field(getter = "isGeodesic", id = 7)
    private boolean f;
    @Field(getter = "isClickable", id = 8)
    private boolean g;
    @Field(getter = "getStartCap", id = 9)
    @NonNull
    private Cap h;
    @Field(getter = "getEndCap", id = 10)
    @NonNull
    private Cap i;
    @Field(getter = "getJointType", id = 11)
    private int j;
    @Nullable
    @Field(getter = "getPattern", id = 12)
    private List<PatternItem> k;

    public PolylineOptions() {
        this.b = 10.0f;
        this.c = -16777216;
        this.d = 0.0f;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = new ButtCap();
        this.i = new ButtCap();
        this.j = 0;
        this.k = null;
        this.a = new ArrayList();
    }

    @Constructor
    PolylineOptions(@Param(id = 2) List list, @Param(id = 3) float f, @Param(id = 4) int i, @Param(id = 5) float f2, @Param(id = 6) boolean z, @Param(id = 7) boolean z2, @Param(id = 8) boolean z3, @Nullable @Param(id = 9) Cap cap, @Nullable @Param(id = 10) Cap cap2, @Param(id = 11) int i2, @Nullable @Param(id = 12) List<PatternItem> list2) {
        this.b = 10.0f;
        this.c = -16777216;
        this.d = 0.0f;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = new ButtCap();
        this.i = new ButtCap();
        this.j = 0;
        this.k = null;
        this.a = list;
        this.b = f;
        this.c = i;
        this.d = f2;
        this.e = z;
        this.f = z2;
        this.g = z3;
        if (cap != null) {
            this.h = cap;
        }
        if (cap2 != null) {
            this.i = cap2;
        }
        this.j = i2;
        this.k = list2;
    }

    public final PolylineOptions a(float f) {
        this.b = f;
        return this;
    }

    public final PolylineOptions a(int i) {
        this.c = i;
        return this;
    }

    public final PolylineOptions a(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.a.add(add);
        }
        return this;
    }

    public final PolylineOptions a(boolean z) {
        this.f = z;
        return this;
    }

    public final PolylineOptions b(float f) {
        this.d = f;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.b(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e);
        b.a(parcel, 7, this.f);
        b.a(parcel, 8, this.g);
        b.a(parcel, 9, this.h, i);
        b.a(parcel, 10, this.i, i);
        b.a(parcel, 11, this.j);
        b.b(parcel, 12, this.k);
        b.a(parcel, a);
    }
}
