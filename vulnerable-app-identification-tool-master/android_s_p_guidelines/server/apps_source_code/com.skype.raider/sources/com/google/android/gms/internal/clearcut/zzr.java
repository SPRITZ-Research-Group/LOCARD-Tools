package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import java.util.Arrays;

@Class(creator = "PlayLoggerContextCreator")
@Reserved({1})
public final class zzr extends AbstractSafeParcelable {
    public static final Creator<zzr> CREATOR = new ef();
    @Field(id = 4)
    public final int a;
    @Field(id = 8)
    public final String b;
    @Field(id = 2)
    private final String c;
    @Field(id = 3)
    private final int d;
    @Field(id = 5)
    private final String e;
    @Field(id = 6)
    private final String f;
    @Field(defaultValue = "true", id = 7)
    private final boolean g;
    @Field(id = 9)
    private final boolean h;
    @Field(id = 10)
    private final int i;

    @Constructor
    public zzr(@Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) String str2, @Param(id = 6) String str3, @Param(id = 7) boolean z, @Param(id = 8) String str4, @Param(id = 9) boolean z2, @Param(id = 10) int i3) {
        this.c = str;
        this.d = i;
        this.a = i2;
        this.e = str2;
        this.f = str3;
        this.g = z;
        this.b = str4;
        this.h = z2;
        this.i = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzr)) {
            return false;
        }
        zzr zzr = (zzr) obj;
        return z.a(this.c, zzr.c) && this.d == zzr.d && this.a == zzr.a && z.a(this.b, zzr.b) && z.a(this.e, zzr.e) && z.a(this.f, zzr.f) && this.g == zzr.g && this.h == zzr.h && this.i == zzr.i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.c, Integer.valueOf(this.d), Integer.valueOf(this.a), this.b, this.e, this.f, Boolean.valueOf(this.g), Boolean.valueOf(this.h), Integer.valueOf(this.i)});
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PlayLoggerContext[");
        stringBuilder.append("package=").append(this.c).append(',');
        stringBuilder.append("packageVersionCode=").append(this.d).append(',');
        stringBuilder.append("logSource=").append(this.a).append(',');
        stringBuilder.append("logSourceName=").append(this.b).append(',');
        stringBuilder.append("uploadAccount=").append(this.e).append(',');
        stringBuilder.append("loggingId=").append(this.f).append(',');
        stringBuilder.append("logAndroidId=").append(this.g).append(',');
        stringBuilder.append("isAnonymous=").append(this.h).append(',');
        stringBuilder.append("qosTier=").append(this.i);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.c);
        b.a(parcel, 3, this.d);
        b.a(parcel, 4, this.a);
        b.a(parcel, 5, this.e);
        b.a(parcel, 6, this.f);
        b.a(parcel, 7, this.g);
        b.a(parcel, 8, this.b);
        b.a(parcel, 9, this.h);
        b.a(parcel, 10, this.i);
        b.a(parcel, a);
    }
}
