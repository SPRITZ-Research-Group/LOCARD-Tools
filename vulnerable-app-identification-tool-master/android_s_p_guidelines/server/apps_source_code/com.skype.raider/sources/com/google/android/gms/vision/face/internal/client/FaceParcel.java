package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;

@UsedByNative("wrapper.cc")
@Class(creator = "FaceParcelCreator")
public class FaceParcel extends AbstractSafeParcelable {
    public static final Creator<FaceParcel> CREATOR = new a();
    @Field(id = 2)
    public final int a;
    @Field(id = 3)
    public final float b;
    @Field(id = 4)
    public final float c;
    @Field(id = 5)
    public final float d;
    @Field(id = 6)
    public final float e;
    @Field(id = 7)
    public final float f;
    @Field(id = 8)
    public final float g;
    @Field(id = 9)
    public final LandmarkParcel[] h;
    @Field(id = 10)
    public final float i;
    @Field(id = 11)
    public final float j;
    @Field(id = 12)
    public final float k;
    @VersionField(id = 1)
    private final int l;

    @Constructor
    public FaceParcel(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) float f, @Param(id = 4) float f2, @Param(id = 5) float f3, @Param(id = 6) float f4, @Param(id = 7) float f5, @Param(id = 8) float f6, @Param(id = 9) LandmarkParcel[] landmarkParcelArr, @Param(id = 10) float f7, @Param(id = 11) float f8, @Param(id = 12) float f9) {
        this.l = i;
        this.a = i2;
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
        this.g = f6;
        this.h = landmarkParcelArr;
        this.i = f7;
        this.j = f8;
        this.k = f9;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.l);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e);
        b.a(parcel, 7, this.f);
        b.a(parcel, 8, this.g);
        b.a(parcel, 9, this.h, i);
        b.a(parcel, 10, this.i);
        b.a(parcel, 11, this.j);
        b.a(parcel, 12, this.k);
        b.a(parcel, a);
    }
}
