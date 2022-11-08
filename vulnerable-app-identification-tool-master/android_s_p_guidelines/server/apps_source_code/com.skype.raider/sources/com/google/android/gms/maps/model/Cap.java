package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.b.b.a;
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

@Class(creator = "CapCreator")
@Reserved({1})
public class Cap extends AbstractSafeParcelable {
    public static final Creator<Cap> CREATOR = new l();
    private static final String a = Cap.class.getSimpleName();
    @Field(getter = "getType", id = 2)
    private final int b;
    @Nullable
    @Field(getter = "getWrappedBitmapDescriptorImplBinder", id = 3, type = "android.os.IBinder")
    private final a c;
    @Nullable
    @Field(getter = "getBitmapRefWidth", id = 4)
    private final Float d;

    protected Cap(int i) {
        this(i, null, null);
    }

    @Constructor
    Cap(@Param(id = 2) int i, @Nullable @Param(id = 3) IBinder iBinder, @Nullable @Param(id = 4) Float f) {
        this(i, iBinder == null ? null : new a(a.a(iBinder)), f);
    }

    private Cap(int i, @Nullable a aVar, @Nullable Float f) {
        int i2 = (f == null || f.floatValue() <= 0.0f) ? 0 : 1;
        boolean z = (i == 3 && (aVar == null || i2 == 0)) ? false : true;
        ab.b(z, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[]{Integer.valueOf(i), aVar, f}));
        this.b = i;
        this.c = aVar;
        this.d = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.b == cap.b && z.a(this.c, cap.c) && z.a(this.d, cap.d);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), this.c, this.d});
    }

    public String toString() {
        return "[Cap: type=" + this.b + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c == null ? null : this.c.a().asBinder());
        b.a(parcel, 4, this.d);
        b.a(parcel, a);
    }
}
