package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.internal.p;
import com.facebook.internal.s;
import com.facebook.internal.u;
import java.net.HttpURLConnection;
import org.json.JSONObject;

public final class FacebookRequestError implements Parcelable {
    public static final Creator<FacebookRequestError> CREATOR = new Creator<FacebookRequestError>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new FacebookRequestError[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new FacebookRequestError(parcel, (byte) 0);
        }
    };
    static final r a = new r();
    private final q b;
    private final int c;
    private final int d;
    private final int e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final JSONObject k;
    private final JSONObject l;
    private final Object m;
    private final HttpURLConnection n;
    private final n o;

    public final int describeContents() {
        return 0;
    }

    /* synthetic */ FacebookRequestError(Parcel parcel, byte b) {
        this(parcel);
    }

    private FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, boolean z, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, n nVar) {
        Object obj2;
        q qVar;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = str;
        this.g = str2;
        this.l = jSONObject;
        this.k = jSONObject2;
        this.m = obj;
        this.n = httpURLConnection;
        this.h = str3;
        this.i = str4;
        if (nVar != null) {
            this.o = nVar;
            obj2 = 1;
        } else {
            this.o = new v(this, str2);
            obj2 = null;
        }
        p h = h();
        if (obj2 != null) {
            qVar = q.OTHER;
        } else {
            qVar = h.a(i2, i3, z);
        }
        this.b = qVar;
        this.j = h.a(this.b);
    }

    FacebookRequestError(HttpURLConnection httpURLConnection, Exception exception) {
        Throwable th = exception;
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, th instanceof n ? (n) th : new n(th));
    }

    public FacebookRequestError(int i, String str, String str2) {
        this(-1, i, -1, str, str2, null, null, false, null, null, null, null, null);
    }

    public final int a() {
        return this.c;
    }

    public final int b() {
        return this.d;
    }

    public final int c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }

    public final String e() {
        if (this.g != null) {
            return this.g;
        }
        return this.o.getLocalizedMessage();
    }

    public final JSONObject f() {
        return this.k;
    }

    public final n g() {
        return this.o;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("{HttpStatus: ");
        stringBuilder.append(this.c);
        stringBuilder.append(", errorCode: ");
        stringBuilder.append(this.d);
        stringBuilder.append(", subErrorCode: ");
        stringBuilder.append(this.e);
        stringBuilder.append(", errorType: ");
        stringBuilder.append(this.f);
        stringBuilder.append(", errorMessage: ");
        stringBuilder.append(e());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    static com.facebook.FacebookRequestError a(org.json.JSONObject r16, java.lang.Object r17, java.net.HttpURLConnection r18) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.FacebookRequestError.a(org.json.JSONObject, java.lang.Object, java.net.HttpURLConnection):com.facebook.FacebookRequestError. bs: []
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
        r10 = r16;
        r14 = 0;
        r0 = "code";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r10.has(r0);	 Catch:{ JSONException -> 0x00e8 }
        if (r0 == 0) goto L_0x00e8;	 Catch:{ JSONException -> 0x00e8 }
    L_0x000b:
        r0 = "code";	 Catch:{ JSONException -> 0x00e8 }
        r1 = r10.getInt(r0);	 Catch:{ JSONException -> 0x00e8 }
        r0 = "body";	 Catch:{ JSONException -> 0x00e8 }
        r2 = "FACEBOOK_NON_JSON_RESULT";	 Catch:{ JSONException -> 0x00e8 }
        r0 = com.facebook.internal.bj.a(r10, r0, r2);	 Catch:{ JSONException -> 0x00e8 }
        if (r0 == 0) goto L_0x00b6;	 Catch:{ JSONException -> 0x00e8 }
    L_0x001b:
        r2 = r0 instanceof org.json.JSONObject;	 Catch:{ JSONException -> 0x00e8 }
        if (r2 == 0) goto L_0x00b6;	 Catch:{ JSONException -> 0x00e8 }
    L_0x001f:
        r9 = r0;	 Catch:{ JSONException -> 0x00e8 }
        r9 = (org.json.JSONObject) r9;	 Catch:{ JSONException -> 0x00e8 }
        r0 = "error";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r9.has(r0);	 Catch:{ JSONException -> 0x00e8 }
        r2 = 1;	 Catch:{ JSONException -> 0x00e8 }
        r3 = -1;	 Catch:{ JSONException -> 0x00e8 }
        r4 = 0;	 Catch:{ JSONException -> 0x00e8 }
        if (r0 == 0) goto L_0x0063;	 Catch:{ JSONException -> 0x00e8 }
    L_0x002d:
        r0 = "error";	 Catch:{ JSONException -> 0x00e8 }
        r0 = com.facebook.internal.bj.a(r9, r0, r14);	 Catch:{ JSONException -> 0x00e8 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ JSONException -> 0x00e8 }
        r5 = "type";	 Catch:{ JSONException -> 0x00e8 }
        r5 = r0.optString(r5, r14);	 Catch:{ JSONException -> 0x00e8 }
        r6 = "message";	 Catch:{ JSONException -> 0x00e8 }
        r6 = r0.optString(r6, r14);	 Catch:{ JSONException -> 0x00e8 }
        r7 = "code";	 Catch:{ JSONException -> 0x00e8 }
        r7 = r0.optInt(r7, r3);	 Catch:{ JSONException -> 0x00e8 }
        r8 = "error_subcode";	 Catch:{ JSONException -> 0x00e8 }
        r3 = r0.optInt(r8, r3);	 Catch:{ JSONException -> 0x00e8 }
        r8 = "error_user_msg";	 Catch:{ JSONException -> 0x00e8 }
        r8 = r0.optString(r8, r14);	 Catch:{ JSONException -> 0x00e8 }
        r11 = "error_user_title";	 Catch:{ JSONException -> 0x00e8 }
        r11 = r0.optString(r11, r14);	 Catch:{ JSONException -> 0x00e8 }
        r12 = "is_transient";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r0.optBoolean(r12, r4);	 Catch:{ JSONException -> 0x00e8 }
        r4 = r7;	 Catch:{ JSONException -> 0x00e8 }
        r7 = r8;	 Catch:{ JSONException -> 0x00e8 }
        r8 = r0;	 Catch:{ JSONException -> 0x00e8 }
        goto L_0x00a2;	 Catch:{ JSONException -> 0x00e8 }
    L_0x0063:
        r0 = "error_code";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r9.has(r0);	 Catch:{ JSONException -> 0x00e8 }
        if (r0 != 0) goto L_0x0084;	 Catch:{ JSONException -> 0x00e8 }
    L_0x006b:
        r0 = "error_msg";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r9.has(r0);	 Catch:{ JSONException -> 0x00e8 }
        if (r0 != 0) goto L_0x0084;	 Catch:{ JSONException -> 0x00e8 }
    L_0x0073:
        r0 = "error_reason";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r9.has(r0);	 Catch:{ JSONException -> 0x00e8 }
        if (r0 == 0) goto L_0x007c;	 Catch:{ JSONException -> 0x00e8 }
    L_0x007b:
        goto L_0x0084;	 Catch:{ JSONException -> 0x00e8 }
    L_0x007c:
        r5 = r14;	 Catch:{ JSONException -> 0x00e8 }
        r6 = r5;	 Catch:{ JSONException -> 0x00e8 }
        r7 = r6;	 Catch:{ JSONException -> 0x00e8 }
        r11 = r7;	 Catch:{ JSONException -> 0x00e8 }
        r2 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r3 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r8 = 0;	 Catch:{ JSONException -> 0x00e8 }
        goto L_0x00a2;	 Catch:{ JSONException -> 0x00e8 }
    L_0x0084:
        r0 = "error_reason";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r9.optString(r0, r14);	 Catch:{ JSONException -> 0x00e8 }
        r5 = "error_msg";	 Catch:{ JSONException -> 0x00e8 }
        r5 = r9.optString(r5, r14);	 Catch:{ JSONException -> 0x00e8 }
        r6 = "error_code";	 Catch:{ JSONException -> 0x00e8 }
        r6 = r9.optInt(r6, r3);	 Catch:{ JSONException -> 0x00e8 }
        r7 = "error_subcode";	 Catch:{ JSONException -> 0x00e8 }
        r3 = r9.optInt(r7, r3);	 Catch:{ JSONException -> 0x00e8 }
        r4 = r6;	 Catch:{ JSONException -> 0x00e8 }
        r7 = r14;	 Catch:{ JSONException -> 0x00e8 }
        r11 = r7;	 Catch:{ JSONException -> 0x00e8 }
        r8 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r6 = r5;	 Catch:{ JSONException -> 0x00e8 }
        r5 = r0;	 Catch:{ JSONException -> 0x00e8 }
    L_0x00a2:
        if (r2 == 0) goto L_0x00b6;	 Catch:{ JSONException -> 0x00e8 }
    L_0x00a4:
        r15 = new com.facebook.FacebookRequestError;	 Catch:{ JSONException -> 0x00e8 }
        r13 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r0 = r15;	 Catch:{ JSONException -> 0x00e8 }
        r2 = r4;	 Catch:{ JSONException -> 0x00e8 }
        r4 = r5;	 Catch:{ JSONException -> 0x00e8 }
        r5 = r6;	 Catch:{ JSONException -> 0x00e8 }
        r6 = r11;	 Catch:{ JSONException -> 0x00e8 }
        r10 = r16;	 Catch:{ JSONException -> 0x00e8 }
        r11 = r17;	 Catch:{ JSONException -> 0x00e8 }
        r12 = r18;	 Catch:{ JSONException -> 0x00e8 }
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);	 Catch:{ JSONException -> 0x00e8 }
        return r15;	 Catch:{ JSONException -> 0x00e8 }
    L_0x00b6:
        r0 = a;	 Catch:{ JSONException -> 0x00e8 }
        r0 = r0.a(r1);	 Catch:{ JSONException -> 0x00e8 }
        if (r0 != 0) goto L_0x00e8;	 Catch:{ JSONException -> 0x00e8 }
    L_0x00be:
        r15 = new com.facebook.FacebookRequestError;	 Catch:{ JSONException -> 0x00e8 }
        r2 = -1;	 Catch:{ JSONException -> 0x00e8 }
        r3 = -1;	 Catch:{ JSONException -> 0x00e8 }
        r4 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r5 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r6 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r7 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r8 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r0 = "body";	 Catch:{ JSONException -> 0x00e8 }
        r0 = r10.has(r0);	 Catch:{ JSONException -> 0x00e8 }
        if (r0 == 0) goto L_0x00db;	 Catch:{ JSONException -> 0x00e8 }
    L_0x00cf:
        r0 = "body";	 Catch:{ JSONException -> 0x00e8 }
        r9 = "FACEBOOK_NON_JSON_RESULT";	 Catch:{ JSONException -> 0x00e8 }
        r0 = com.facebook.internal.bj.a(r10, r0, r9);	 Catch:{ JSONException -> 0x00e8 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ JSONException -> 0x00e8 }
        r9 = r0;	 Catch:{ JSONException -> 0x00e8 }
        goto L_0x00dc;	 Catch:{ JSONException -> 0x00e8 }
    L_0x00db:
        r9 = r14;	 Catch:{ JSONException -> 0x00e8 }
    L_0x00dc:
        r13 = 0;	 Catch:{ JSONException -> 0x00e8 }
        r0 = r15;	 Catch:{ JSONException -> 0x00e8 }
        r10 = r16;	 Catch:{ JSONException -> 0x00e8 }
        r11 = r17;	 Catch:{ JSONException -> 0x00e8 }
        r12 = r18;	 Catch:{ JSONException -> 0x00e8 }
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);	 Catch:{ JSONException -> 0x00e8 }
        return r15;
    L_0x00e8:
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookRequestError.a(org.json.JSONObject, java.lang.Object, java.net.HttpURLConnection):com.facebook.FacebookRequestError");
    }

    private static synchronized p h() {
        synchronized (FacebookRequestError.class) {
            s a = u.a(s.j());
            p a2;
            if (a == null) {
                a2 = p.a();
                return a2;
            }
            a2 = a.h();
            return a2;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
    }

    private FacebookRequestError(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), false, null, null, null, null, null);
    }
}
