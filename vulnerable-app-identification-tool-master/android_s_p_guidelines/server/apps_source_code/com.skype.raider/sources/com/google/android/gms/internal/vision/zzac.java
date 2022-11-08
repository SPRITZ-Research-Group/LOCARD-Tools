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

@Class(creator = "WordBoxParcelCreator")
@Reserved({1})
public final class zzac extends AbstractSafeParcelable {
    public static final Creator<zzac> CREATOR = new c();
    @Field(id = 3)
    public final zzn a;
    @Field(id = 5)
    public final String b;
    @Field(id = 7)
    public final String c;
    @Field(id = 2)
    private final zzx[] d;
    @Field(id = 4)
    private final zzn e;
    @Field(id = 6)
    private final float f;
    @Field(id = 8)
    private final boolean g;

    @Constructor
    public zzac(@Param(id = 2) zzx[] zzxArr, @Param(id = 3) zzn zzn, @Param(id = 4) zzn zzn2, @Param(id = 5) String str, @Param(id = 6) float f, @Param(id = 7) String str2, @Param(id = 8) boolean z) {
        this.d = zzxArr;
        this.a = zzn;
        this.e = zzn2;
        this.b = str;
        this.f = f;
        this.c = str2;
        this.g = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.d, i);
        b.a(parcel, 3, this.a, i);
        b.a(parcel, 4, this.e, i);
        b.a(parcel, 5, this.b);
        b.a(parcel, 6, this.f);
        b.a(parcel, 7, this.c);
        b.a(parcel, 8, this.g);
        b.a(parcel, a);
    }
}
