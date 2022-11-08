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

    public Metadata(Entry... entries) {
        this.a = entries;
    }

    public Metadata(List<? extends Entry> entries) {
        this.a = new Entry[entries.size()];
        entries.toArray(this.a);
    }

    Metadata(Parcel in) {
        this.a = new Entry[in.readInt()];
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = (Entry) in.readParcelable(Entry.class.getClassLoader());
        }
    }

    public final int a() {
        return this.a.length;
    }

    public final Entry a(int index) {
        return this.a[index];
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

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.a.length);
        for (Entry entry : this.a) {
            dest.writeParcelable(entry, 0);
        }
    }
}
