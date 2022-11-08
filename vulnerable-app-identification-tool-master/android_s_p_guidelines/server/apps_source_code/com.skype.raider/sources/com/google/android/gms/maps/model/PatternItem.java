package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import java.util.Arrays;

@Class(creator = "PatternItemCreator")
@Reserved({1})
public class PatternItem extends AbstractSafeParcelable {
    public static final Creator<PatternItem> CREATOR = new s();
    private static final String a = PatternItem.class.getSimpleName();
    @Field(getter = "getType", id = 2)
    private final int b;
    @Nullable
    @Field(getter = "getLength", id = 3)
    private final Float c;

    @Constructor
    public PatternItem(@Param(id = 2) int i, @Nullable @Param(id = 3) Float f) {
        boolean z = true;
        if (i != 1 && (f == null || f.floatValue() < 0.0f)) {
            z = false;
        }
        String valueOf = String.valueOf(f);
        ab.b(z, new StringBuilder(String.valueOf(valueOf).length() + 45).append("Invalid PatternItem: type=").append(i).append(" length=").append(valueOf).toString());
        this.b = i;
        this.c = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.b == patternItem.b && z.a(this.c, patternItem.c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), this.c});
    }

    public String toString() {
        int i = this.b;
        String valueOf = String.valueOf(this.c);
        return new StringBuilder(String.valueOf(valueOf).length() + 39).append("[PatternItem: type=").append(i).append(" length=").append(valueOf).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, a);
    }
}
