package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.UserManager;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.y;
import com.google.android.gms.common.m.a;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.g;
import com.google.android.gms.common.util.h;
import com.google.android.gms.common.util.m;
import com.google.android.gms.common.util.q;
import java.util.concurrent.atomic.AtomicBoolean;

public class k {
    private static boolean a = false;
    @Deprecated
    public static final int b = 12451000;
    @VisibleForTesting
    public static boolean c = false;
    @VisibleForTesting
    public static boolean d = false;
    @VisibleForTesting
    static final AtomicBoolean e = new AtomicBoolean();
    @VisibleForTesting
    private static boolean f = false;
    private static final AtomicBoolean g = new AtomicBoolean();

    @Deprecated
    public static int a(Context context, int i) {
        try {
            context.getResources().getString(a.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
        }
        if (!("com.google.android.gms".equals(context.getPackageName()) || g.get())) {
            int b = y.b(context);
            if (b == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (b != b) {
                throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + b + " but found " + b + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
        }
        boolean z = (g.b(context) || g.c(context)) ? false : true;
        return a(context, z, i);
    }

    @VisibleForTesting
    private static int a(Context context, boolean z, int i) {
        ab.b(i >= 0);
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        if (z) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (NameNotFoundException e) {
                return 9;
            }
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            l.a(context);
            if (!l.a(packageInfo2, true)) {
                return 9;
            }
            if (z && (!l.a(packageInfo, true) || !packageInfo.signatures[0].equals(packageInfo2.signatures[0]))) {
                return 9;
            }
            if (h.a(packageInfo2.versionCode) < h.a(i)) {
                new StringBuilder(77).append("Google Play services out of date.  Requires ").append(i).append(" but found ").append(packageInfo2.versionCode);
                return 2;
            }
            ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
            if (applicationInfo == null) {
                try {
                    applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                } catch (NameNotFoundException e2) {
                    return 1;
                }
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (NameNotFoundException e3) {
            return 1;
        }
    }

    @Deprecated
    @VisibleForTesting
    public static String a(int i) {
        return ConnectionResult.a(i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(Context context) {
        if (!f) {
            try {
                PackageInfo b = c.a(context).b("com.google.android.gms", 64);
                l.a(context);
                if (b == null || l.a(b, false) || !l.a(b, true)) {
                    a = false;
                } else {
                    a = true;
                }
                f = true;
            } catch (NameNotFoundException e) {
            } catch (Throwable th) {
                f = true;
            }
        }
        return a;
    }

    @TargetApi(19)
    @Deprecated
    public static boolean a(Context context, int i, String str) {
        return q.a(context, i, str);
    }

    @TargetApi(21)
    static boolean a(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        if (m.c()) {
            try {
                for (SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                    if (str.equals(appPackageName.getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            if (applicationInfo.enabled) {
                Object obj;
                if (VERSION.SDK_INT >= 18) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    Bundle applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
                    if (applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"))) {
                        obj = 1;
                        if (obj == null) {
                            return true;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e2) {
            return false;
        }
    }

    @Deprecated
    public static boolean b(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static boolean b(Context context, int i) {
        return q.a(context, i);
    }

    public static boolean c(Context context) {
        if (!a(context)) {
            if (c ? d : "user".equals(Build.TYPE)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public static boolean c(Context context, int i) {
        return i == 18 ? true : i == 1 ? a(context, "com.google.android.gms") : false;
    }

    public static Resources d(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Context e(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public static int f(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }
}
