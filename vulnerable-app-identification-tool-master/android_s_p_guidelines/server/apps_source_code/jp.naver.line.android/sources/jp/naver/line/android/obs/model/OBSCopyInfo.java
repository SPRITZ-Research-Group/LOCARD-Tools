package jp.naver.line.android.obs.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Pair;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OBSCopyInfo implements Parcelable, Serializable {
    public static final Creator<OBSCopyInfo> CREATOR = new Creator<OBSCopyInfo>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new OBSCopyInfo[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new OBSCopyInfo(parcel);
        }
    };
    private static String m = "jp.naver.line.android.intent.extras.OBSINFO";
    private static String n = "jp.naver.line.android.intent.extras.OBSINFO_FROM";
    private static String o = "jp.naver.line.android.intent.extras.OBSINFO_ID";
    private static final long serialVersionUID = 6021389627637288482L;
    public final String a;
    public final b b;
    public final c c;
    public final String d;
    public final String e;
    public final String f;
    public final long g;
    public final long h;
    public final transient Pair<String, String> i;
    public final Map<String, String> j;
    public final int k;
    public final int l;

    public int describeContents() {
        return 0;
    }

    public OBSCopyInfo(String str, b bVar) {
        this(str, bVar, c.IMAGE);
    }

    public OBSCopyInfo(String str, b bVar, c cVar) {
        this(str, bVar, cVar, null, null);
    }

    public OBSCopyInfo(String str, b bVar, c cVar, String str2, String str3) {
        this(str, bVar, cVar, str2, str3, null, 0);
    }

    public OBSCopyInfo(String str, b bVar, c cVar, String str2, String str3, String str4, long j) {
        this(str, bVar, cVar, str2, str3, str4, j, 0);
    }

    public OBSCopyInfo(String str, b bVar, c cVar, String str2, String str3, String str4, long j, long j2) {
        this(str, bVar, cVar, str2, str3, str4, j, j2, null, 0, 0, null);
    }

    public OBSCopyInfo(String str, b bVar, c cVar, String str2, String str3, String str4, long j, long j2, Pair<String, String> pair, int i, int i2, Map<String, String> map) {
        this.a = str;
        this.b = bVar;
        this.c = cVar;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = j;
        this.h = j2;
        this.i = pair;
        this.k = i;
        this.l = i2;
        this.j = map;
    }

    public static void a(Intent intent, OBSCopyInfo oBSCopyInfo, boolean z) {
        if (oBSCopyInfo != null) {
            if (z) {
                intent.putExtra(m, oBSCopyInfo);
                return;
            }
            intent.putExtra(o, oBSCopyInfo.a);
            intent.putExtra(n, oBSCopyInfo.b.ordinal());
        }
    }

    public static OBSCopyInfo a(Intent intent) {
        OBSCopyInfo oBSCopyInfo = (OBSCopyInfo) intent.getParcelableExtra(m);
        if (oBSCopyInfo != null) {
            return oBSCopyInfo;
        }
        int intExtra = intent.getIntExtra(n, -1);
        if (intExtra != -1) {
            Object stringExtra = intent.getStringExtra(o);
            if (!TextUtils.isEmpty(stringExtra)) {
                for (b bVar : b.values()) {
                    if (bVar.ordinal() == intExtra) {
                        return new OBSCopyInfo(stringExtra, bVar);
                    }
                }
            }
        }
        return null;
    }

    public static Pair<String, String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf(124);
        if (indexOf <= 0 || indexOf >= str.length() - 1) {
            return null;
        }
        return new Pair(str.substring(0, indexOf), str.substring(indexOf + 1));
    }

    public static String a(Pair<String, String> pair) {
        if (!b(pair)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append((String) pair.first);
        stringBuilder.append('|');
        stringBuilder.append((String) pair.second);
        return stringBuilder.toString();
    }

    static boolean b(Pair<String, String> pair) {
        return (pair == null || TextUtils.isEmpty((CharSequence) pair.first) || TextUtils.isEmpty((CharSequence) pair.second)) ? false : true;
    }

    public OBSCopyInfo(Parcel parcel) {
        this.a = parcel.readString();
        this.b = b.values()[parcel.readInt()];
        this.c = c.values()[parcel.readInt()];
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        CharSequence readString = parcel.readString();
        CharSequence readString2 = parcel.readString();
        Pair pair = (TextUtils.isEmpty(readString) || TextUtils.isEmpty(readString2)) ? null : new Pair(readString, readString2);
        this.i = pair;
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.j = new HashMap();
        parcel.readMap(this.j, getClass().getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a == null ? "" : this.a);
        int i2 = 0;
        parcel.writeInt(this.b == null ? 0 : this.b.ordinal());
        if (this.c != null) {
            i2 = this.c.ordinal();
        }
        parcel.writeInt(i2);
        parcel.writeString(this.d == null ? "" : this.d);
        parcel.writeString(this.e == null ? "" : this.e);
        parcel.writeString(this.f == null ? "" : this.f);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        if (this.i != null) {
            parcel.writeString((String) this.i.first);
            parcel.writeString((String) this.i.second);
        } else {
            parcel.writeString("");
            parcel.writeString("");
        }
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeMap(this.j);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("{obsId=");
        stringBuilder.append(this.a);
        stringBuilder.append(" from=");
        stringBuilder.append(this.b);
        stringBuilder.append(" type=");
        stringBuilder.append(this.c);
        stringBuilder.append("\n localFilePath=");
        stringBuilder.append(this.d);
        stringBuilder.append(" thumbnailPath=");
        stringBuilder.append(this.e);
        stringBuilder.append("\n localFileName=");
        stringBuilder.append(this.f);
        stringBuilder.append(" fileSize=");
        stringBuilder.append(this.g);
        stringBuilder.append(" duration=");
        stringBuilder.append(this.h);
        stringBuilder.append("\n parameter=");
        if (this.i != null) {
            stringBuilder.append((String) this.i.first);
            stringBuilder.append('|');
            stringBuilder.append((String) this.i.second);
        }
        if (this.j != null) {
            stringBuilder.append("headerMap=");
            stringBuilder.append(this.j.toString());
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
