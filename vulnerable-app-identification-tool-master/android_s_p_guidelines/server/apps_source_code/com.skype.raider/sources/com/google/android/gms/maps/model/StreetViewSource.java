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
import java.util.Arrays;

@Class(creator = "StreetViewSourceCreator")
@Reserved({1})
public final class StreetViewSource extends AbstractSafeParcelable {
    public static final Creator<StreetViewSource> CREATOR = new aa();
    public static final StreetViewSource a = new StreetViewSource(0);
    public static final StreetViewSource b = new StreetViewSource(1);
    private static final String c = StreetViewSource.class.getSimpleName();
    @Field(getter = "getType", id = 2)
    private final int d;

    @Constructor
    public StreetViewSource(@Param(id = 2) int i) {
        this.d = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewSource)) {
            return false;
        }
        return this.d == ((StreetViewSource) obj).d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.d)});
    }

    public final String toString() {
        String str;
        switch (this.d) {
            case 0:
                str = "DEFAULT";
                break;
            case 1:
                str = "OUTDOOR";
                break;
            default:
                str = String.format("UNKNOWN(%s)", new Object[]{Integer.valueOf(this.d)});
                break;
        }
        return String.format("StreetViewSource:%s", new Object[]{str});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.d);
        b.a(parcel, a);
    }
}
