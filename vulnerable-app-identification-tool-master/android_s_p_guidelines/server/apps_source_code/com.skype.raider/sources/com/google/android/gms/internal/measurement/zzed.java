package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "ConditionalUserPropertyParcelCreator")
public final class zzed extends AbstractSafeParcelable {
    public static final Creator<zzed> CREATOR = new v();
    @Field(id = 2)
    public String a;
    @Field(id = 3)
    public String b;
    @Field(id = 4)
    public zzjx c;
    @Field(id = 5)
    public long d;
    @Field(id = 6)
    public boolean e;
    @Field(id = 7)
    public String f;
    @Field(id = 8)
    public zzeu g;
    @Field(id = 9)
    public long h;
    @Field(id = 10)
    public zzeu i;
    @Field(id = 11)
    public long j;
    @Field(id = 12)
    public zzeu k;

    zzed(zzed zzed) {
        ab.a((Object) zzed);
        this.a = zzed.a;
        this.b = zzed.b;
        this.c = zzed.c;
        this.d = zzed.d;
        this.e = zzed.e;
        this.f = zzed.f;
        this.g = zzed.g;
        this.h = zzed.h;
        this.i = zzed.i;
        this.j = zzed.j;
        this.k = zzed.k;
    }

    @Constructor
    zzed(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) zzjx zzjx, @Param(id = 5) long j, @Param(id = 6) boolean z, @Param(id = 7) String str3, @Param(id = 8) zzeu zzeu, @Param(id = 9) long j2, @Param(id = 10) zzeu zzeu2, @Param(id = 11) long j3, @Param(id = 12) zzeu zzeu3) {
        this.a = str;
        this.b = str2;
        this.c = zzjx;
        this.d = j;
        this.e = z;
        this.f = str3;
        this.g = zzeu;
        this.h = j2;
        this.i = zzeu2;
        this.j = j3;
        this.k = zzeu3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c, i);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e);
        b.a(parcel, 7, this.f);
        b.a(parcel, 8, this.g, i);
        b.a(parcel, 9, this.h);
        b.a(parcel, 10, this.i, i);
        b.a(parcel, 11, this.j);
        b.a(parcel, 12, this.k, i);
        b.a(parcel, a);
    }
}
