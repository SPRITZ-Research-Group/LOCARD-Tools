package com.google.android.exoplayer2.source.dash.manifest;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;

public final class RepresentationKey implements Parcelable, Comparable<RepresentationKey> {
    public static final Creator<RepresentationKey> CREATOR = new Creator<RepresentationKey>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new RepresentationKey[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new RepresentationKey(parcel.readInt(), parcel.readInt(), parcel.readInt());
        }
    };
    public final int a;
    public final int b;
    public final int c;

    public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
        RepresentationKey representationKey = (RepresentationKey) obj;
        int i = this.a - representationKey.a;
        if (i != 0) {
            return i;
        }
        i = this.b - representationKey.b;
        return i == 0 ? this.c - representationKey.c : i;
    }

    public RepresentationKey(int periodIndex, int adaptationSetIndex, int representationIndex) {
        this.a = periodIndex;
        this.b = adaptationSetIndex;
        this.c = representationIndex;
    }

    public final String toString() {
        return this.a + "." + this.b + "." + this.c;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.a);
        dest.writeInt(this.b);
        dest.writeInt(this.c);
    }
}
