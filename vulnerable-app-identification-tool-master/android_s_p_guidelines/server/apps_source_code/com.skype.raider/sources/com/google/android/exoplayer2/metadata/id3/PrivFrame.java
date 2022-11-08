package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;
import java.util.Arrays;

public final class PrivFrame extends Id3Frame {
    public static final Creator<PrivFrame> CREATOR = new Creator<PrivFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PrivFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PrivFrame(parcel);
        }
    };
    public final String a;
    public final byte[] b;

    public PrivFrame(String owner, byte[] privateData) {
        super("PRIV");
        this.a = owner;
        this.b = privateData;
    }

    PrivFrame(Parcel in) {
        super("PRIV");
        this.a = in.readString();
        this.b = in.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrivFrame other = (PrivFrame) obj;
        if (s.a(this.a, other.a) && Arrays.equals(this.b, other.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((this.a != null ? this.a.hashCode() : 0) + 527) * 31) + Arrays.hashCode(this.b);
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeByteArray(this.b);
    }
}
