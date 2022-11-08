package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Arrays;
import java.util.Comparator;

@Class(creator = "FlagCreator")
@Reserved({1})
public final class zzi extends AbstractSafeParcelable implements Comparable<zzi> {
    public static final Creator<zzi> CREATOR = new h();
    private static final Comparator<zzi> i = new g();
    @Field(id = 2)
    public final String a;
    @Field(id = 9)
    public final int b;
    @Field(id = 3)
    private final long c;
    @Field(id = 4)
    private final boolean d;
    @Field(id = 5)
    private final double e;
    @Field(id = 6)
    private final String f;
    @Field(id = 7)
    private final byte[] g;
    @Field(id = 8)
    private final int h;

    @Constructor
    public zzi(@Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) boolean z, @Param(id = 5) double d, @Param(id = 6) String str2, @Param(id = 7) byte[] bArr, @Param(id = 8) int i, @Param(id = 9) int i2) {
        this.a = str;
        this.c = j;
        this.d = z;
        this.e = d;
        this.f = str2;
        this.g = bArr;
        this.h = i;
        this.b = i2;
    }

    private static int a(int i, int i2) {
        return i < i2 ? -1 : i == i2 ? 0 : 1;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        int i = 0;
        zzi zzi = (zzi) obj;
        int compareTo = this.a.compareTo(zzi.a);
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = a(this.h, zzi.h);
        if (compareTo != 0) {
            return compareTo;
        }
        switch (this.h) {
            case 1:
                long j = this.c;
                long j2 = zzi.c;
                return j < j2 ? -1 : j != j2 ? 1 : 0;
            case 2:
                boolean z = this.d;
                return z != zzi.d ? z ? 1 : -1 : 0;
            case 3:
                return Double.compare(this.e, zzi.e);
            case 4:
                String str = this.f;
                String str2 = zzi.f;
                return str != str2 ? str == null ? -1 : str2 == null ? 1 : str.compareTo(str2) : 0;
            case 5:
                if (this.g == zzi.g) {
                    return 0;
                }
                if (this.g == null) {
                    return -1;
                }
                if (zzi.g == null) {
                    return 1;
                }
                while (i < Math.min(this.g.length, zzi.g.length)) {
                    compareTo = this.g[i] - zzi.g[i];
                    if (compareTo != 0) {
                        return compareTo;
                    }
                    i++;
                }
                return a(this.g.length, zzi.g.length);
            default:
                throw new AssertionError("Invalid enum value: " + this.h);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        if (!i.a(this.a, zzi.a) || this.h != zzi.h || this.b != zzi.b) {
            return false;
        }
        switch (this.h) {
            case 1:
                return this.c == zzi.c;
            case 2:
                return this.d == zzi.d;
            case 3:
                return this.e == zzi.e;
            case 4:
                return i.a(this.f, zzi.f);
            case 5:
                return Arrays.equals(this.g, zzi.g);
            default:
                throw new AssertionError("Invalid enum value: " + this.h);
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Flag(");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        switch (this.h) {
            case 1:
                stringBuilder.append(this.c);
                break;
            case 2:
                stringBuilder.append(this.d);
                break;
            case 3:
                stringBuilder.append(this.e);
                break;
            case 4:
                stringBuilder.append("'");
                stringBuilder.append(this.f);
                stringBuilder.append("'");
                break;
            case 5:
                if (this.g != null) {
                    stringBuilder.append("'");
                    stringBuilder.append(Base64.encodeToString(this.g, 3));
                    stringBuilder.append("'");
                    break;
                }
                stringBuilder.append("null");
                break;
            default:
                String str = this.a;
                throw new AssertionError(new StringBuilder(String.valueOf(str).length() + 27).append("Invalid type: ").append(str).append(", ").append(this.h).toString());
        }
        stringBuilder.append(", ");
        stringBuilder.append(this.h);
        stringBuilder.append(", ");
        stringBuilder.append(this.b);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.c);
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.e);
        b.a(parcel, 6, this.f);
        b.a(parcel, 7, this.g);
        b.a(parcel, 8, this.h);
        b.a(parcel, 9, this.b);
        b.a(parcel, a);
    }
}
