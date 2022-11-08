package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.maps.a.j;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

@Class(creator = "StreetViewPanoramaOptionsCreator")
@Reserved({1})
public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<StreetViewPanoramaOptions> CREATOR = new m();
    @Field(getter = "getStreetViewPanoramaCamera", id = 2)
    private StreetViewPanoramaCamera a;
    @Field(getter = "getPanoramaId", id = 3)
    private String b;
    @Field(getter = "getPosition", id = 4)
    private LatLng c;
    @Field(getter = "getRadius", id = 5)
    private Integer d;
    @Field(getter = "getUserNavigationEnabledForParcel", id = 6, type = "byte")
    private Boolean e = Boolean.valueOf(true);
    @Field(getter = "getZoomGesturesEnabledForParcel", id = 7, type = "byte")
    private Boolean f = Boolean.valueOf(true);
    @Field(getter = "getPanningGesturesEnabledForParcel", id = 8, type = "byte")
    private Boolean g = Boolean.valueOf(true);
    @Field(getter = "getStreetNamesEnabledForParcel", id = 9, type = "byte")
    private Boolean h = Boolean.valueOf(true);
    @Field(getter = "getUseViewLifecycleInFragmentForParcel", id = 10, type = "byte")
    private Boolean i;
    @Field(getter = "getSource", id = 11)
    private StreetViewSource j = StreetViewSource.a;

    @Constructor
    StreetViewPanoramaOptions(@Param(id = 2) StreetViewPanoramaCamera streetViewPanoramaCamera, @Param(id = 3) String str, @Param(id = 4) LatLng latLng, @Param(id = 5) Integer num, @Param(id = 6) byte b, @Param(id = 7) byte b2, @Param(id = 8) byte b3, @Param(id = 9) byte b4, @Param(id = 10) byte b5, @Param(id = 11) StreetViewSource streetViewSource) {
        this.a = streetViewPanoramaCamera;
        this.c = latLng;
        this.d = num;
        this.b = str;
        this.e = j.a(b);
        this.f = j.a(b2);
        this.g = j.a(b3);
        this.h = j.a(b4);
        this.i = j.a(b5);
        this.j = streetViewSource;
    }

    public final String toString() {
        return z.a(this).a("PanoramaId", this.b).a("Position", this.c).a("Radius", this.d).a("Source", this.j).a("StreetViewPanoramaCamera", this.a).a("UserNavigationEnabled", this.e).a("ZoomGesturesEnabled", this.f).a("PanningGesturesEnabled", this.g).a("StreetNamesEnabled", this.h).a("UseViewLifecycleInFragment", this.i).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c, i);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, j.a(this.e));
        b.a(parcel, 7, j.a(this.f));
        b.a(parcel, 8, j.a(this.g));
        b.a(parcel, 9, j.a(this.h));
        b.a(parcel, 10, j.a(this.i));
        b.a(parcel, 11, this.j, i);
        b.a(parcel, a);
    }
}
