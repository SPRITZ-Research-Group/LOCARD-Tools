package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.vision.b;

@Class(creator = "FrameMetadataParcelCreator")
@Reserved({1})
public final class zzk extends AbstractSafeParcelable {
    public static final Creator<zzk> CREATOR = new l();
    @Field(id = 2)
    public int a;
    @Field(id = 3)
    public int b;
    @Field(id = 6)
    public int c;
    @Field(id = 4)
    private int d;
    @Field(id = 5)
    private long e;

    @Constructor
    public zzk(@Param(id = 2) int i, @Param(id = 3) int i2, @Param(id = 4) int i3, @Param(id = 5) long j, @Param(id = 6) int i4) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.e = j;
        this.c = i4;
    }

    public static zzk a(b bVar) {
        zzk zzk = new zzk();
        zzk.a = bVar.a().a();
        zzk.b = bVar.a().b();
        zzk.c = bVar.a().e();
        zzk.d = bVar.a().c();
        zzk.e = bVar.a().d();
        return zzk;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.b.a(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, this.a);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, this.b);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, this.d);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, this.e);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, this.c);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, a);
    }
}
