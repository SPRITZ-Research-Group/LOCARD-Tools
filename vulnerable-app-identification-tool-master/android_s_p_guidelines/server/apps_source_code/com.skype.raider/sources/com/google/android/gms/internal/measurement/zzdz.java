package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "AppMetadataCreator")
@Reserved({1})
public final class zzdz extends AbstractSafeParcelable {
    public static final Creator<zzdz> CREATOR = new s();
    @Field(id = 2)
    public final String a;
    @Field(id = 3)
    public final String b;
    @Field(id = 4)
    public final String c;
    @Field(id = 5)
    public final String d;
    @Field(id = 6)
    public final long e;
    @Field(id = 7)
    public final long f;
    @Field(id = 8)
    public final String g;
    @Field(defaultValue = "true", id = 9)
    public final boolean h;
    @Field(id = 10)
    public final boolean i;
    @Field(defaultValueUnchecked = "Integer.MIN_VALUE", id = 11)
    public final long j;
    @Field(id = 12)
    public final String k;
    @Field(id = 13)
    public final long l;
    @Field(id = 14)
    public final long m;
    @Field(id = 15)
    public final int n;
    @Field(defaultValue = "true", id = 16)
    public final boolean o;
    @Field(defaultValue = "true", id = 17)
    public final boolean p;
    @Field(id = 18)
    public final boolean q;

    zzdz(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3, boolean z4, boolean z5) {
        ab.a(str);
        this.a = str;
        if (TextUtils.isEmpty(str2)) {
            str2 = null;
        }
        this.b = str2;
        this.c = str3;
        this.j = j;
        this.d = str4;
        this.e = j2;
        this.f = j3;
        this.g = str5;
        this.h = z;
        this.i = z2;
        this.k = str6;
        this.l = j4;
        this.m = j5;
        this.n = i;
        this.o = z3;
        this.p = z4;
        this.q = z5;
    }

    @Constructor
    zzdz(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) long j, @Param(id = 7) long j2, @Param(id = 8) String str5, @Param(id = 9) boolean z, @Param(id = 10) boolean z2, @Param(id = 11) long j3, @Param(id = 12) String str6, @Param(id = 13) long j4, @Param(id = 14) long j5, @Param(id = 15) int i, @Param(id = 16) boolean z3, @Param(id = 17) boolean z4, @Param(id = 18) boolean z5) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.j = j3;
        this.d = str4;
        this.e = j;
        this.f = j2;
        this.g = str5;
        this.h = z;
        this.i = z2;
        this.k = str6;
        this.l = j4;
        this.m = j5;
        this.n = i;
        this.o = z3;
        this.p = z4;
        this.q = z5;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
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
        b.a(parcel, 16, this.o);
        b.a(parcel, 17, this.p);
        b.a(parcel, 18, this.q);
        b.a(parcel, a);
    }
}
