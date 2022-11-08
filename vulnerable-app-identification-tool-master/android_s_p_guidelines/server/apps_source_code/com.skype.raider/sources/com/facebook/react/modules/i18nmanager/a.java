package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.v4.text.g;
import java.util.Locale;

public final class a {
    private static a a = null;

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public static boolean a(Context context) {
        if (a(context, "RCTI18nUtil_forceRTL", false)) {
            return true;
        }
        if (a(context, "RCTI18nUtil_allowRTL", true)) {
            boolean z;
            if (g.a(Locale.getDefault()) == 1) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static void a(Context context, boolean allowRTL) {
        b(context, "RCTI18nUtil_allowRTL", allowRTL);
    }

    public static void b(Context context, boolean forceRTL) {
        b(context, "RCTI18nUtil_forceRTL", forceRTL);
    }

    private static boolean a(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).getBoolean(key, defaultValue);
    }

    private static void b(Context context, String key, boolean value) {
        Editor editor = context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
