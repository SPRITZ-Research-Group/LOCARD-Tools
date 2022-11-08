package jp.naver.line.android.common.access.remote;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SimpleContactDto implements Parcelable {
    public static final Creator<SimpleContactDto> CREATOR = new Creator<SimpleContactDto>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new SimpleContactDto[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new SimpleContactDto(parcel);
        }
    };
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;

    public int describeContents() {
        return 0;
    }

    public SimpleContactDto(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, boolean z4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = z;
        this.g = z2;
        this.h = z3;
        this.i = z4;
    }

    public SimpleContactDto(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        boolean z = false;
        this.f = parcel.readInt() == 1;
        this.g = parcel.readInt() == 1;
        this.h = parcel.readInt() == 1;
        if (parcel.readInt() == 1) {
            z = true;
        }
        this.i = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
    }
}
