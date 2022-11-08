package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
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
import com.google.android.gms.maps.h.a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

@Class(creator = "GoogleMapOptionsCreator")
@Reserved({1})
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<GoogleMapOptions> CREATOR = new k();
    @Field(defaultValue = "-1", getter = "getZOrderOnTopForParcel", id = 2, type = "byte")
    private Boolean a;
    @Field(defaultValue = "-1", getter = "getUseViewLifecycleInFragmentForParcel", id = 3, type = "byte")
    private Boolean b;
    @Field(getter = "getMapType", id = 4)
    private int c = -1;
    @Field(getter = "getCamera", id = 5)
    private CameraPosition d;
    @Field(defaultValue = "-1", getter = "getZoomControlsEnabledForParcel", id = 6, type = "byte")
    private Boolean e;
    @Field(defaultValue = "-1", getter = "getCompassEnabledForParcel", id = 7, type = "byte")
    private Boolean f;
    @Field(defaultValue = "-1", getter = "getScrollGesturesEnabledForParcel", id = 8, type = "byte")
    private Boolean g;
    @Field(defaultValue = "-1", getter = "getZoomGesturesEnabledForParcel", id = 9, type = "byte")
    private Boolean h;
    @Field(defaultValue = "-1", getter = "getTiltGesturesEnabledForParcel", id = 10, type = "byte")
    private Boolean i;
    @Field(defaultValue = "-1", getter = "getRotateGesturesEnabledForParcel", id = 11, type = "byte")
    private Boolean j;
    @Field(defaultValue = "-1", getter = "getLiteModeForParcel", id = 12, type = "byte")
    private Boolean k;
    @Field(defaultValue = "-1", getter = "getMapToolbarEnabledForParcel", id = 14, type = "byte")
    private Boolean l;
    @Field(defaultValue = "-1", getter = "getAmbientEnabledForParcel", id = 15, type = "byte")
    private Boolean m;
    @Field(getter = "getMinZoomPreference", id = 16)
    private Float n = null;
    @Field(getter = "getMaxZoomPreference", id = 17)
    private Float o = null;
    @Field(getter = "getLatLngBoundsForCameraTarget", id = 18)
    private LatLngBounds p = null;

    @Constructor
    GoogleMapOptions(@Param(id = 2) byte b, @Param(id = 3) byte b2, @Param(id = 4) int i, @Param(id = 5) CameraPosition cameraPosition, @Param(id = 6) byte b3, @Param(id = 7) byte b4, @Param(id = 8) byte b5, @Param(id = 9) byte b6, @Param(id = 10) byte b7, @Param(id = 11) byte b8, @Param(id = 12) byte b9, @Param(id = 14) byte b10, @Param(id = 15) byte b11, @Param(id = 16) Float f, @Param(id = 17) Float f2, @Param(id = 18) LatLngBounds latLngBounds) {
        this.a = j.a(b);
        this.b = j.a(b2);
        this.c = i;
        this.d = cameraPosition;
        this.e = j.a(b3);
        this.f = j.a(b4);
        this.g = j.a(b5);
        this.h = j.a(b6);
        this.i = j.a(b7);
        this.j = j.a(b8);
        this.k = j.a(b9);
        this.l = j.a(b10);
        this.m = j.a(b11);
        this.n = f;
        this.o = f2;
        this.p = latLngBounds;
    }

    public static GoogleMapOptions a(Context context, AttributeSet attributeSet) {
        CameraPosition cameraPosition = null;
        if (context == null || attributeSet == null) {
            return null;
        }
        LatLngBounds latLngBounds;
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, a.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(a.MapAttrs_mapType)) {
            googleMapOptions.c = obtainAttributes.getInt(a.MapAttrs_mapType, -1);
        }
        if (obtainAttributes.hasValue(a.MapAttrs_zOrderOnTop)) {
            googleMapOptions.a = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_useViewLifecycle)) {
            googleMapOptions.b = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_uiCompass)) {
            googleMapOptions.f = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_uiRotateGestures)) {
            googleMapOptions.j = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_uiScrollGestures)) {
            googleMapOptions.g = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_uiTiltGestures)) {
            googleMapOptions.i = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_uiZoomGestures)) {
            googleMapOptions.h = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_uiZoomControls)) {
            googleMapOptions.e = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_liteMode)) {
            googleMapOptions.a(obtainAttributes.getBoolean(a.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_uiMapToolbar)) {
            googleMapOptions.l = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_ambientEnabled)) {
            googleMapOptions.m = Boolean.valueOf(obtainAttributes.getBoolean(a.MapAttrs_ambientEnabled, false));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.n = Float.valueOf(obtainAttributes.getFloat(a.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(a.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.o = Float.valueOf(obtainAttributes.getFloat(a.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        if (context == null || attributeSet == null) {
            latLngBounds = null;
        } else {
            TypedArray obtainAttributes2 = context.getResources().obtainAttributes(attributeSet, a.MapAttrs);
            Float valueOf = obtainAttributes2.hasValue(a.MapAttrs_latLngBoundsSouthWestLatitude) ? Float.valueOf(obtainAttributes2.getFloat(a.MapAttrs_latLngBoundsSouthWestLatitude, 0.0f)) : null;
            Float valueOf2 = obtainAttributes2.hasValue(a.MapAttrs_latLngBoundsSouthWestLongitude) ? Float.valueOf(obtainAttributes2.getFloat(a.MapAttrs_latLngBoundsSouthWestLongitude, 0.0f)) : null;
            Float valueOf3 = obtainAttributes2.hasValue(a.MapAttrs_latLngBoundsNorthEastLatitude) ? Float.valueOf(obtainAttributes2.getFloat(a.MapAttrs_latLngBoundsNorthEastLatitude, 0.0f)) : null;
            Float valueOf4 = obtainAttributes2.hasValue(a.MapAttrs_latLngBoundsNorthEastLongitude) ? Float.valueOf(obtainAttributes2.getFloat(a.MapAttrs_latLngBoundsNorthEastLongitude, 0.0f)) : null;
            obtainAttributes2.recycle();
            latLngBounds = (valueOf == null || valueOf2 == null || valueOf3 == null || valueOf4 == null) ? null : new LatLngBounds(new LatLng((double) valueOf.floatValue(), (double) valueOf2.floatValue()), new LatLng((double) valueOf3.floatValue(), (double) valueOf4.floatValue()));
        }
        googleMapOptions.p = latLngBounds;
        if (!(context == null || attributeSet == null)) {
            TypedArray obtainAttributes3 = context.getResources().obtainAttributes(attributeSet, a.MapAttrs);
            LatLng latLng = new LatLng((double) (obtainAttributes3.hasValue(a.MapAttrs_cameraTargetLat) ? obtainAttributes3.getFloat(a.MapAttrs_cameraTargetLat, 0.0f) : 0.0f), (double) (obtainAttributes3.hasValue(a.MapAttrs_cameraTargetLng) ? obtainAttributes3.getFloat(a.MapAttrs_cameraTargetLng, 0.0f) : 0.0f));
            CameraPosition.a a = CameraPosition.a();
            a.a(latLng);
            if (obtainAttributes3.hasValue(a.MapAttrs_cameraZoom)) {
                a.a(obtainAttributes3.getFloat(a.MapAttrs_cameraZoom, 0.0f));
            }
            if (obtainAttributes3.hasValue(a.MapAttrs_cameraBearing)) {
                a.c(obtainAttributes3.getFloat(a.MapAttrs_cameraBearing, 0.0f));
            }
            if (obtainAttributes3.hasValue(a.MapAttrs_cameraTilt)) {
                a.b(obtainAttributes3.getFloat(a.MapAttrs_cameraTilt, 0.0f));
            }
            obtainAttributes3.recycle();
            cameraPosition = a.a();
        }
        googleMapOptions.d = cameraPosition;
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public final GoogleMapOptions a(boolean z) {
        this.k = Boolean.valueOf(z);
        return this;
    }

    public final String toString() {
        return z.a(this).a("MapType", Integer.valueOf(this.c)).a("LiteMode", this.k).a("Camera", this.d).a("CompassEnabled", this.f).a("ZoomControlsEnabled", this.e).a("ScrollGesturesEnabled", this.g).a("ZoomGesturesEnabled", this.h).a("TiltGesturesEnabled", this.i).a("RotateGesturesEnabled", this.j).a("MapToolbarEnabled", this.l).a("AmbientEnabled", this.m).a("MinZoomPreference", this.n).a("MaxZoomPreference", this.o).a("LatLngBoundsForCameraTarget", this.p).a("ZOrderOnTop", this.a).a("UseViewLifecycleInFragment", this.b).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, j.a(this.a));
        b.a(parcel, 3, j.a(this.b));
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d, i);
        b.a(parcel, 6, j.a(this.e));
        b.a(parcel, 7, j.a(this.f));
        b.a(parcel, 8, j.a(this.g));
        b.a(parcel, 9, j.a(this.h));
        b.a(parcel, 10, j.a(this.i));
        b.a(parcel, 11, j.a(this.j));
        b.a(parcel, 12, j.a(this.k));
        b.a(parcel, 14, j.a(this.l));
        b.a(parcel, 15, j.a(this.m));
        b.a(parcel, 16, this.n);
        b.a(parcel, 17, this.o);
        b.a(parcel, 18, this.p, i);
        b.a(parcel, a);
    }
}
