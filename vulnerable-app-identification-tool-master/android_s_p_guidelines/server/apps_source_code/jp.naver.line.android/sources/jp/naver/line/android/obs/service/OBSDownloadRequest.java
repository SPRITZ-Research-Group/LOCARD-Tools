package jp.naver.line.android.obs.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Random;
import jp.naver.line.android.obs.f;

public class OBSDownloadRequest implements Parcelable {
    public static final Creator<OBSDownloadRequest> CREATOR = new Creator<OBSDownloadRequest>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new OBSDownloadRequest[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new OBSDownloadRequest(parcel);
        }
    };
    private String a;
    private f b;
    private String c;
    private String d;
    private String e;
    private int f;
    private long g;
    private String h;

    public int describeContents() {
        return 0;
    }

    private OBSDownloadRequest() {
        this.g = new Random().nextLong() + System.currentTimeMillis();
    }

    OBSDownloadRequest(Parcel parcel) {
        this.a = parcel.readString();
        this.b = f.values()[parcel.readInt()];
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
        this.g = parcel.readLong();
        this.h = parcel.readString();
    }

    public static final OBSDownloadRequest a(String str, String str2, boolean z, boolean z2) {
        OBSDownloadRequest oBSDownloadRequest = new OBSDownloadRequest();
        oBSDownloadRequest.a = g.a(str, str2, z);
        oBSDownloadRequest.b = z ? f.IMAGE_PROFILE_PREVIEW : f.IMAGE_PROFILE;
        oBSDownloadRequest.c = str;
        oBSDownloadRequest.d = str2;
        oBSDownloadRequest.f = ((z ? 2 : 1) | (z2 ? 4 : 0)) | 8;
        return oBSDownloadRequest;
    }

    public static final OBSDownloadRequest a(String str) {
        OBSDownloadRequest oBSDownloadRequest = new OBSDownloadRequest();
        oBSDownloadRequest.a = g.a(str, false, false);
        oBSDownloadRequest.b = f.IMAGE_GROUP;
        oBSDownloadRequest.c = str;
        oBSDownloadRequest.f = 1;
        return oBSDownloadRequest;
    }

    public static final OBSDownloadRequest b(String str) {
        OBSDownloadRequest oBSDownloadRequest = new OBSDownloadRequest();
        oBSDownloadRequest.a = g.c(str, false);
        oBSDownloadRequest.b = f.IMAGE_SQUARE_GROUP_MEMBER;
        oBSDownloadRequest.c = str;
        oBSDownloadRequest.f = 9;
        return oBSDownloadRequest;
    }

    public final String a() {
        return this.a;
    }

    public final f b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.e;
    }

    public final String e() {
        return this.d;
    }

    public final long f() {
        return this.g;
    }

    public final boolean g() {
        return (this.f & 2) == 2;
    }

    public final boolean h() {
        return (this.f & 4) == 4;
    }

    public final boolean i() {
        return (this.f & 8) == 8;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b.ordinal());
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeLong(this.g);
        parcel.writeString(this.h);
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OBSDownloadRequest oBSDownloadRequest = (OBSDownloadRequest) obj;
        if (this.a == null) {
            if (oBSDownloadRequest.a != null) {
                return false;
            }
        } else if (!this.a.equals(oBSDownloadRequest.a)) {
            return false;
        }
        return true;
    }
}
