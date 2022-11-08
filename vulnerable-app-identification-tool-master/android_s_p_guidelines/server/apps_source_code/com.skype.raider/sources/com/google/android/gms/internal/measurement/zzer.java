package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Iterator;

@Class(creator = "EventParamsCreator")
@Reserved({1})
public final class zzer extends AbstractSafeParcelable implements Iterable<String> {
    public static final Creator<zzer> CREATOR = new aj();
    @Field(getter = "z", id = 2)
    private final Bundle a;

    @Constructor
    zzer(@Param(id = 2) Bundle bundle) {
        this.a = bundle;
    }

    public final int a() {
        return this.a.size();
    }

    final Object a(String str) {
        return this.a.get(str);
    }

    public final Bundle b() {
        return new Bundle(this.a);
    }

    final Long b(String str) {
        return Long.valueOf(this.a.getLong(str));
    }

    final Double c(String str) {
        return Double.valueOf(this.a.getDouble(str));
    }

    final String d(String str) {
        return this.a.getString(str);
    }

    public final Iterator<String> iterator() {
        return new ai(this);
    }

    public final String toString() {
        return this.a.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, b());
        b.a(parcel, a);
    }
}
