package jp.naver.myhome.android.activity.write;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import defpackage.qcj;
import defpackage.tjr;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jp.naver.gallery.android.media.MediaItem;
import jp.naver.line.android.model.Location;
import jp.naver.line.android.music.ProfileMusic;
import jp.naver.line.android.obs.model.OBSCopyInfo;
import jp.naver.line.android.stickershop.model.StickerInfo;
import jp.naver.myhome.android.activity.write.writeform.model.LocationModel;
import jp.naver.myhome.android.model.x;

public final class WriteParams implements Parcelable {
    public static final Creator<WriteParams> CREATOR = new s();
    boolean A;
    boolean B;
    private List<MediaItem> C;
    public char a = 't';
    public boolean b = true;
    public String c;
    public String d;
    public x e = x.UNDEFINED;
    public String f;
    public String g;
    public StickerInfo h;
    public Uri[] i;
    public Uri[] j;
    public OBSCopy[] k;
    public LocationModel l;
    public ProfileMusic m;
    public boolean n;
    public String o;
    public String p;
    public String q;
    public int r;
    public boolean s;
    public boolean t;
    public boolean u = true;
    boolean v;
    public String w;
    public boolean x;
    public boolean y;
    public String z;

    public final int describeContents() {
        return 0;
    }

    private static <T> T[] a(Class<T> cls, List<T> list, T... tArr) {
        int size = qcj.b((Collection) list) ? list.size() : 0;
        int length = qcj.b((Object[]) tArr) ? tArr.length : 0;
        Object[] objArr = (Object[]) Array.newInstance(cls, size + length);
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj != null) {
                int i3 = i + 1;
                objArr[i] = obj;
                i = i3;
            }
        }
        for (int i4 = 0; i4 < length; i4++) {
            T t = tArr[i4];
            if (t != null) {
                size = i + 1;
                objArr[i] = t;
                i = size;
            }
        }
        return i > 0 ? objArr : null;
    }

    static <T> T[] a(Parcelable[] parcelableArr, Class<T> cls) {
        int length = qcj.b((Object[]) parcelableArr) ? parcelableArr.length : 0;
        if (length <= 0) {
            return null;
        }
        Object[] objArr = (Object[]) Array.newInstance(cls, length);
        System.arraycopy(parcelableArr, 0, objArr, 0, length);
        return objArr;
    }

    public final WriteParams a(String str) {
        this.d = str;
        return this;
    }

    public final WriteParams a(Location location) {
        this.l = LocationModel.a(location);
        return this;
    }

    public final WriteParams a(OBSCopy oBSCopy) {
        this.k = (OBSCopy[]) a(OBSCopy.class, null, oBSCopy);
        return this;
    }

    public final WriteParams a(List<OBSCopyInfo> list) {
        if (qcj.b((Collection) list)) {
            List arrayList = new ArrayList(list.size());
            for (OBSCopyInfo oBSCopyInfo : list) {
                if (oBSCopyInfo != null) {
                    arrayList.add(OBSCopy.a(null, oBSCopyInfo));
                }
            }
            this.k = (OBSCopy[]) a(OBSCopy.class, arrayList, new OBSCopy[0]);
        }
        return this;
    }

    public final WriteParams a(List<Uri> list, Uri... uriArr) {
        this.i = (Uri[]) a(Uri.class, list, uriArr);
        return this;
    }

    public final WriteParams b(List<MediaItem> list) {
        this.C = list;
        return this;
    }

    public final WriteParams a(x xVar) {
        this.e = xVar;
        return this;
    }

    public final WriteParams b(String str) {
        this.g = str;
        return this;
    }

    public final WriteParams b(List<Uri> list, Uri... uriArr) {
        this.j = (Uri[]) a(Uri.class, list, uriArr);
        return this;
    }

    public final WriteParams a() {
        this.u = false;
        return this;
    }

    public final WriteParams c(String str) {
        this.w = str;
        return this;
    }

    public final List<MediaItem> b() {
        return this.C;
    }

    public final boolean c() {
        return (TextUtils.isEmpty(this.g) && this.h == null && ((this.i == null || this.i.length == 0) && ((this.j == null || this.j.length == 0) && ((this.k == null || this.k.length == 0) && !qcj.b(this.C) && this.l == null && !this.x && this.m == null && !this.n && TextUtils.isEmpty(this.o))))) ? false : true;
    }

    public final boolean d() {
        return !(this.i == null || this.i.length == 0) || (!(this.j == null || this.j.length == 0) || (!(this.k == null || this.k.length == 0) || qcj.b(this.C)));
    }

    public static int a(Uri uri) {
        Object queryParameter = uri.getQueryParameter("eventAllowedScope");
        return !TextUtils.isEmpty(queryParameter) ? d(queryParameter) : 0;
    }

    public static int d(String str) {
        if ("PUBLIC".equals(str)) {
            return 1;
        }
        if ("FRIEND".equals(str)) {
            return 2;
        }
        if ("FRIEND_PUBLIC".equals(str)) {
            return 3;
        }
        return "USER_DEFINED".equals(str) ? 4 : 0;
    }

    public final String toString() {
        return tjr.a(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Character.toString(this.a));
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt((this.e != null ? this.e : x.UNDEFINED).ordinal());
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeParcelable(this.h, i);
        parcel.writeParcelableArray(this.i, i);
        parcel.writeParcelableArray(this.j, i);
        parcel.writeParcelableArray(this.k, i);
        parcel.writeParcelable(this.l, i);
        parcel.writeParcelable(this.m, i);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeInt(this.r);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeString(this.w);
        parcel.writeInt(this.x);
        parcel.writeInt(this.y);
        parcel.writeString(this.z);
        parcel.writeInt(this.A);
        parcel.writeInt(this.B);
        parcel.writeList(this.C);
    }
}
