package com.google.android.exoplayer2.source;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.Format;
import defpackage.bcz;
import java.util.Arrays;

public final class TrackGroup implements Parcelable {
    public static final Creator<TrackGroup> CREATOR = new Creator<TrackGroup>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new TrackGroup[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new TrackGroup(parcel);
        }
    };
    public final int a;
    private final Format[] b;
    private int c;

    public final int describeContents() {
        return 0;
    }

    public TrackGroup(Format... formatArr) {
        bcz.b(formatArr.length > 0);
        this.b = formatArr;
        this.a = formatArr.length;
    }

    TrackGroup(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = new Format[this.a];
        for (int i = 0; i < this.a; i++) {
            this.b[i] = (Format) parcel.readParcelable(Format.class.getClassLoader());
        }
    }

    public final Format a(int i) {
        return this.b[i];
    }

    public final int a(Format format) {
        for (int i = 0; i < this.b.length; i++) {
            if (format == this.b[i]) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b) + 527;
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
        TrackGroup trackGroup = (TrackGroup) obj;
        return this.a == trackGroup.a && Arrays.equals(this.b, trackGroup.b);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        for (int i2 = 0; i2 < this.a; i2++) {
            parcel.writeParcelable(this.b[i2], 0);
        }
    }
}
