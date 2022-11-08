package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import com.microsoft.tokenshare.AccountInfo;
import java.util.Arrays;

@Class(creator = "FeatureCreator")
public class Feature extends AbstractSafeParcelable {
    public static final Creator<Feature> CREATOR = new d();
    @Field(getter = "getName", id = 1)
    private final String a;
    @Field(getter = "getOldVersion", id = 2)
    @Deprecated
    private final int b;
    @Field(defaultValue = "-1", getter = "getVersion", id = 3)
    private final long c;

    @Constructor
    public Feature(@Param(id = 1) String str, @Param(id = 2) int i, @Param(id = 3) long j) {
        this.a = str;
        this.b = i;
        this.c = j;
    }

    public final String a() {
        return this.a;
    }

    public final long b() {
        return this.c == -1 ? (long) this.b : this.c;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Feature)) {
            return false;
        }
        Feature feature = (Feature) obj;
        return ((this.a != null && this.a.equals(feature.a)) || (this.a == null && feature.a == null)) && b() == feature.b();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, Long.valueOf(b())});
    }

    public String toString() {
        return z.a(this).a("name", this.a).a(AccountInfo.VERSION_KEY, Long.valueOf(b())).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, b());
        b.a(parcel, a);
    }
}
