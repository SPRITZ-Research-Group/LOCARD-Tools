package com.facebook.ads.internal.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.facebook.ads.internal.c.a.c;
import com.facebook.ads.internal.c.c.a;

public final class b {
    public static String a = "";
    public static String b = "";
    public static boolean c = false;
    public static String d = "";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(Context context) {
        a a;
        a a2;
        SharedPreferences sharedPreferences = context.getSharedPreferences("SDKIDFA", 0);
        if (sharedPreferences.contains("attributionId")) {
            a = sharedPreferences.getString("attributionId", "");
        }
        if (sharedPreferences.contains("advertisingId")) {
            b = sharedPreferences.getString("advertisingId", "");
            c = sharedPreferences.getBoolean("limitAdTracking", c);
            d = c.SHARED_PREFS.name();
        }
        try {
            a = c.a(context.getContentResolver());
        } catch (Throwable e) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e, "Error retrieving attribution id from fb4a"));
            a = null;
        }
        if (a != null) {
            if (a.a != null) {
                a = a.a;
            }
        }
        if (com.facebook.ads.internal.q.a.b.a() && com.facebook.ads.internal.q.a.b.b("aid_override")) {
            a = com.facebook.ads.internal.q.a.b.a("aid_override");
        }
        try {
            a2 = a.a(context, a);
        } catch (Throwable e2) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e2, "Error retrieving advertising id from Google Play Services"));
            a2 = null;
        }
        if (a2 != null) {
            String a3 = a2.a();
            Boolean valueOf = Boolean.valueOf(a2.b());
            if (a3 != null) {
                b = a3;
                c = valueOf.booleanValue();
                d = a2.c().name();
            }
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("attributionId", a);
        edit.putString("advertisingId", b);
        edit.putBoolean("limitAdTracking", c);
        edit.apply();
    }
}
