package com.slowpath.hockeyapp;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import net.hockeyapp.android.i;

final class f {
    private static a a = new a();
    private static boolean b = false;

    private static final class a implements ActivityLifecycleCallbacks {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
            if (!a(activity)) {
                long currentTimeMillis = System.currentTimeMillis();
                if (activity != null) {
                    Editor edit = activity.getSharedPreferences("HockeyApp", 0).edit();
                    edit.putLong("startTime" + activity.hashCode(), currentTimeMillis);
                    edit.apply();
                }
            }
        }

        public final void onActivityPaused(Activity activity) {
            if (!a(activity)) {
                i.a(activity);
            }
        }

        public final void onActivityStopped(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        private static boolean a(Activity activity) {
            return activity.getClass().getPackage().getName().startsWith("net.hockeyapp.android");
        }
    }

    public static void a(Application app) {
        if (!b) {
            app.registerActivityLifecycleCallbacks(a);
            b = true;
        }
    }
}
