package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "BoundingBoxParcelCreator")
@Reserved({1})
public final class zzn extends AbstractSafeParcelable {
    public static final Creator<zzn> CREATOR = new m();
    @Field(id = 2)
    public final int a;
    @Field(id = 3)
    public final int b;
    @Field(id = 4)
    public final int c;
    @Field(id = 5)
    public final int d;
    @Field(id = 6)
    public final float e;

    @Constructor
    public zzn(@Param(id = 2) int i, @Param(id = 3) int i2, @Param(id = 4) int i3, @Param(id = 5) int i4, @Param(id = 6) float f) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e);
        b.a(parcel, a);
    }
}
