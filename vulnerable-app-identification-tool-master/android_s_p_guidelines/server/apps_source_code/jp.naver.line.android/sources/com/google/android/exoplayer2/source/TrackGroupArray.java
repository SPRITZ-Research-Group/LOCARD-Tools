package com.google.android.exoplayer2.source;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class TrackGroupArray implements Parcelable {
    public static final Creator<TrackGroupArray> CREATOR = new Creator<TrackGroupArray>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new TrackGroupArray[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new TrackGroupArray(parcel);
        }
    };
    public static final TrackGroupArray a = new TrackGroupArray(new TrackGroup[0]);
    public final int b;
    private final TrackGroup[] c;
    private int d;

    public final int describeContents() {
        return 0;
    }

    public TrackGroupArray(TrackGroup... trackGroupArr) {
        this.c = trackGroupArr;
        this.b = trackGroupArr.length;
    }

    TrackGroupArray(Parcel parcel) {
        this.b = parcel.readInt();
        this.c = new TrackGroup[this.b];
        for (int i = 0; i < this.b; i++) {
            this.c[i] = (TrackGroup) parcel.readParcelable(TrackGroup.class.getClassLoader());
        }
    }

    public final TrackGroup a(int i) {
        return this.c[i];
    }

    public final int a(TrackGroup trackGroup) {
        for (int i = 0; i < this.b; i++) {
            if (this.c[i] == trackGroup) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.d == 0) {
            this.d = Arrays.hashCode(this.c);
        }
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrackGroupArray trackGroupArray = (TrackGroupArray) obj;
        return this.b == trackGroupArray.b && Arrays.equals(this.c, trackGroupArray.c);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        for (int i2 = 0; i2 < this.b; i2++) {
            parcel.writeParcelable(this.c[i2], 0);
        }
    }
}
