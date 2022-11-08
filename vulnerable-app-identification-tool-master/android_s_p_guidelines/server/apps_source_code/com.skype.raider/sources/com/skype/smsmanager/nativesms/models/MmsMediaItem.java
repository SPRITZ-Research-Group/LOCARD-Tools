package com.skype.smsmanager.nativesms.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MmsMediaItem implements Parcelable {
    public static final Creator<MmsMediaItem> CREATOR = new Creator<MmsMediaItem>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new MmsMediaItem[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new MmsMediaItem(parcel, (byte) 0);
        }
    };
    private final String a;
    private final String b;
    private final String c;
    private final long d;

    /* synthetic */ MmsMediaItem(Parcel x0, byte b) {
        this(x0);
    }

    public MmsMediaItem(String uri, String name, String type, long size) {
        this.a = uri;
        this.b = name;
        this.c = type;
        this.d = size;
    }

    private MmsMediaItem(Parcel in) {
        this.a = in.readString();
        this.b = in.readString();
        this.c = in.readString();
        this.d = in.readLong();
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final long d() {
        return this.d;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.a);
        out.writeString(this.b);
        out.writeString(this.c);
        out.writeLong(this.d);
    }

    public int describeContents() {
        return 0;
    }
}
