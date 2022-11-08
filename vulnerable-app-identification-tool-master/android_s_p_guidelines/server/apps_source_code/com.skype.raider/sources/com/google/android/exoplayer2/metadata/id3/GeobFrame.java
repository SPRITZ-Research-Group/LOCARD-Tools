package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;
import java.util.Arrays;

public final class GeobFrame extends Id3Frame {
    public static final Creator<GeobFrame> CREATOR = new Creator<GeobFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new GeobFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new GeobFrame(parcel);
        }
    };
    public final String a;
    public final String b;
    public final String c;
    public final byte[] d;

    public GeobFrame(String mimeType, String filename, String description, byte[] data) {
        super("GEOB");
        this.a = mimeType;
        this.b = filename;
        this.c = description;
        this.d = data;
    }

    GeobFrame(Parcel in) {
        super("GEOB");
        this.a = in.readString();
        this.b = in.readString();
        this.c = in.readString();
        this.d = in.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeobFrame other = (GeobFrame) obj;
        if (s.a(this.a, other.a) && s.a(this.b, other.b) && s.a(this.c, other.c) && Arrays.equals(this.d, other.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int i = 0;
        if (this.a != null) {
            hashCode = this.a.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = (hashCode + 527) * 31;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return ((hashCode + i) * 31) + Arrays.hashCode(this.d);
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeString(this.b);
        dest.writeString(this.c);
        dest.writeByteArray(this.d);
    }
}
