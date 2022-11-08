package com.facebook.ads.internal.l;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static a a;
    private final SharedPreferences b;

    private a(Context context) {
        this.b = context.getApplicationContext().getSharedPreferences("com.facebook.ads.FEATURE_CONFIG", 0);
    }

    private static int a(Context context, String str, int i) {
        int a = w(context).a(str, i);
        return (a < 0 || a >= 101) ? i : a;
    }

    private int a(String str, int i) {
        String string = this.b.getString(str, String.valueOf(i));
        try {
            return string.equals("null") ? i : Integer.valueOf(string).intValue();
        } catch (NumberFormatException e) {
            return i;
        }
    }

    @Nullable
    private String a(String str, String str2) {
        String string = this.b.getString(str, str2);
        return (string == null || string.equals("null")) ? str2 : string;
    }

    public static boolean a(Context context) {
        return VERSION.SDK_INT >= 14 && b("com.google.android.exoplayer2", "ExoPlayer") && w(context).b("adnw_enable_exoplayer");
    }

    public static boolean b(Context context) {
        return VERSION.SDK_INT >= 18 && w(context).b("adnw_enable_debug_overlay");
    }

    private boolean b(String str) {
        String string = this.b.getString(str, "false");
        return string.equals("null") ? false : Boolean.valueOf(string).booleanValue();
    }

    private static boolean b(String str, String str2) {
        try {
            Class.forName(str + "." + str2);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private long c(String str) {
        String string = this.b.getString(str, "500");
        try {
            return string.equals("null") ? 500 : Long.valueOf(string).longValue();
        } catch (NumberFormatException e) {
            return 500;
        }
    }

    public static boolean c(Context context) {
        return w(context).b("adnw_block_lockscreen");
    }

    public static boolean d(Context context) {
        return w(context).b("adnw_android_memory_opt");
    }

    public static boolean e(Context context) {
        return w(context).b("adnw_android_disable_blur");
    }

    public static boolean f(Context context) {
        return w(context).b("adnw_android_disable_playable_precache");
    }

    public static boolean g(Context context) {
        return VERSION.SDK_INT >= 19 && w(context).b("adnw_enable_iab");
    }

    public static boolean h(Context context) {
        return w(context).b("adnw_debug_logging");
    }

    public static Set<String> i(Context context) {
        String a = w(context).a("additional_debug_logging_black_list", "");
        Set hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(a);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
        } catch (JSONException e) {
        }
        return hashSet;
    }

    public static int j(Context context) {
        return a(context, "additional_debug_logging_black_list_percentage", 0);
    }

    public static int k(Context context) {
        return a(context, "additional_debug_logging_sampling_percentage", 100);
    }

    public static long l(Context context) {
        return w(context).c("unified_logging_immediate_delay_ms");
    }

    public static long m(Context context) {
        return ((long) w(context).a("unified_logging_dispatch_interval_seconds", 300)) * 1000;
    }

    public static int n(Context context) {
        return w(context).a("unified_logging_event_limit", -1);
    }

    public static boolean o(Context context) {
        return w(context).a("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }

    public static int p(Context context) {
        return w(context).a("minimum_elapsed_time_after_impression", -1);
    }

    public static int q(Context context) {
        return w(context).a("stack_trace_sample_rate", 0);
    }

    public static boolean r(Context context) {
        return w(context).b("adnw_top_activity_viewability");
    }

    public static boolean s(Context context) {
        return w(context).b("adnw_enhanced_viewability_area_check");
    }

    public static boolean t(Context context) {
        return w(context).b("adnw_viewability_check_area_based");
    }

    @Nullable
    public static String u(Context context) {
        return w(context).a("adnw_logging_endpoint_prefix", "www");
    }

    public static boolean v(Context context) {
        return w(context).b("adnw_mapp_markup_impression_after_image_load");
    }

    public static a w(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public final void a(@Nullable String str) {
        if (str != null && !str.isEmpty() && !str.equals("[]")) {
            Editor edit = this.b.edit();
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                edit.putString(str2, jSONObject.getString(str2));
            }
            edit.apply();
        }
    }
}
