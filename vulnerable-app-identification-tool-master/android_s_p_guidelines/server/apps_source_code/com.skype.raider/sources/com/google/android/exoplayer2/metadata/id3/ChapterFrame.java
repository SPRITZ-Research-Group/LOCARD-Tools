package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;
import java.util.Arrays;

public final class ChapterFrame extends Id3Frame {
    public static final Creator<ChapterFrame> CREATOR = new Creator<ChapterFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ChapterFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ChapterFrame(parcel);
        }
    };
    public final String a;
    public final int b;
    public final int c;
    public final long d;
    public final long e;
    private final Id3Frame[] g;

    public ChapterFrame(String chapterId, int startTimeMs, int endTimeMs, long startOffset, long endOffset, Id3Frame[] subFrames) {
        super("CHAP");
        this.a = chapterId;
        this.b = startTimeMs;
        this.c = endTimeMs;
        this.d = startOffset;
        this.e = endOffset;
        this.g = subFrames;
    }

    ChapterFrame(Parcel in) {
        super("CHAP");
        this.a = in.readString();
        this.b = in.readInt();
        this.c = in.readInt();
        this.d = in.readLong();
        this.e = in.readLong();
        int subFrameCount = in.readInt();
        this.g = new Id3Frame[subFrameCount];
        for (int i = 0; i < subFrameCount; i++) {
            this.g[i] = (Id3Frame) in.readParcelable(Id3Frame.class.getClassLoader());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChapterFrame other = (ChapterFrame) obj;
        if (this.b == other.b && this.c == other.c && this.d == other.d && this.e == other.e && s.a(this.a, other.a) && Arrays.equals(this.g, other.g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.a != null ? this.a.hashCode() : 0) + ((((((((this.b + 527) * 31) + this.c) * 31) + ((int) this.d)) * 31) + ((int) this.e)) * 31);
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeInt(this.b);
        dest.writeInt(this.c);
        dest.writeLong(this.d);
        dest.writeLong(this.e);
        dest.writeInt(this.g.length);
        for (Id3Frame subFrame : this.g) {
            dest.writeParcelable(subFrame, 0);
        }
    }

    public final int describeContents() {
        return 0;
    }
}
