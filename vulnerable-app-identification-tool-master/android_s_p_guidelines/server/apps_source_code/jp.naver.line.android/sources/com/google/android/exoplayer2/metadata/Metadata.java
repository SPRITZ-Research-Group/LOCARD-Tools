package com.google.android.exoplayer2.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;
import java.util.List;

public final class Metadata implements Parcelable {
    public static final Creator<Metadata> CREATOR = new Creator<Metadata>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new Metadata[0];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new Metadata(parcel);
        }
    };
    private final Entry[] a;

    public interface Entry extends Parcelable {
    }

    public final int describeContents() {
        return 0;
    }

    public Metadata(Entry... entryArr) {
        this.a = entryArr;
    }

    public Metadata(List<? extends Entry> list) {
        this.a = new Entry[list.size()];
        list.toArray(this.a);
    }

    Metadata(Parcel parcel) {
        this.a = new Entry[parcel.readInt()];
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = (Entry) parcel.readParcelable(Entry.class.getClassLoader());
        }
    }

    public final int a() {
        return this.a.length;
    }

    public final Entry a(int i) {
        return this.a[i];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.a, ((Metadata) obj).a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a.length);
        for (Parcelable writeParcelable : this.a) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
