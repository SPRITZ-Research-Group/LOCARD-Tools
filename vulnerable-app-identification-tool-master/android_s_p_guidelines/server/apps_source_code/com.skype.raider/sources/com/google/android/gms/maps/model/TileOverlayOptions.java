package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.c.g;
import com.google.android.gms.internal.c.h;

@Class(creator = "TileOverlayOptionsCreator")
@Reserved({1})
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Creator<TileOverlayOptions> CREATOR = new ae();
    @Field(getter = "getTileProviderDelegate", id = 2, type = "android.os.IBinder")
    private g a;
    private i b;
    @Field(getter = "isVisible", id = 3)
    private boolean c = true;
    @Field(getter = "getZIndex", id = 4)
    private float d;
    @Field(defaultValue = "true", getter = "getFadeIn", id = 5)
    private boolean e = true;
    @Field(getter = "getTransparency", id = 6)
    private float f = 0.0f;

    @Constructor
    TileOverlayOptions(@Param(id = 2) IBinder iBinder, @Param(id = 3) boolean z, @Param(id = 4) float f, @Param(id = 5) boolean z2, @Param(id = 6) float f2) {
        this.a = h.a(iBinder);
        this.b = this.a == null ? null : new ac(this);
        this.c = z;
        this.d = f;
        this.e = z2;
        this.f = f2;
    }

    public final TileOverlayOptions a(float f) {
        this.d = f;
        return this;
    }

    public final TileOverlayOptions a(i iVar) {
        this.b = iVar;
        this.a = this.b == null ? null : new ad(iVar);
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a.asBinder());
        b.a(parcel, 3, this.c);
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.e);
        b.a(parcel, 6, this.f);
        b.a(parcel, a);
    }
}
