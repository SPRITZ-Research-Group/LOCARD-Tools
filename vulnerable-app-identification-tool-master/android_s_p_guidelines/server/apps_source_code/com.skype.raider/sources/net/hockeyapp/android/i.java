package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class i {
    public static void a(Activity activity) {
        long now = System.currentTimeMillis();
        if (activity != null && b(activity)) {
            SharedPreferences preferences = activity.getSharedPreferences("HockeyApp", 0);
            long start = preferences.getLong("startTime" + activity.hashCode(), 0);
            long sum = preferences.getLong("usageTime" + a.a, 0);
            if (start > 0) {
                long duration = now - start;
                long newSum = sum + duration;
                if (duration > 0 && newSum >= 0) {
                    Editor editor = preferences.edit();
                    editor.putLong("usageTime" + a.a, newSum);
                    editor.apply();
                }
            }
        }
    }

    public static long a(Context context) {
        if (!b(context)) {
            return 0;
        }
        SharedPreferences preferences = context.getSharedPreferences("HockeyApp", 0);
        long sum = preferences.getLong("usageTime" + a.a, 0);
        if (sum >= 0) {
            return sum / 1000;
        }
        preferences.edit().remove("usageTime" + a.a).apply();
        return 0;
    }

    private static boolean b(Context context) {
        if (a.a == null) {
            a.a(context);
            if (a.a == null) {
                return false;
            }
        }
        return true;
    }
}
