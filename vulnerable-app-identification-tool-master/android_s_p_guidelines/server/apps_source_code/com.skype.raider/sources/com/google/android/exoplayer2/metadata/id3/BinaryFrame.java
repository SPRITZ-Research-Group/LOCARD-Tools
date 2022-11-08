package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class BinaryFrame extends Id3Frame {
    public static final Creator<BinaryFrame> CREATOR = new Creator<BinaryFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new BinaryFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new BinaryFrame(parcel);
        }
    };
    public final byte[] a;

    public BinaryFrame(String id, byte[] data) {
        super(id);
        this.a = data;
    }

    BinaryFrame(Parcel in) {
        super(in.readString());
        this.a = in.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BinaryFrame other = (BinaryFrame) obj;
        if (this.f.equals(other.f) && Arrays.equals(this.a, other.a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f.hashCode() + 527) * 31) + Arrays.hashCode(this.a);
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f);
        dest.writeByteArray(this.a);
    }
}
