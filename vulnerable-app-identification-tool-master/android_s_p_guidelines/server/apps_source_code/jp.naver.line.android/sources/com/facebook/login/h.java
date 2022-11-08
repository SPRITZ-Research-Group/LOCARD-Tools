package com.facebook.login;

import android.os.Bundle;
import defpackage.amm;
import java.util.Map;
import org.json.JSONObject;

final class h {
    private final amm a;
    private String b;
    private String c;

    h(android.content.Context r2, java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.login.h.<init>(android.content.Context, java.lang.String):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = this;
        r1.<init>();
        r1.b = r3;
        r3 = defpackage.amm.b(r2, r3);
        r1.a = r3;
        r2 = r2.getPackageManager();	 Catch:{ NameNotFoundException -> 0x001f }
        if (r2 == 0) goto L_0x001e;	 Catch:{ NameNotFoundException -> 0x001f }
    L_0x0011:
        r3 = "com.facebook.katana";	 Catch:{ NameNotFoundException -> 0x001f }
        r0 = 0;	 Catch:{ NameNotFoundException -> 0x001f }
        r2 = r2.getPackageInfo(r3, r0);	 Catch:{ NameNotFoundException -> 0x001f }
        if (r2 == 0) goto L_0x001e;	 Catch:{ NameNotFoundException -> 0x001f }
    L_0x001a:
        r2 = r2.versionName;	 Catch:{ NameNotFoundException -> 0x001f }
        r1.c = r2;	 Catch:{ NameNotFoundException -> 0x001f }
    L_0x001e:
        return;
    L_0x001f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.h.<init>(android.content.Context, java.lang.String):void");
    }

    public final String a() {
        return this.b;
    }

    private static Bundle a(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", str);
        bundle.putString("3_method", "");
        bundle.putString("2_result", "");
        bundle.putString("5_error_message", "");
        bundle.putString("4_error_code", "");
        bundle.putString("6_extras", "");
        return bundle;
    }

