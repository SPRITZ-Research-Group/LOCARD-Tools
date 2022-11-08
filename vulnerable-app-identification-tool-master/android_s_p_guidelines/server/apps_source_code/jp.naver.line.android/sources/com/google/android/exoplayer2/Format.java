package com.google.android.exoplayer2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.video.ColorInfo;
import defpackage.bdn;
import defpackage.beg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Format implements Parcelable {
    public static final Creator<Format> CREATOR = new Creator<Format>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new Format[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new Format(parcel);
        }
    };
    public final int A;
    private int B;
    public final String a;
    public final String b;
    public final int c;
    public final String d;
    public final Metadata e;
    public final String f;
    public final String g;
    public final int h;
    public final List<byte[]> i;
    public final DrmInitData j;
    public final long k;
    public final int l;
    public final int m;
    public final float n;
    public final int o;
    public final float p;
    public final int q;
    public final byte[] r;
    public final ColorInfo s;
    public final int t;
    public final int u;
    public final int v;
    public final int w;
    public final int x;
    public final int y;
    public final String z;

    public final int describeContents() {
        return 0;
    }

    public static Format a(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, float f, int i4) {
        return new Format(str, str2, str3, str4, str5, i, -1, i2, i3, f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i4, null, -1, Long.MAX_VALUE, null, null, null);
    }

    public static Format a(String str, String str2, String str3, int i, int i2, List<byte[]> list, float f) {
        return a(str, str2, str3, i, i2, list, -1, f, null, -1, null);
    }

    public static Format a(String str, String str2, String str3, int i, int i2, List<byte[]> list, int i3, float f, byte[] bArr, int i4, DrmInitData drmInitData) {
        return new Format(str, null, null, str2, str3, -1, -1, i, i2, -1.0f, i3, f, bArr, i4, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, list, drmInitData, null);
    }

    public static Format a(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, String str6) {
        return new Format(str, str2, str3, str4, str5, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i2, -1, -1, -1, -1, i3, str6, -1, Long.MAX_VALUE, null, null, null);
    }

    public static Format a(String str, String str2, int i, int i2, int i3, int i4, List<byte[]> list, DrmInitData drmInitData, String str3) {
        return a(str, str2, i, i2, i3, i4, -1, (List) list, drmInitData, str3);
    }

    public static Format a(String str, String str2, int i, int i2, int i3, int i4, int i5, List<byte[]> list, DrmInitData drmInitData, String str3) {
        return a(str, str2, i, i2, i3, i4, i5, -1, -1, list, drmInitData, str3, null);
    }

    public static Format a(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, int i7, List<byte[]> list, DrmInitData drmInitData, String str3, Metadata metadata) {
        return new Format(str, null, null, str2, null, i, i2, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i3, i4, i5, i6, i7, 0, str3, -1, Long.MAX_VALUE, list, drmInitData, metadata);
    }

    public static Format a(String str, String str2, String str3, String str4, int i, String str5) {
        return a(str, str2, str3, str4, i, str5, -1);
    }

    public static Format a(String str, String str2, String str3, String str4, int i, String str5, int i2) {
        return new Format(str, str2, str3, str4, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i, str5, i2, Long.MAX_VALUE, null, null, null);
    }

    public static Format a(String str, String str2, long j) {
        return a(null, str, 0, str2, -1, j, Collections.emptyList());
    }

    public static Format a(String str, String str2, int i, String str3, int i2, long j, List<byte[]> list) {
        return new Format(str, null, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i, str3, i2, j, list, null, null);
    }

    public static Format a(String str, String str2, List<byte[]> list, String str3) {
        return new Format(str, null, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, str3, -1, Long.MAX_VALUE, list, null, null);
    }

    public static Format a(String str, String str2) {
        return new Format(str, null, str2, null, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, null, null, null);
    }

    public static Format a(String str, long j) {
        return new Format(null, null, null, str, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, j, null, null, null);
    }

    public static Format b(String str, String str2) {
        return new Format(str, null, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, null, null, null);
    }

    private Format(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, float f, int i5, float f2, byte[] bArr, int i6, ColorInfo colorInfo, int i7, int i8, int i9, int i10, int i11, int i12, String str6, int i13, long j, List<byte[]> list, DrmInitData drmInitData, Metadata metadata) {
        this.a = str;
        this.b = str2;
        this.f = str3;
        this.g = str4;
        this.d = str5;
        this.c = i;
        this.h = i2;
        this.l = i3;
        this.m = i4;
        this.n = f;
        int i14 = i5;
        if (i14 == -1) {
            i14 = 0;
        }
        r0.o = i14;
        r0.p = f2 == -1.0f ? 1.0f : f2;
        r0.r = bArr;
        r0.q = i6;
        r0.s = colorInfo;
        r0.t = i7;
        r0.u = i8;
        r0.v = i9;
        i14 = i10;
        if (i14 == -1) {
            i14 = 0;
        }
        r0.w = i14;
        i14 = i11;
        if (i14 == -1) {
            i14 = 0;
        }
        r0.x = i14;
        r0.y = i12;
        r0.z = str6;
        r0.A = i13;
        r0.k = j;
        r0.i = list == null ? Collections.emptyList() : list;
        r0.j = drmInitData;
        r0.e = metadata;
    }

    Format(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.d = parcel.readString();
        this.c = parcel.readInt();
        this.h = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readFloat();
        this.o = parcel.readInt();
        this.p = parcel.readFloat();
        this.r = beg.a(parcel) ? parcel.createByteArray() : null;
        this.q = parcel.readInt();
        this.s = (ColorInfo) parcel.readParcelable(ColorInfo.class.getClassLoader());
        this.t = parcel.readInt();
        this.u = parcel.readInt();
        this.v = parcel.readInt();
        this.w = parcel.readInt();
        this.x = parcel.readInt();
        this.y = parcel.readInt();
        this.z = parcel.readString();
        this.A = parcel.readInt();
        this.k = parcel.readLong();
        int readInt = parcel.readInt();
        this.i = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            this.i.add(parcel.createByteArray());
        }
        this.j = (DrmInitData) parcel.readParcelable(DrmInitData.class.getClassLoader());
        this.e = (Metadata) parcel.readParcelable(Metadata.class.getClassLoader());
    }

    public final Format a(long j) {
        return new Format(this.a, this.b, this.f, this.g, this.d, this.c, this.h, this.l, this.m, this.n, this.o, this.p, this.r, this.q, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, j, this.i, this.j, this.e);
    }

    public final Format a(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, String str5) {
        return new Format(str, str2, this.f, str3, str4, i, this.h, i2, i3, this.n, this.o, this.p, this.r, this.q, this.s, this.t, this.u, this.v, this.w, this.x, i4, str5, this.A, this.k, this.i, this.j, this.e);
    }

    public final Format a(Format format) {
        Format format2 = format;
        if (this == format2) {
            return r0;
        }
        String str;
        float f;
        float f2;
        Format format3;
        Format format4;
        int g = bdn.g(r0.g);
        String str2 = format2.a;
        String str3 = format2.b != null ? format2.b : r0.b;
        String str4 = r0.z;
        if ((g == 3 || g == 1) && format2.z != null) {
            str4 = format2.z;
        }
        String str5 = str4;
        int i = r0.c == -1 ? format2.c : r0.c;
        str4 = r0.d;
        if (str4 == null) {
            String a = beg.a(format2.d, g);
            if (beg.i(a).length == 1) {
                str = a;
                f = r0.n;
                f2 = (f == -1.0f || g != 2) ? f : format2.n;
                format3 = format4;
                format4 = new Format(str2, str3, r0.f, r0.g, str, i, r0.h, r0.l, r0.m, f2, r0.o, r0.p, r0.r, r0.q, r0.s, r0.t, r0.u, r0.v, r0.w, r0.x, r0.y | format2.y, str5, r0.A, r0.k, r0.i, DrmInitData.a(format2.j, r0.j), r0.e);
                return format3;
            }
        }
        str = str4;
        f = r0.n;
        if (f == -1.0f) {
        }
        format3 = format4;
        format4 = new Format(str2, str3, r0.f, r0.g, str, i, r0.h, r0.l, r0.m, f2, r0.o, r0.p, r0.r, r0.q, r0.s, r0.t, r0.u, r0.v, r0.w, r0.x, r0.y | format2.y, str5, r0.A, r0.k, r0.i, DrmInitData.a(format2.j, r0.j), r0.e);
        return format3;
    }

    public final Format a(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        return new Format(this.a, this.b, this.f, this.g, this.d, this.c, this.h, this.l, this.m, this.n, this.o, this.p, this.r, this.q, this.s, this.t, this.u, this.v, i3, i4, this.y, this.z, this.A, this.k, this.i, this.j, this.e);
    }

    public final Format a(DrmInitData drmInitData) {
        DrmInitData drmInitData2 = drmInitData;
        return new Format(this.a, this.b, this.f, this.g, this.d, this.c, this.h, this.l, this.m, this.n, this.o, this.p, this.r, this.q, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.k, this.i, drmInitData2, this.e);
    }

    public final Format a(Metadata metadata) {
        Metadata metadata2 = metadata;
        return new Format(this.a, this.b, this.f, this.g, this.d, this.c, this.h, this.l, this.m, this.n, this.o, this.p, this.r, this.q, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.k, this.i, this.j, metadata2);
    }

    public final int a() {
        return (this.l == -1 || this.m == -1) ? -1 : this.l * this.m;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("Format(");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append(this.f);
        stringBuilder.append(", ");
        stringBuilder.append(this.g);
        stringBuilder.append(", ");
        stringBuilder.append(this.d);
        stringBuilder.append(", ");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append(this.z);
        stringBuilder.append(", [");
        stringBuilder.append(this.l);
        stringBuilder.append(", ");
        stringBuilder.append(this.m);
        stringBuilder.append(", ");
        stringBuilder.append(this.n);
        stringBuilder.append("], [");
        stringBuilder.append(this.t);
        stringBuilder.append(", ");
        stringBuilder.append(this.u);
        stringBuilder.append("])");
        return stringBuilder.toString();
    }

    public final int hashCode() {
        if (this.B == 0) {
            int i = 0;
            int hashCode = ((((((((((((((((((((((((((this.a == null ? 0 : this.a.hashCode()) + 527) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.g == null ? 0 : this.g.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + this.c) * 31) + this.l) * 31) + this.m) * 31) + this.t) * 31) + this.u) * 31) + (this.z == null ? 0 : this.z.hashCode())) * 31) + this.A) * 31) + (this.j == null ? 0 : this.j.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31;
            if (this.b != null) {
                i = this.b.hashCode();
            }
            this.B = ((((((((((((((((((((hashCode + i) * 31) + this.h) * 31) + ((int) this.k)) * 31) + Float.floatToIntBits(this.n)) * 31) + Float.floatToIntBits(this.p)) * 31) + this.o) * 31) + this.q) * 31) + this.v) * 31) + this.w) * 31) + this.x) * 31) + this.y;
        }
        return this.B;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Format format = (Format) obj;
        return (this.B == 0 || format.B == 0 || this.B == format.B) && this.c == format.c && this.h == format.h && this.l == format.l && this.m == format.m && Float.compare(this.n, format.n) == 0 && this.o == format.o && Float.compare(this.p, format.p) == 0 && this.q == format.q && this.t == format.t && this.u == format.u && this.v == format.v && this.w == format.w && this.x == format.x && this.k == format.k && this.y == format.y && beg.a(this.a, format.a) && beg.a(this.b, format.b) && beg.a(this.z, format.z) && this.A == format.A && beg.a(this.f, format.f) && beg.a(this.g, format.g) && beg.a(this.d, format.d) && beg.a(this.j, format.j) && beg.a(this.e, format.e) && beg.a(this.s, format.s) && Arrays.equals(this.r, format.r) && b(format);
    }

    public final boolean b(Format format) {
        if (this.i.size() != format.i.size()) {
            return false;
        }
        for (int i = 0; i < this.i.size(); i++) {
            if (!Arrays.equals((byte[]) this.i.get(i), (byte[]) format.i.get(i))) {
                return false;
            }
        }
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.d);
        parcel.writeInt(this.c);
        parcel.writeInt(this.h);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeFloat(this.n);
        parcel.writeInt(this.o);
        parcel.writeFloat(this.p);
        beg.a(parcel, this.r != null);
        if (this.r != null) {
            parcel.writeByteArray(this.r);
        }
        parcel.writeInt(this.q);
        parcel.writeParcelable(this.s, i);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w);
        parcel.writeInt(this.x);
        parcel.writeInt(this.y);
        parcel.writeString(this.z);
        parcel.writeInt(this.A);
        parcel.writeLong(this.k);
        i = this.i.size();
        parcel.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            parcel.writeByteArray((byte[]) this.i.get(i2));
        }
        parcel.writeParcelable(this.j, 0);
        parcel.writeParcelable(this.e, 0);
    }

    public static Format a(String str) {
        return a(null, str, 0, null, -1, Long.MAX_VALUE, Collections.emptyList());
    }
}
