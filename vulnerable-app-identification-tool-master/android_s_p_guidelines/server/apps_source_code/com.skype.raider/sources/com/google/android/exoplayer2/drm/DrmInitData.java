package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.s;
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
    public final int a;
    private final SchemeData[] b;
    private int c;

    public static final class SchemeData implements Parcelable {
        public static final Creator<SchemeData> CREATOR = new Creator<SchemeData>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SchemeData[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }
        };
        public final String a;
        public final byte[] b;
        public final boolean c;
        private int d;
        private final UUID e;

        public SchemeData(UUID uuid, String mimeType, byte[] data) {
            this(uuid, mimeType, data, (byte) 0);
        }

        private SchemeData(UUID uuid, String mimeType, byte[] data, byte b) {
            this.e = (UUID) a.a((Object) uuid);
            this.a = (String) a.a((Object) mimeType);
            this.b = (byte[]) a.a((Object) data);
            this.c = false;
        }

        SchemeData(Parcel in) {
            this.e = new UUID(in.readLong(), in.readLong());
            this.a = in.readString();
            this.b = in.createByteArray();
            this.c = in.readByte() != (byte) 0;
        }

        public final boolean a(UUID schemeUuid) {
            return C.b.equals(this.e) || schemeUuid.equals(this.e);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeData other = (SchemeData) obj;
            if (this.a.equals(other.a) && s.a(this.e, other.e) && Arrays.equals(this.b, other.b)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.d == 0) {
                this.d = (((this.e.hashCode() * 31) + this.a.hashCode()) * 31) + Arrays.hashCode(this.b);
            }
            return this.d;
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.e.getMostSignificantBits());
            dest.writeLong(this.e.getLeastSignificantBits());
            dest.writeString(this.a);
            dest.writeByteArray(this.b);
            dest.writeByte((byte) (this.c ? 1 : 0));
        }
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        SchemeData schemeData = (SchemeData) obj;
        SchemeData schemeData2 = (SchemeData) obj2;
        if (C.b.equals(schemeData.e)) {
            return C.b.equals(schemeData2.e) ? 0 : 1;
        } else {
            return schemeData.e.compareTo(schemeData2.e);
        }
    }

    public DrmInitData(List<SchemeData> schemeDatas) {
        this(false, (SchemeData[]) schemeDatas.toArray(new SchemeData[schemeDatas.size()]));
    }

    public DrmInitData(SchemeData... schemeDatas) {
        this(true, schemeDatas);
    }

    private DrmInitData(boolean cloneSchemeDatas, SchemeData... schemeDatas) {
        if (cloneSchemeDatas) {
            schemeDatas = (SchemeData[]) schemeDatas.clone();
        }
        Arrays.sort(schemeDatas, this);
        for (int i = 1; i < schemeDatas.length; i++) {
            if (schemeDatas[i - 1].e.equals(schemeDatas[i].e)) {
                throw new IllegalArgumentException("Duplicate data for uuid: " + schemeDatas[i].e);
            }
        }
        this.b = schemeDatas;
        this.a = schemeDatas.length;
    }

    DrmInitData(Parcel in) {
        this.b = (SchemeData[]) in.createTypedArray(SchemeData.CREATOR);
        this.a = this.b.length;
    }

    public final SchemeData a(UUID uuid) {
        for (SchemeData schemeData : this.b) {
            if (schemeData.a(uuid)) {
                return schemeData;
            }
        }
        return null;
    }

    public final SchemeData a(int index) {
        return this.b[index];
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b);
        }
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.b, ((DrmInitData) obj).b);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.b, 0);
    }
}
