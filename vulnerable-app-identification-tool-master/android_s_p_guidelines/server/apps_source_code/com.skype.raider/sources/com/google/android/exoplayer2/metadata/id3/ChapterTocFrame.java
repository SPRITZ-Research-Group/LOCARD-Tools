package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;
import java.util.Arrays;

public final class ChapterTocFrame extends Id3Frame {
    public static final Creator<ChapterTocFrame> CREATOR = new Creator<ChapterTocFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ChapterTocFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ChapterTocFrame(parcel);
        }
    };
    public final String a;
    public final boolean b;
    public final boolean c;
    public final String[] d;
    private final Id3Frame[] e;

    public ChapterTocFrame(String elementId, boolean isRoot, boolean isOrdered, String[] children, Id3Frame[] subFrames) {
        super("CTOC");
        this.a = elementId;
        this.b = isRoot;
        this.c = isOrdered;
        this.d = children;
        this.e = subFrames;
    }

    ChapterTocFrame(Parcel in) {
        boolean z;
        boolean z2 = true;
        super("CTOC");
        this.a = in.readString();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.b = z;
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.c = z2;
        this.d = in.createStringArray();
        int subFrameCount = in.readInt();
        this.e = new Id3Frame[subFrameCount];
        for (int i = 0; i < subFrameCount; i++) {
            this.e[i] = (Id3Frame) in.readParcelable(Id3Frame.class.getClassLoader());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChapterTocFrame other = (ChapterTocFrame) obj;
        if (this.b == other.b && this.c == other.c && s.a(this.a, other.a) && Arrays.equals(this.d, other.d) && Arrays.equals(this.e, other.e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        if (this.b) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + 527) * 31;
        if (!this.c) {
            i2 = 0;
        }
        i = (i + i2) * 31;
        if (this.a != null) {
            i3 = this.a.hashCode();
        }
        return i + i3;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeString(this.a);
        if (this.b) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (!this.c) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
        dest.writeStringArray(this.d);
        dest.writeInt(this.e.length);
        for (Parcelable writeParcelable : this.e) {
            dest.writeParcelable(writeParcelable, 0);
        }
    }
}
