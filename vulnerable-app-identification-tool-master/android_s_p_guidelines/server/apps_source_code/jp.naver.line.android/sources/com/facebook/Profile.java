package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.facebook.internal.bj;
import com.facebook.internal.bk;
import com.facebook.internal.bn;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new Profile[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new Profile(parcel, (byte) 0);
        }
    };
    private static final String a = "Profile";
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final Uri g;

    public final int describeContents() {
        return 0;
    }

    /* synthetic */ Profile(Parcel parcel, byte b) {
        this(parcel);
    }

    public static Profile a() {
        return ak.a().b();
    }

    public static void a(Profile profile) {
        ak.a().a(profile);
    }

    public static void b() {
        AccessToken a = AccessToken.a();
        if (AccessToken.b()) {
            bj.a(a.d(), new bk() {
                public final void a(JSONObject jSONObject) {
                    String optString = jSONObject.optString("id");
                    if (optString != null) {
                        String optString2 = jSONObject.optString("link");
                        Profile.a(new Profile(optString, jSONObject.optString("first_name"), jSONObject.optString("middle_name"), jSONObject.optString("last_name"), jSONObject.optString("name"), optString2 != null ? Uri.parse(optString2) : null));
                    }
                }

                public final void a(n nVar) {
                    Log.e(Profile.a, "Got unexpected exception: ".concat(String.valueOf(nVar)));
                }
            });
        } else {
            ak.a().a(null);
        }
    }

    public Profile(String str, String str2, String str3, String str4, String str5, Uri uri) {
        bn.a(str, "id");
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = uri;
    }

    public final String c() {
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) obj;
        if (this.b.equals(profile.b) && this.c == null) {
            return profile.c == null;
        } else {
            if (this.c.equals(profile.c) && this.d == null) {
                return profile.d == null;
            } else {
                if (this.d.equals(profile.d) && this.e == null) {
                    return profile.e == null;
                } else {
                    if (this.e.equals(profile.e) && this.f == null) {
                        return profile.f == null;
                    } else {
                        if (this.f.equals(profile.f) && this.g == null) {
                            return profile.g == null;
                        } else {
                            return this.g.equals(profile.g);
                        }
                    }
                }
            }
        }
    }

    public final int hashCode() {
        int hashCode = this.b.hashCode() + 527;
        if (this.c != null) {
            hashCode = (hashCode * 31) + this.c.hashCode();
        }
        if (this.d != null) {
            hashCode = (hashCode * 31) + this.d.hashCode();
        }
        if (this.e != null) {
            hashCode = (hashCode * 31) + this.e.hashCode();
        }
        if (this.f != null) {
            hashCode = (hashCode * 31) + this.f.hashCode();
        }
        return this.g != null ? (hashCode * 31) + this.g.hashCode() : hashCode;
    }

    final org.json.JSONObject d() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.Profile.d():org.json.JSONObject. bs: []
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
        r3 = this;
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = "id";	 Catch:{ JSONException -> 0x0038 }
        r2 = r3.b;	 Catch:{ JSONException -> 0x0038 }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x0038 }
        r1 = "first_name";	 Catch:{ JSONException -> 0x0038 }
        r2 = r3.c;	 Catch:{ JSONException -> 0x0038 }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x0038 }
        r1 = "middle_name";	 Catch:{ JSONException -> 0x0038 }
        r2 = r3.d;	 Catch:{ JSONException -> 0x0038 }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x0038 }
        r1 = "last_name";	 Catch:{ JSONException -> 0x0038 }
        r2 = r3.e;	 Catch:{ JSONException -> 0x0038 }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x0038 }
        r1 = "name";	 Catch:{ JSONException -> 0x0038 }
        r2 = r3.f;	 Catch:{ JSONException -> 0x0038 }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x0038 }
        r1 = r3.g;	 Catch:{ JSONException -> 0x0038 }
        if (r1 == 0) goto L_0x0039;	 Catch:{ JSONException -> 0x0038 }
    L_0x002c:
        r1 = "link_uri";	 Catch:{ JSONException -> 0x0038 }
        r2 = r3.g;	 Catch:{ JSONException -> 0x0038 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x0038 }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x0038 }
        goto L_0x0039;
    L_0x0038:
        r0 = 0;
    L_0x0039:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.Profile.d():org.json.JSONObject");
    }

    Profile(JSONObject jSONObject) {
        Uri uri = null;
        this.b = jSONObject.optString("id", null);
        this.c = jSONObject.optString("first_name", null);
        this.d = jSONObject.optString("middle_name", null);
        this.e = jSONObject.optString("last_name", null);
        this.f = jSONObject.optString("name", null);
        String optString = jSONObject.optString("link_uri", null);
        if (optString != null) {
            uri = Uri.parse(optString);
        }
        this.g = uri;
    }

    private Profile(Parcel parcel) {
        Uri uri;
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        String readString = parcel.readString();
        if (readString == null) {
            uri = null;
        } else {
            uri = Uri.parse(readString);
        }
        this.g = uri;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g == null ? null : this.g.toString());
    }
}
