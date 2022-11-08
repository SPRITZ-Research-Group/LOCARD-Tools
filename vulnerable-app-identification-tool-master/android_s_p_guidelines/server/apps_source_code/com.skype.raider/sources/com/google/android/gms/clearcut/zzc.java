package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Arrays;

@Class(creator = "CollectForDebugParcelableCreator")
public final class zzc extends AbstractSafeParcelable {
    public static final Creator<zzc> CREATOR = new c();
    @Field(defaultValue = "false", id = 1)
    private final boolean a;
    @Field(id = 3)
    private final long b;
    @Field(id = 2)
    private final long c;

    @Constructor
    public zzc(@Param(id = 1) boolean z, @Param(id = 3) long j, @Param(id = 2) long j2) {
        this.a = z;
        this.b = j;
        this.c = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return this.a == zzc.a && this.b == zzc.b && this.c == zzc.c;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.a), Long.valueOf(this.b), Long.valueOf(this.c)});
    }

    public final String toString() {
        return "CollectForDebugParcelable[skipPersistentStorage: " + this.a + ",collectForDebugStartTimeMillis: " + this.b + ",collectForDebugExpiryTimeMillis: " + this.c + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.c);
        b.a(parcel, 3, this.b);
        b.a(parcel, a);
    }
}
