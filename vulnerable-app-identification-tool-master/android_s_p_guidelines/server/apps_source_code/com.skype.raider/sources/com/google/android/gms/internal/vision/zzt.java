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

@Class(creator = "LineBoxParcelCreator")
@Reserved({1})
public final class zzt extends AbstractSafeParcelable {
    public static final Creator<zzt> CREATOR = new n();
    @Field(id = 2)
    public final zzac[] a;
    @Field(id = 3)
    public final zzn b;
    @Field(id = 6)
    public final String c;
    @Field(id = 8)
    public final String d;
    @Field(id = 10)
    public final boolean e;
    @Field(id = 11)
    public final int f;
    @Field(id = 12)
    public final int g;
    @Field(id = 4)
    private final zzn h;
    @Field(id = 5)
    private final zzn i;
    @Field(id = 7)
    private final float j;
    @Field(id = 9)
    private final int k;

    @Constructor
    public zzt(@Param(id = 2) zzac[] zzacArr, @Param(id = 3) zzn zzn, @Param(id = 4) zzn zzn2, @Param(id = 5) zzn zzn3, @Param(id = 6) String str, @Param(id = 7) float f, @Param(id = 8) String str2, @Param(id = 9) int i, @Param(id = 10) boolean z, @Param(id = 11) int i2, @Param(id = 12) int i3) {
        this.a = zzacArr;
        this.b = zzn;
        this.h = zzn2;
        this.i = zzn3;
        this.c = str;
        this.j = f;
        this.d = str2;
        this.k = i;
        this.e = z;
        this.f = i2;
        this.g = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b, i);
        b.a(parcel, 4, this.h, i);
        b.a(parcel, 5, this.i, i);
        b.a(parcel, 6, this.c);
        b.a(parcel, 7, this.j);
        b.a(parcel, 8, this.d);
        b.a(parcel, 9, this.k);
        b.a(parcel, 10, this.e);
        b.a(parcel, 11, this.f);
        b.a(parcel, 12, this.g);
        b.a(parcel, a);
    }
}
