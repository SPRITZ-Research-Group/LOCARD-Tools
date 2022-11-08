package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.b.b.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "GroundOverlayOptionsCreator")
@Reserved({1})
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    public static final Creator<GroundOverlayOptions> CREATOR = new n();
    @Field(getter = "getWrappedImageDescriptorImplBinder", id = 2, type = "android.os.IBinder")
    @NonNull
    private a a;
    @Field(getter = "getLocation", id = 3)
    private LatLng b;
    @Field(getter = "getWidth", id = 4)
    private float c;
    @Field(getter = "getHeight", id = 5)
    private float d;
    @Field(getter = "getBounds", id = 6)
    private LatLngBounds e;
    @Field(getter = "getBearing", id = 7)
    private float f;
    @Field(getter = "getZIndex", id = 8)
    private float g;
    @Field(getter = "isVisible", id = 9)
    private boolean h = true;
    @Field(getter = "getTransparency", id = 10)
    private float i = 0.0f;
    @Field(getter = "getAnchorU", id = 11)
    private float j = 0.5f;
    @Field(getter = "getAnchorV", id = 12)
    private float k = 0.5f;
    @Field(getter = "isClickable", id = 13)
    private boolean l = false;

    @Constructor
    GroundOverlayOptions(@Param(id = 2) IBinder iBinder, @Param(id = 3) LatLng latLng, @Param(id = 4) float f, @Param(id = 5) float f2, @Param(id = 6) LatLngBounds latLngBounds, @Param(id = 7) float f3, @Param(id = 8) float f4, @Param(id = 9) boolean z, @Param(id = 10) float f5, @Param(id = 11) float f6, @Param(id = 12) float f7, @Param(id = 13) boolean z2) {
        this.a = new a(a.a(iBinder));
        this.b = latLng;
        this.c = f;
        this.d = f2;
        this.e = latLngBounds;
        this.f = f3;
        this.g = f4;
        this.h = z;
        this.i = f5;
        this.j = f6;
        this.k = f7;
        this.l = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a.a().asBinder());
        b.a(parcel, 3, this.b, i);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e, i);
        b.a(parcel, 7, this.f);
        b.a(parcel, 8, this.g);
        b.a(parcel, 9, this.h);
        b.a(parcel, 10, this.i);
        b.a(parcel, 11, this.j);
        b.a(parcel, 12, this.k);
        b.a(parcel, 13, this.l);
        b.a(parcel, a);
    }
}
