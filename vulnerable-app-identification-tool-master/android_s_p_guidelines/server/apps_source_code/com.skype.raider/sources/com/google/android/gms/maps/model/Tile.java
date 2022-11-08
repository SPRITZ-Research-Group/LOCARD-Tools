package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "TileCreator")
@Reserved({1})
public final class Tile extends AbstractSafeParcelable {
    public static final Creator<Tile> CREATOR = new ab();
    @Field(id = 2)
    public final int a;
    @Field(id = 3)
    public final int b;
    @Field(id = 4)
    public final byte[] c;

    @Constructor
    public Tile(@Param(id = 2) int i, @Param(id = 3) int i2, @Param(id = 4) byte[] bArr) {
        this.a = i;
        this.b = i2;
        this.c = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, a);
    }
}
