package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;
import java.util.Arrays;

public final class ApicFrame extends Id3Frame {
    public static final Creator<ApicFrame> CREATOR = new Creator<ApicFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ApicFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ApicFrame(parcel);
        }
    };
    public final String a;
    public final String b;
    public final int c;
    public final byte[] d;

    public ApicFrame(String mimeType, String description, int pictureType, byte[] pictureData) {
        super("APIC");
        this.a = mimeType;
        this.b = description;
        this.c = pictureType;
        this.d = pictureData;
    }

    ApicFrame(Parcel in) {
        super("APIC");
        this.a = in.readString();
        this.b = in.readString();
        this.c = in.readInt();
        this.d = in.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ApicFrame other = (ApicFrame) obj;
        if (this.c == other.c && s.a(this.a, other.a) && s.a(this.b, other.b) && Arrays.equals(this.d, other.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int i = 0;
        int i2 = (this.c + 527) * 31;
        if (this.a != null) {
            hashCode = this.a.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return ((hashCode + i) * 31) + Arrays.hashCode(this.d);
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeString(this.b);
        dest.writeInt(this.c);
        dest.writeByteArray(this.d);
    }
}
