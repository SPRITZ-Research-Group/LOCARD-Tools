package com.microsoft.tokenshare;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RefreshToken implements Parcelable {
    public static final Creator<RefreshToken> CREATOR = new Creator<RefreshToken>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new RefreshToken[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new RefreshToken(parcel);
        }
    };
    private final String a;
    private final String b;

    public RefreshToken(String token, String appId) {
        this.a = token;
        this.b = appId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeString(this.b);
    }

    protected RefreshToken(Parcel in) {
        this.a = in.readString();
        this.b = in.readString();
    }

    public final String a() {
        return this.a;
    }

    public String toString() {
        return "RefreshToken{mRefreshToken='" + this.a + '\'' + ", mAppId='" + this.b + '\'' + '}';
    }
}
