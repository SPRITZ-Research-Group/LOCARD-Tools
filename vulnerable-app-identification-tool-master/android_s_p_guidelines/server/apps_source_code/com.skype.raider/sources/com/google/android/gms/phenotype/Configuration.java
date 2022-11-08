package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@KeepForSdk
@Class(creator = "ConfigurationCreator")
@Reserved({1})
public class Configuration extends AbstractSafeParcelable implements Comparable<Configuration> {
    @KeepForSdk
    public static final Creator<Configuration> CREATOR = new a();
    @Field(id = 2)
    private final int a;
    @Field(id = 3)
    private final zzi[] b;
    @Field(id = 4)
    private final String[] c;
    private final Map<String, zzi> d = new TreeMap();

    @Constructor
    public Configuration(@Param(id = 2) int i, @Param(id = 3) zzi[] zziArr, @Param(id = 4) String[] strArr) {
        this.a = i;
        this.b = zziArr;
        for (zzi zzi : zziArr) {
            this.d.put(zzi.a, zzi);
        }
        this.c = strArr;
        if (this.c != null) {
            Arrays.sort(this.c);
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return this.a - ((Configuration) obj).a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) obj;
        return this.a == configuration.a && i.a(this.d, configuration.d) && Arrays.equals(this.c, configuration.c);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Configuration(");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("(");
        for (zzi append : this.d.values()) {
            stringBuilder.append(append);
            stringBuilder.append(", ");
        }
        stringBuilder.append(")");
        stringBuilder.append(", ");
        stringBuilder.append("(");
        if (this.c != null) {
            for (String append2 : this.c) {
                stringBuilder.append(append2);
                stringBuilder.append(", ");
            }
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append(")");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b, i);
        b.a(parcel, 4, this.c);
        b.a(parcel, a);
    }
}
