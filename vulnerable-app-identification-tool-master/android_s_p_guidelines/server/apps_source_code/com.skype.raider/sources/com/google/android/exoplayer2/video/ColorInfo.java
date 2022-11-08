package com.google.android.exoplayer2.video;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class ColorInfo implements Parcelable {
    public static final Creator<ColorInfo> CREATOR = new Creator<ColorInfo>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ColorInfo[0];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ColorInfo(parcel);
        }
    };
    public final int a;
    public final int b;
    public final int c;
    public final byte[] d;
    private int e;

    public ColorInfo(int colorSpace, int colorRange, int colorTransfer, byte[] hdrStaticInfo) {
        this.a = colorSpace;
        this.b = colorRange;
        this.c = colorTransfer;
        this.d = hdrStaticInfo;
    }

    ColorInfo(Parcel in) {
        this.a = in.readInt();
        this.b = in.readInt();
        this.c = in.readInt();
        this.d = in.readInt() != 0 ? in.createByteArray() : null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorInfo other = (ColorInfo) obj;
        if (this.a == other.a && this.b == other.b && this.c == other.c && Arrays.equals(this.d, other.d)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "ColorInfo(" + this.a + ", " + this.b + ", " + this.c + ", " + (this.d != null) + ")";
    }

    public final int hashCode() {
        if (this.e == 0) {
            this.e = ((((((this.a + 527) * 31) + this.b) * 31) + this.c) * 31) + Arrays.hashCode(this.d);
        }
        return this.e;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.a);
        dest.writeInt(this.b);
        dest.writeInt(this.c);
        dest.writeInt(this.d != null ? 1 : 0);
        if (this.d != null) {
            dest.writeByteArray(this.d);
        }
    }
}
