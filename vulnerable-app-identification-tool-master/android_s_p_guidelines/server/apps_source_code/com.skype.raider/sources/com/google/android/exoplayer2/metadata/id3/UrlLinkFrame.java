package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;

public final class UrlLinkFrame extends Id3Frame {
    public static final Creator<UrlLinkFrame> CREATOR = new Creator<UrlLinkFrame>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new UrlLinkFrame[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new UrlLinkFrame(parcel);
        }
    };
    public final String a;
    public final String b;

    public UrlLinkFrame(String id, String description, String url) {
        super(id);
        this.a = description;
        this.b = url;
    }

    UrlLinkFrame(Parcel in) {
        super(in.readString());
        this.a = in.readString();
        this.b = in.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UrlLinkFrame other = (UrlLinkFrame) obj;
        if (this.f.equals(other.f) && s.a(this.a, other.a) && s.a(this.b, other.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.f.hashCode() + 527) * 31;
        if (this.a != null) {
            hashCode = this.a.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode2 + hashCode) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f);
        dest.writeString(this.a);
        dest.writeString(this.b);
    }
}
