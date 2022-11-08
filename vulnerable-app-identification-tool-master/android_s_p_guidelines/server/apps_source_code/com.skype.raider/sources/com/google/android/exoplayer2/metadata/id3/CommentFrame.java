package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;

public final class CommentFrame extends Id3Frame {
    public static final Creator<CommentFrame> CREATOR = new Creator<CommentFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CommentFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CommentFrame(parcel);
        }
    };
    public final String a;
    public final String b;
    public final String c;

    public CommentFrame(String language, String description, String text) {
        super("COMM");
        this.a = language;
        this.b = description;
        this.c = text;
    }

    CommentFrame(Parcel in) {
        super("COMM");
        this.a = in.readString();
        this.b = in.readString();
        this.c = in.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CommentFrame other = (CommentFrame) obj;
        if (s.a(this.b, other.b) && s.a(this.a, other.a) && s.a(this.c, other.c)) {
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
        return hashCode + i;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f);
        dest.writeString(this.a);
        dest.writeString(this.c);
    }
}
