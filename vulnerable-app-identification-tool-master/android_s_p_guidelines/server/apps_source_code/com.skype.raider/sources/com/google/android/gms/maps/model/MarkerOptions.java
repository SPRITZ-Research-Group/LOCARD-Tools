package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.b.b.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "MarkerOptionsCreator")
@Reserved({1})
public final class MarkerOptions extends AbstractSafeParcelable {
    public static final Creator<MarkerOptions> CREATOR = new r();
    @Field(getter = "getPosition", id = 2)
    private LatLng a;
    @Field(getter = "getTitle", id = 3)
    private String b;
    @Field(getter = "getSnippet", id = 4)
    private String c;
    @Field(getter = "getWrappedIconDescriptorImplBinder", id = 5, type = "android.os.IBinder")
    private a d;
    @Field(getter = "getAnchorU", id = 6)
    private float e = 0.5f;
    @Field(getter = "getAnchorV", id = 7)
    private float f = 1.0f;
    @Field(getter = "isDraggable", id = 8)
    private boolean g;
    @Field(getter = "isVisible", id = 9)
    private boolean h = true;
    @Field(getter = "isFlat", id = 10)
    private boolean i = false;
    @Field(getter = "getRotation", id = 11)
    private float j = 0.0f;
    @Field(defaultValue = "0.5f", getter = "getInfoWindowAnchorU", id = 12)
    private float k = 0.5f;
    @Field(getter = "getInfoWindowAnchorV", id = 13)
    private float l = 0.0f;
    @Field(defaultValue = "1.0f", getter = "getAlpha", id = 14)
    private float m = 1.0f;
    @Field(getter = "getZIndex", id = 15)
    private float n;

    @Constructor
    MarkerOptions(@Param(id = 2) LatLng latLng, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) IBinder iBinder, @Param(id = 6) float f, @Param(id = 7) float f2, @Param(id = 8) boolean z, @Param(id = 9) boolean z2, @Param(id = 10) boolean z3, @Param(id = 11) float f3, @Param(id = 12) float f4, @Param(id = 13) float f5, @Param(id = 14) float f6, @Param(id = 15) float f7) {
        this.a = latLng;
        this.b = str;
        this.c = str2;
        if (iBinder == null) {
            this.d = null;
        } else {
            this.d = new a(a.a(iBinder));
        }
        this.e = f;
        this.f = f2;
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.j = f3;
        this.k = f4;
        this.l = f5;
        this.m = f6;
        this.n = f7;
    }

    public final MarkerOptions a(float f) {
        this.n = f;
        return this;
    }

    public final MarkerOptions a(float f, float f2) {
        this.e = f;
        this.f = f2;
        return this;
    }

    public final MarkerOptions a(@NonNull LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
        this.a = latLng;
        return this;
    }

    public final MarkerOptions a(@Nullable a aVar) {
        this.d = aVar;
        return this;
    }

    public final MarkerOptions a(@Nullable String str) {
        this.b = str;
        return this;
    }

    public final MarkerOptions a(boolean z) {
        this.g = z;
        return this;
    }

    public final MarkerOptions b(float f) {
        this.j = f;
        return this;
    }

    public final MarkerOptions b(float f, float f2) {
        this.k = f;
        this.l = f2;
        return this;
    }

    public final MarkerOptions b(@Nullable String str) {
        this.c = str;
        return this;
    }

    public final MarkerOptions b(boolean z) {
        this.i = z;
        return this;
    }

    public final MarkerOptions c(float f) {
        this.m = f;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d == null ? null : this.d.a().asBinder());
        b.a(parcel, 6, this.e);
        b.a(parcel, 7, this.f);
        b.a(parcel, 8, this.g);
        b.a(parcel, 9, this.h);
        b.a(parcel, 10, this.i);
        b.a(parcel, 11, this.j);
        b.a(parcel, 12, this.k);
        b.a(parcel, 13, this.l);
        b.a(parcel, 14, this.m);
        b.a(parcel, 15, this.n);
        b.a(parcel, a);
    }
}
