package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.c;
import defpackage.bcz;
import defpackage.beg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class DrmInitData implements Parcelable, Comparator<SchemeData> {
    public static final Creator<DrmInitData> CREATOR = new Creator<DrmInitData>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new DrmInitData[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new DrmInitData(parcel);
        }
    };
    public final String a;
    public final int b;
    private final SchemeData[] c;
    private int d;

    public final class SchemeData implements Parcelable {
        public static final Creator<SchemeData> CREATOR = new Creator<SchemeData>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SchemeData[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }
        };
        public final String a;
        public final String b;
        public final byte[] c;
        public final boolean d;
        private int e;
        private final UUID f;

        public final int describeContents() {
            return 0;
        }

        public SchemeData(UUID uuid, String str, byte[] bArr) {
            this(uuid, str, bArr, (byte) 0);
        }

        private SchemeData(UUID uuid, String str, byte[] bArr, byte b) {
            this(uuid, null, str, bArr, false);
        }

        private SchemeData(UUID uuid, String str, String str2, byte[] bArr, boolean z) {
            this.f = (UUID) bcz.a((Object) uuid);
            this.a = str;
            this.b = (String) bcz.a((Object) str2);
            this.c = bArr;
            this.d = z;
        }

        SchemeData(Parcel parcel) {
            this.f = new UUID(parcel.readLong(), parcel.readLong());
            this.a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.createByteArray();
            this.d = parcel.readByte() != (byte) 0;
        }

        public final boolean a() {
            return this.c != null;
        }

        public final SchemeData b() {
            return new SchemeData(this.f, this.a, this.b, null, this.d);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeData schemeData = (SchemeData) obj;
            return beg.a(this.a, schemeData.a) && beg.a(this.b, schemeData.b) && beg.a(this.f, schemeData.f) && Arrays.equals(this.c, schemeData.c);
        }

        public final int hashCode() {
            if (this.e == 0) {
                this.e = (((((this.f.hashCode() * 31) + (this.a == null ? 0 : this.a.hashCode())) * 31) + this.b.hashCode()) * 31) + Arrays.hashCode(this.c);
            }
            return this.e;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f.getMostSignificantBits());
            parcel.writeLong(this.f.getLeastSignificantBits());
            parcel.writeString(this.a);
            parcel.writeString(this.b);
            parcel.writeByteArray(this.c);
            parcel.writeByte((byte) this.d);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        SchemeData schemeData = (SchemeData) obj;
        SchemeData schemeData2 = (SchemeData) obj2;
        if (c.a.equals(schemeData.f)) {
            return c.a.equals(schemeData2.f) ? 0 : 1;
        } else {
            return schemeData.f.compareTo(schemeData2.f);
        }
    }

    public static DrmInitData a(DrmInitData drmInitData, DrmInitData drmInitData2) {
        String str;
        List arrayList = new ArrayList();
        if (drmInitData != null) {
            str = drmInitData.a;
            for (SchemeData schemeData : drmInitData.c) {
                if (schemeData.a()) {
                    arrayList.add(schemeData);
                }
            }
        } else {
            str = null;
        }
        if (drmInitData2 != null) {
            if (str == null) {
                str = drmInitData2.a;
            }
            int size = arrayList.size();
            for (SchemeData schemeData2 : drmInitData2.c) {
                if (schemeData2.a() && !a(arrayList, size, schemeData2.f)) {
                    arrayList.add(schemeData2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new DrmInitData(str, arrayList);
    }

    public DrmInitData(List<SchemeData> list) {
        this(null, false, (SchemeData[]) list.toArray(new SchemeData[list.size()]));
    }

    private DrmInitData(String str, List<SchemeData> list) {
        this(str, false, (SchemeData[]) list.toArray(new SchemeData[list.size()]));
    }

    public DrmInitData(String str, SchemeData... schemeDataArr) {
        this(str, true, schemeDataArr);
    }

    private DrmInitData(String str, boolean z, SchemeData... schemeDataArr) {
        this.a = str;
        if (z) {
            schemeDataArr = (SchemeData[]) schemeDataArr.clone();
        }
        Arrays.sort(schemeDataArr, this);
        this.c = schemeDataArr;
        this.b = schemeDataArr.length;
    }

    DrmInitData(Parcel parcel) {
        this.a = parcel.readString();
        this.c = (SchemeData[]) parcel.createTypedArray(SchemeData.CREATOR);
        this.b = this.c.length;
    }

    public final SchemeData a(int i) {
        return this.c[i];
    }

    public final DrmInitData a(String str) {
        if (beg.a(this.a, (Object) str)) {
            return this;
        }
        return new DrmInitData(str, false, this.c);
    }

    public final int hashCode() {
        if (this.d == 0) {
            this.d = ((this.a == null ? 0 : this.a.hashCode()) * 31) + Arrays.hashCode(this.c);
        }
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DrmInitData drmInitData = (DrmInitData) obj;
        return beg.a(this.a, drmInitData.a) && Arrays.equals(this.c, drmInitData.c);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeTypedArray(this.c, 0);
    }

    private static boolean a(ArrayList<SchemeData> arrayList, int i, UUID uuid) {
        for (int i2 = 0; i2 < i; i2++) {
            if (((SchemeData) arrayList.get(i2)).f.equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
