package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.f;
import com.facebook.internal.bj;
import com.facebook.login.LoginClient.Request;
import com.facebook.n;
import defpackage.amm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

abstract class LoginMethodHandler implements Parcelable {
    Map<String, String> a;
    protected LoginClient b;

    abstract String a();

    void a(JSONObject jSONObject) throws JSONException {
    }

    boolean a(int i, int i2, Intent intent) {
        return false;
    }

    abstract boolean a(Request request);

    void b() {
    }

    boolean d() {
        return false;
    }

    LoginMethodHandler(LoginClient loginClient) {
        this.b = loginClient;
    }

    LoginMethodHandler(Parcel parcel) {
        this.a = bj.a(parcel);
    }

    final void a(LoginClient loginClient) {
        if (this.b == null) {
            this.b = loginClient;
            return;
        }
        throw new n("Can't set LoginClient if it is already set.");
    }

    protected final String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0_auth_logger_id", str);
            jSONObject.put("3_method", a());
            a(jSONObject);
        } catch (JSONException e) {
            StringBuilder stringBuilder = new StringBuilder("Error creating client state json: ");
            stringBuilder.append(e.getMessage());
            Log.w("LoginMethodHandler", stringBuilder.toString());
        }
        return jSONObject.toString();
    }

    protected final void a(String str, Object obj) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, obj == null ? null : obj.toString());
    }

    protected final void b(String str) {
        String d = this.b.g.d();
        amm b = amm.b(this.b.c.getActivity(), d);
        Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", str);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", d);
        b.b("fb_dialogs_web_login_dialog_complete", bundle);
    }

    static AccessToken a(Bundle bundle, f fVar, String str) {
        Date a = bj.a(bundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0));
        Collection stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
        String string = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
        if (bj.a(string)) {
            return null;
        }
        return new AccessToken(string, str, bundle.getString("com.facebook.platform.extra.USER_ID"), stringArrayList, null, fVar, a, new Date());
    }

    public static AccessToken a(Collection<String> collection, Bundle bundle, f fVar, String str) throws n {
        Date a = bj.a(bundle, "expires_in", new Date());
        String string = bundle.getString("access_token");
        String string2 = bundle.getString("granted_scopes");
        if (!bj.a(string2)) {
            collection = new ArrayList(Arrays.asList(string2.split(",")));
        }
        Collection<String> collection2 = collection;
        String string3 = bundle.getString("denied_scopes");
        Collection arrayList = !bj.a(string3) ? new ArrayList(Arrays.asList(string3.split(","))) : null;
        if (bj.a(string)) {
            return null;
        }
        return new AccessToken(string, str, c(bundle.getString("signed_request")), collection2, arrayList, fVar, a, new Date());
    }

    private static java.lang.String c(java.lang.String r2) throws com.facebook.n {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.login.LoginMethodHandler.c(java.lang.String):java.lang.String. bs: []
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
        if (r2 == 0) goto L_0x0035;
    L_0x0002:
        r0 = r2.isEmpty();
        if (r0 != 0) goto L_0x0035;
    L_0x0008:
        r0 = "\\.";	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r2 = r2.split(r0);	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r0 = r2.length;	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r1 = 2;	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        if (r0 != r1) goto L_0x002d;	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
    L_0x0012:
        r0 = 1;	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r2 = r2[r0];	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r0 = 0;	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r2 = android.util.Base64.decode(r2, r0);	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r0 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r1 = "UTF-8";	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r0.<init>(r2, r1);	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r2 = new org.json.JSONObject;	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r2.<init>(r0);	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r0 = "user_id";	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        r2 = r2.getString(r0);	 Catch:{ UnsupportedEncodingException -> 0x002d, UnsupportedEncodingException -> 0x002d }
        return r2;
    L_0x002d:
        r2 = new com.facebook.n;
        r0 = "Failed to retrieve user_id from signed_request";
        r2.<init>(r0);
        throw r2;
    L_0x0035:
        r2 = new com.facebook.n;
        r0 = "Authorization response does not contain the signed_request";
        r2.<init>(r0);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.LoginMethodHandler.c(java.lang.String):java.lang.String");
    }

    public void writeToParcel(Parcel parcel, int i) {
        bj.a(parcel, this.a);
    }
}
