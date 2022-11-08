package jp.naver.myhome.android.activity.write;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.io.File;
import jp.naver.line.android.obs.model.OBSCopyInfo;

public final class OBSCopy implements Parcelable {
    public static final Creator<OBSCopy> CREATOR = new e();
    public Uri a;
    public OBSCopyInfo b;

    public final int describeContents() {
        return 0;
    }

    public static OBSCopy a(Uri uri, OBSCopyInfo oBSCopyInfo) {
        if (uri == null && oBSCopyInfo == null) {
            return null;
        }
        OBSCopy oBSCopy = new OBSCopy();
        if (uri == null) {
            uri = !TextUtils.isEmpty(oBSCopyInfo.d) ? Uri.fromFile(new File(oBSCopyInfo.d)) : Uri.EMPTY;
        }
        oBSCopy.a = uri;
        oBSCopy.b = oBSCopyInfo;
        return oBSCopy;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
    }
}
