package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.clearcut.a.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.internal.clearcut.ea;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;

@Class(creator = "LogEventParcelableCreator")
@Reserved({1})
public final class zze extends AbstractSafeParcelable {
    public static final Creator<zze> CREATOR = new d();
    @Field(id = 2)
    public zzr a;
    @Field(id = 3)
    public byte[] b;
    public final ea c = null;
    public final a d = null;
    public final a e = null;
    @Field(id = 4)
    private int[] f;
    @Field(id = 5)
    private String[] g;
    @Field(id = 6)
    private int[] h;
    @Field(id = 7)
    private byte[][] i;
    @Field(id = 9)
    private ExperimentTokens[] j;
    @Field(defaultValue = "true", id = 8)
    private boolean k;

    @Constructor
    zze(@Param(id = 2) zzr zzr, @Param(id = 3) byte[] bArr, @Param(id = 4) int[] iArr, @Param(id = 5) String[] strArr, @Param(id = 6) int[] iArr2, @Param(id = 7) byte[][] bArr2, @Param(id = 8) boolean z, @Param(id = 9) ExperimentTokens[] experimentTokensArr) {
        this.a = zzr;
        this.b = bArr;
        this.f = iArr;
        this.g = strArr;
        this.h = iArr2;
        this.i = bArr2;
        this.j = experimentTokensArr;
        this.k = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zze = (zze) obj;
        return z.a(this.a, zze.a) && Arrays.equals(this.b, zze.b) && Arrays.equals(this.f, zze.f) && Arrays.equals(this.g, zze.g) && z.a(this.c, zze.c) && z.a(this.d, zze.d) && z.a(this.e, zze.e) && Arrays.equals(this.h, zze.h) && Arrays.deepEquals(this.i, zze.i) && Arrays.equals(this.j, zze.j) && this.k == zze.k;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.f, this.g, this.c, this.d, this.e, this.h, this.i, this.j, Boolean.valueOf(this.k)});
    }

    public final String toString() {
        return "LogEventParcelable[" + this.a + ", LogEventBytes: " + (this.b == null ? null : new String(this.b)) + ", TestCodes: " + Arrays.toString(this.f) + ", MendelPackages: " + Arrays.toString(this.g) + ", LogEvent: " + this.c + ", ExtensionProducer: " + this.d + ", VeProducer: " + this.e + ", ExperimentIDs: " + Arrays.toString(this.h) + ", ExperimentTokens: " + Arrays.toString(this.i) + ", ExperimentTokensParcelables: " + Arrays.toString(this.j) + ", AddPhenotypeExperimentTokens: " + this.k + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a, i);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.f);
        b.a(parcel, 5, this.g);
        b.a(parcel, 6, this.h);
        b.a(parcel, 7, this.i);
        b.a(parcel, 8, this.k);
        b.a(parcel, 9, this.j, i);
        b.a(parcel, a);
    }
}
