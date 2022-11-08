package com.facebook;

import android.content.SharedPreferences;
import com.facebook.internal.bn;
import com.google.android.gms.common.Scopes;
import org.json.JSONObject;

final class aj {
    private final SharedPreferences a = s.f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);

    aj() {
    }

    final com.facebook.Profile a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.aj.a():com.facebook.Profile. bs: []
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
        r1 = "com.facebook.ProfileManager.CachedProfile";
        r2 = 0;
        r0 = r0.getString(r1, r2);
        if (r0 == 0) goto L_0x0016;
    L_0x000b:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0016 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0016 }
        r0 = new com.facebook.Profile;	 Catch:{ JSONException -> 0x0016 }
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0016 }
        return r0;
    L_0x0016:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.aj.a():com.facebook.Profile");
    }

    final void a(Profile profile) {
        bn.a((Object) profile, Scopes.PROFILE);
        JSONObject d = profile.d();
        if (d != null) {
            this.a.edit().putString("com.facebook.ProfileManager.CachedProfile", d.toString()).apply();
        }
    }

    final void b() {
        this.a.edit().remove("com.facebook.ProfileManager.CachedProfile").apply();
    }
}
