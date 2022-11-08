package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@KeepForSdk
@Class(creator = "ExperimentTokensCreator")
@Reserved({1})
public class ExperimentTokens extends AbstractSafeParcelable {
    @KeepForSdk
    public static final Creator<ExperimentTokens> CREATOR = new f();
    private static final byte[][] a = new byte[0][];
    private static final ExperimentTokens b = new ExperimentTokens("", null, a, a, a, a, null, null);
    private static final a k = new b();
    private static final a l = new c();
    private static final a m = new d();
    private static final a n = new e();
    @Field(id = 2)
    private final String c;
    @Field(id = 3)
    private final byte[] d;
    @Field(id = 4)
    private final byte[][] e;
    @Field(id = 5)
    private final byte[][] f;
    @Field(id = 6)
    private final byte[][] g;
    @Field(id = 7)
    private final byte[][] h;
    @Field(id = 8)
    private final int[] i;
    @Field(id = 9)
    private final byte[][] j;

    private interface a {
    }

    @Constructor
    public ExperimentTokens(@Param(id = 2) String str, @Param(id = 3) byte[] bArr, @Param(id = 4) byte[][] bArr2, @Param(id = 5) byte[][] bArr3, @Param(id = 6) byte[][] bArr4, @Param(id = 7) byte[][] bArr5, @Param(id = 8) int[] iArr, @Param(id = 9) byte[][] bArr6) {
        this.c = str;
        this.d = bArr;
        this.e = bArr2;
        this.f = bArr3;
        this.g = bArr4;
        this.h = bArr5;
        this.i = iArr;
        this.j = bArr6;
    }

    private static List<Integer> a(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        List<Integer> arrayList = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List<String> a(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        List<String> arrayList = new ArrayList(bArr.length);
        for (byte[] encodeToString : bArr) {
            arrayList.add(Base64.encodeToString(encodeToString, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static void a(StringBuilder stringBuilder, String str, byte[][] bArr) {
        stringBuilder.append(str);
        stringBuilder.append("=");
        if (bArr == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append("(");
        int length = bArr.length;
        Object obj = 1;
        int i = 0;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("'");
            stringBuilder.append(Base64.encodeToString(bArr2, 3));
            stringBuilder.append("'");
            i++;
            obj = null;
        }
        stringBuilder.append(")");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExperimentTokens)) {
            return false;
        }
        ExperimentTokens experimentTokens = (ExperimentTokens) obj;
        return i.a(this.c, experimentTokens.c) && Arrays.equals(this.d, experimentTokens.d) && i.a(a(this.e), a(experimentTokens.e)) && i.a(a(this.f), a(experimentTokens.f)) && i.a(a(this.g), a(experimentTokens.g)) && i.a(a(this.h), a(experimentTokens.h)) && i.a(a(this.i), a(experimentTokens.i)) && i.a(a(this.j), a(experimentTokens.j));
    }

    public String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder("ExperimentTokens");
        stringBuilder.append("(");
        if (this.c == null) {
            str = "null";
        } else {
            str = this.c;
            str = new StringBuilder(String.valueOf(str).length() + 2).append("'").append(str).append("'").toString();
        }
        stringBuilder.append(str);
        stringBuilder.append(", ");
        byte[] bArr = this.d;
        stringBuilder.append("direct");
        stringBuilder.append("=");
        if (bArr == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append("'");
            stringBuilder.append(Base64.encodeToString(bArr, 3));
            stringBuilder.append("'");
        }
        stringBuilder.append(", ");
        a(stringBuilder, "GAIA", this.e);
        stringBuilder.append(", ");
        a(stringBuilder, "PSEUDO", this.f);
        stringBuilder.append(", ");
        a(stringBuilder, "ALWAYS", this.g);
        stringBuilder.append(", ");
        a(stringBuilder, "OTHER", this.h);
        stringBuilder.append(", ");
        int[] iArr = this.i;
        stringBuilder.append("weak");
        stringBuilder.append("=");
        if (iArr == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append("(");
            int length = iArr.length;
            Object obj = 1;
            int i = 0;
            while (i < length) {
                int i2 = iArr[i];
                if (obj == null) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(i2);
                i++;
                obj = null;
            }
            stringBuilder.append(")");
        }
        stringBuilder.append(", ");
        a(stringBuilder, "directs", this.j);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.c);
        b.a(parcel, 3, this.d);
        b.a(parcel, 4, this.e);
        b.a(parcel, 5, this.f);
        b.a(parcel, 6, this.g);
        b.a(parcel, 7, this.h);
        b.a(parcel, 8, this.i);
        b.a(parcel, 9, this.j);
        b.a(parcel, a);
    }
}
