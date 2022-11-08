package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "EventParcelCreator")
@Reserved({1})
public final class zzeu extends AbstractSafeParcelable {
    public static final Creator<zzeu> CREATOR = new ak();
    @Field(id = 2)
    public final String a;
    @Field(id = 3)
    public final zzer b;
    @Field(id = 4)
    public final String c;
    @Field(id = 5)
    public final long d;

    zzeu(zzeu zzeu, long j) {
        ab.a((Object) zzeu);
        this.a = zzeu.a;
        this.b = zzeu.b;
        this.c = zzeu.c;
        this.d = j;
    }

    @Constructor
    public zzeu(@Param(id = 2) String str, @Param(id = 3) zzer zzer, @Param(id = 4) String str2, @Param(id = 5) long j) {
        this.a = str;
        this.b = zzer;
        this.c = str2;
        this.d = j;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.a;
        String valueOf = String.valueOf(this.b);
        return new StringBuilder(((String.valueOf(str).length() + 21) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("origin=").append(str).append(",name=").append(str2).append(",params=").append(valueOf).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b, i);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, a);
    }
}
