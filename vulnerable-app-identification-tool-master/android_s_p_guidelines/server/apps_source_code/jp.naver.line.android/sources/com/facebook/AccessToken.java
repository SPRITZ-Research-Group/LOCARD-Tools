package com.facebook;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jp.naver.android.npush.common.NPushIntent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final Creator<AccessToken> CREATOR = new Creator() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AccessToken[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new AccessToken(parcel);
        }
    };
    private static final Date a;
    private static final Date b;
    private static final Date c = new Date();
    private static final f d = f.FACEBOOK_APPLICATION_WEB;
    private final Date e;
    private final Set<String> f;
    private final Set<String> g;
    private final String h;
    private final f i;
    private final Date j;
    private final String k;
    private final String l;

    public final int describeContents() {
        return 0;
    }

    static {
        Date date = new Date(Long.MAX_VALUE);
        a = date;
        b = date;
    }

    public AccessToken(String str, String str2, String str3, Collection<String> collection, Collection<String> collection2, f fVar, Date date, Date date2) {
        bn.a(str, "accessToken");
        bn.a(str2, "applicationId");
        bn.a(str3, "userId");
        if (date == null) {
            date = b;
        }
        this.e = date;
        this.f = Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet());
        this.g = Collections.unmodifiableSet(collection2 != null ? new HashSet(collection2) : new HashSet());
        this.h = str;
        if (fVar == null) {
            fVar = d;
        }
        this.i = fVar;
        if (date2 == null) {
            date2 = c;
        }
        this.j = date2;
        this.k = str2;
        this.l = str3;
    }

    public static AccessToken a() {
        return d.a().b();
    }

    public static boolean b() {
        AccessToken b = d.a().b();
        return (b == null || b.l()) ? false : true;
    }

    static void c() {
        AccessToken b = d.a().b();
        if (b != null) {
            d.a().a(new AccessToken(b.h, b.k, b.l, b.f, b.g, b.i, new Date(), new Date()));
        }
    }

    public static void a(AccessToken accessToken) {
        d.a().a(accessToken);
    }

    public final String d() {
        return this.h;
    }

    public final Date e() {
        return this.e;
    }

    public final Set<String> f() {
        return this.f;
    }

    public final Set<String> g() {
        return this.g;
    }

    public final f h() {
        return this.i;
    }

    public final Date i() {
        return this.j;
    }

    public final String j() {
        return this.k;
    }

    public final String k() {
        return this.l;
    }

    public final String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{AccessToken");
        stringBuilder.append(" token:");
        if (this.h == null) {
            str = "null";
        } else if (s.a(ai.INCLUDE_ACCESS_TOKENS)) {
            str = this.h;
        } else {
            str = "ACCESS_TOKEN_REMOVED";
        }
        stringBuilder.append(str);
        stringBuilder.append(" permissions:");
        if (this.f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append("[");
            stringBuilder.append(TextUtils.join(", ", this.f));
            stringBuilder.append("]");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        return this.e.equals(accessToken.e) && this.f.equals(accessToken.f) && this.g.equals(accessToken.g) && this.h.equals(accessToken.h) && this.i == accessToken.i && this.j.equals(accessToken.j) && (this.k != null ? !this.k.equals(accessToken.k) : accessToken.k != null) && this.l.equals(accessToken.l);
    }

    public final int hashCode() {
        return ((((((((((((((this.e.hashCode() + 527) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode()) * 31) + (this.k == null ? 0 : this.k.hashCode())) * 31) + this.l.hashCode();
    }

    static com.facebook.AccessToken a(android.os.Bundle r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.AccessToken.a(android.os.Bundle):com.facebook.AccessToken. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = "com.facebook.TokenCachingStrategy.Permissions";
        r5 = a(r10, r0);
        r0 = "com.facebook.TokenCachingStrategy.DeclinedPermissions";
        r6 = a(r10, r0);
        r0 = com.facebook.ah.d(r10);
        r1 = com.facebook.internal.bj.a(r0);
        if (r1 == 0) goto L_0x001a;
    L_0x0016:
        r0 = com.facebook.s.j();
    L_0x001a:
        r3 = r0;
        r2 = com.facebook.ah.b(r10);
        r0 = com.facebook.internal.bj.e(r2);
        r1 = "id";	 Catch:{ JSONException -> 0x0040 }
        r4 = r0.getString(r1);	 Catch:{ JSONException -> 0x0040 }
        r0 = new com.facebook.AccessToken;
        r7 = com.facebook.ah.c(r10);
        r1 = "com.facebook.TokenCachingStrategy.ExpirationDate";
        r8 = com.facebook.ah.a(r10, r1);
        r1 = "com.facebook.TokenCachingStrategy.LastRefreshDate";
        r9 = com.facebook.ah.a(r10, r1);
        r1 = r0;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        return r0;
    L_0x0040:
        r10 = 0;
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessToken.a(android.os.Bundle):com.facebook.AccessToken");
    }

    private static List<String> a(Bundle bundle, String str) {
        Collection stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(stringArrayList));
    }

    public final boolean l() {
        return new Date().after(this.e);
    }

    final JSONObject m() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NPushIntent.EXTRA_VERSION, 1);
        jSONObject.put("token", this.h);
        jSONObject.put("expires_at", this.e.getTime());
        jSONObject.put("permissions", new JSONArray(this.f));
        jSONObject.put("declined_permissions", new JSONArray(this.g));
        jSONObject.put("last_refresh", this.j.getTime());
        jSONObject.put("source", this.i.name());
        jSONObject.put("application_id", this.k);
        jSONObject.put("user_id", this.l);
        return jSONObject;
    }

    static AccessToken a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getInt(NPushIntent.EXTRA_VERSION) <= 1) {
            String string = jSONObject.getString("token");
            Date date = new Date(jSONObject.getLong("expires_at"));
            JSONArray jSONArray = jSONObject.getJSONArray("permissions");
            JSONArray jSONArray2 = jSONObject.getJSONArray("declined_permissions");
            Date date2 = new Date(jSONObject.getLong("last_refresh"));
            return new AccessToken(string, jSONObject.getString("application_id"), jSONObject.getString("user_id"), bj.a(jSONArray), bj.a(jSONArray2), f.valueOf(jSONObject.getString("source")), date, date2);
        }
        throw new n("Unknown AccessToken serialization format.");
    }

    AccessToken(Parcel parcel) {
        this.e = new Date(parcel.readLong());
        Collection arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.f = Collections.unmodifiableSet(new HashSet(arrayList));
        arrayList.clear();
        parcel.readStringList(arrayList);
        this.g = Collections.unmodifiableSet(new HashSet(arrayList));
        this.h = parcel.readString();
        this.i = f.valueOf(parcel.readString());
        this.j = new Date(parcel.readLong());
        this.k = parcel.readString();
        this.l = parcel.readString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.e.getTime());
        parcel.writeStringList(new ArrayList(this.f));
        parcel.writeStringList(new ArrayList(this.g));
        parcel.writeString(this.h);
        parcel.writeString(this.i.name());
        parcel.writeLong(this.j.getTime());
        parcel.writeString(this.k);
        parcel.writeString(this.l);
    }
}