    public final void a(com.facebook.login.LoginClient.Request r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.login.h.a(com.facebook.login.LoginClient$Request):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r0 = r6.e();
        r0 = a(r0);
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x005c }
        r1.<init>();	 Catch:{ JSONException -> 0x005c }
        r2 = "login_behavior";	 Catch:{ JSONException -> 0x005c }
        r3 = r6.b();	 Catch:{ JSONException -> 0x005c }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x005c }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x005c }
        r2 = "request_code";	 Catch:{ JSONException -> 0x005c }
        r3 = com.facebook.login.LoginClient.a();	 Catch:{ JSONException -> 0x005c }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x005c }
        r2 = "permissions";	 Catch:{ JSONException -> 0x005c }
        r3 = ",";	 Catch:{ JSONException -> 0x005c }
        r4 = r6.a();	 Catch:{ JSONException -> 0x005c }
        r3 = android.text.TextUtils.join(r3, r4);	 Catch:{ JSONException -> 0x005c }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x005c }
        r2 = "default_audience";	 Catch:{ JSONException -> 0x005c }
        r3 = r6.c();	 Catch:{ JSONException -> 0x005c }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x005c }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x005c }
        r2 = "isReauthorize";	 Catch:{ JSONException -> 0x005c }
        r6 = r6.f();	 Catch:{ JSONException -> 0x005c }
        r1.put(r2, r6);	 Catch:{ JSONException -> 0x005c }
        r6 = r5.c;	 Catch:{ JSONException -> 0x005c }
        if (r6 == 0) goto L_0x0053;	 Catch:{ JSONException -> 0x005c }
    L_0x004c:
        r6 = "facebookVersion";	 Catch:{ JSONException -> 0x005c }
        r2 = r5.c;	 Catch:{ JSONException -> 0x005c }
        r1.put(r6, r2);	 Catch:{ JSONException -> 0x005c }
    L_0x0053:
        r6 = "6_extras";	 Catch:{ JSONException -> 0x005c }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x005c }
        r0.putString(r6, r1);	 Catch:{ JSONException -> 0x005c }
    L_0x005c:
        r6 = r5.a;
        r1 = "fb_mobile_login_start";
        r6.b(r1, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.h.a(com.facebook.login.LoginClient$Request):void");
    }

    public final void a(java.lang.String r2, java.util.Map<java.lang.String, java.lang.String> r3, com.facebook.login.g r4, java.util.Map<java.lang.String, java.lang.String> r5, java.lang.Exception r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.login.h.a(java.lang.String, java.util.Map, com.facebook.login.g, java.util.Map, java.lang.Exception):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = this;
        r2 = a(r2);
        if (r4 == 0) goto L_0x000f;
    L_0x0006:
        r0 = "2_result";
        r4 = r4.a();
        r2.putString(r0, r4);
    L_0x000f:
        if (r6 == 0) goto L_0x0020;
    L_0x0011:
        r4 = r6.getMessage();
        if (r4 == 0) goto L_0x0020;
    L_0x0017:
        r4 = "5_error_message";
        r6 = r6.getMessage();
        r2.putString(r4, r6);
    L_0x0020:
        r4 = 0;
        r6 = r3.isEmpty();
        if (r6 != 0) goto L_0x002c;
    L_0x0027:
        r4 = new org.json.JSONObject;
        r4.<init>(r3);
    L_0x002c:
        if (r5 == 0) goto L_0x0058;
    L_0x002e:
        if (r4 != 0) goto L_0x0036;
    L_0x0030:
        r3 = new org.json.JSONObject;
        r3.<init>();
        r4 = r3;
    L_0x0036:
        r3 = r5.entrySet();	 Catch:{ JSONException -> 0x0058 }
        r3 = r3.iterator();	 Catch:{ JSONException -> 0x0058 }
    L_0x003e:
        r5 = r3.hasNext();	 Catch:{ JSONException -> 0x0058 }
        if (r5 == 0) goto L_0x0058;	 Catch:{ JSONException -> 0x0058 }
    L_0x0044:
        r5 = r3.next();	 Catch:{ JSONException -> 0x0058 }
        r5 = (java.util.Map.Entry) r5;	 Catch:{ JSONException -> 0x0058 }
        r6 = r5.getKey();	 Catch:{ JSONException -> 0x0058 }
        r6 = (java.lang.String) r6;	 Catch:{ JSONException -> 0x0058 }
        r5 = r5.getValue();	 Catch:{ JSONException -> 0x0058 }
        r4.put(r6, r5);	 Catch:{ JSONException -> 0x0058 }
        goto L_0x003e;
    L_0x0058:
        if (r4 == 0) goto L_0x0063;
    L_0x005a:
        r3 = "6_extras";
        r4 = r4.toString();
        r2.putString(r3, r4);
    L_0x0063:
        r3 = r1.a;
        r4 = "fb_mobile_login_complete";
        r3.b(r4, r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.h.a(java.lang.String, java.util.Map, com.facebook.login.g, java.util.Map, java.lang.Exception):void");
    }

    public final void a(String str, String str2) {
        Bundle a = a(str);
        a.putString("3_method", str2);
        this.a.b("fb_mobile_login_method_start", a);
    }

    public final void a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        Bundle a = a(str);
        if (str3 != null) {
            a.putString("2_result", str3);
        }
        if (str4 != null) {
            a.putString("5_error_message", str4);
        }
        if (str5 != null) {
            a.putString("4_error_code", str5);
        }
        if (!(map == null || map.isEmpty())) {
            a.putString("6_extras", new JSONObject(map).toString());
        }
        a.putString("3_method", str2);
        this.a.b("fb_mobile_login_method_complete", a);
    }

    public final void b(String str, String str2) {
        Bundle a = a(str);
        a.putString("3_method", str2);
        this.a.b("fb_mobile_login_method_not_tried", a);
    }

    public final void c(String str, String str2) {
        a(str, str2, "");
    }

    public final void a(String str, String str2, String str3) {
        Bundle a = a("");
        a.putString("2_result", g.ERROR.a());
        a.putString("5_error_message", str2);
        a.putString("3_method", str3);
        this.a.b(str, a);
    }
}
