package com.facebook.ads;

import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.r.c;

public final class a {
    public static final a a = new a(Constants.ONE_SECOND, "Network Error");
    public static final a b = new a(1001, "No Fill");
    public static final a c = new a(1002, "Ad was re-loaded too frequently");
    public static final a d = new a(2000, "Server Error");
    public static final a e = new a(2001, "Internal Error");
    public static final a f = new a(2002, "Cache Error");
    public static final a g = new a(3001, "Mediation Error");
    @Deprecated
    public static final a h = new a(2002, "Native ad failed to load due to missing properties");
    public static final a i = new a(2100, "Native ad failed to load its media");
    public static final a j = new a(6003, "unsupported type of ad assets");
    private final int k;
    private final String l;

    private a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "unknown error";
        }
        this.k = i;
        this.l = str;
    }

    public static a a(c cVar) {
        return cVar.a().c() ? new a(cVar.a().a(), cVar.b()) : new a(com.facebook.ads.internal.r.a.UNKNOWN_ERROR.a(), com.facebook.ads.internal.r.a.UNKNOWN_ERROR.b());
    }

    public final int a() {
        return this.k;
    }

    public final String b() {
        return this.l;
    }
}
