package com.facebook;

import android.content.SharedPreferences;
import android.os.Bundle;

final class b {
    private final SharedPreferences a;
    private final c b;
    private ah c;

    private b(SharedPreferences sharedPreferences, c cVar) {
        this.a = sharedPreferences;
        this.b = cVar;
    }

    public b() {
        this(s.f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new c());
    }

    public final void a(com.facebook.AccessToken r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.b.a(com.facebook.AccessToken):void. bs: []
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
        r2 = this;
        r0 = "accessToken";
        com.facebook.internal.bn.a(r3, r0);
        r3 = r3.m();	 Catch:{ JSONException -> 0x001d }
        r0 = r2.a;	 Catch:{ JSONException -> 0x001d }
        r0 = r0.edit();	 Catch:{ JSONException -> 0x001d }
        r1 = "com.facebook.AccessTokenManager.CachedAccessToken";	 Catch:{ JSONException -> 0x001d }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x001d }
        r3 = r0.putString(r1, r3);	 Catch:{ JSONException -> 0x001d }
        r3.apply();	 Catch:{ JSONException -> 0x001d }
        return;
    L_0x001d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.b.a(com.facebook.AccessToken):void");
    }

    public final void b() {
        this.a.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
        if (s.c()) {
            d().b();
        }
    }

    private com.facebook.AccessToken c() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.b.c():com.facebook.AccessToken. bs: []
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
        r0 = r3.a;
        r1 = "com.facebook.AccessTokenManager.CachedAccessToken";
        r2 = 0;
        r0 = r0.getString(r1, r2);
        if (r0 == 0) goto L_0x0016;
    L_0x000b:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0015 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0015 }
        r0 = com.facebook.AccessToken.a(r1);	 Catch:{ JSONException -> 0x0015 }
        return r0;
    L_0x0015:
        return r2;
    L_0x0016:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.b.c():com.facebook.AccessToken");
    }

    private ah d() {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = new ah(s.f());
                }
            }
        }
        return this.c;
    }

    public final AccessToken a() {
        AccessToken accessToken = null;
        if (this.a.contains("com.facebook.AccessTokenManager.CachedAccessToken")) {
            return c();
        }
        if (!s.c()) {
            return null;
        }
        Bundle a = d().a();
        if (a != null && ah.a(a)) {
            accessToken = AccessToken.a(a);
        }
        if (accessToken == null) {
            return accessToken;
        }
        a(accessToken);
        d().b();
        return accessToken;
    }
}
