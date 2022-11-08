package com.facebook.ads.internal.g;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.facebook.ads.internal.q.a.e;

public final class b {
    public static final String a = VERSION.RELEASE;
    private final Context b;

    public b(Context context) {
        this.b = context.getApplicationContext();
    }

    public static String a() {
        return (Build.MANUFACTURER == null || Build.MANUFACTURER.length() <= 0) ? "" : Build.MANUFACTURER;
    }

    public static String b() {
        return (Build.MODEL == null || Build.MODEL.length() <= 0) ? "" : Build.MODEL;
    }

    public final String c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null && networkOperatorName.length() > 0) {
                return networkOperatorName;
            }
        }
        return "";
    }

    public final String d() {
        try {
            CharSequence applicationLabel = this.b.getPackageManager().getApplicationLabel(this.b.getPackageManager().getApplicationInfo(f(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                return applicationLabel.toString();
            }
        } catch (NameNotFoundException e) {
        }
        return "";
    }

    public final String e() {
        try {
            String f = f();
            if (f != null && f.length() >= 0) {
                f = this.b.getPackageManager().getInstallerPackageName(f);
                if (f != null && f.length() > 0) {
                    return f;
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public final String f() {
        PendingIntent activity = PendingIntent.getActivity(this.b, 0, new Intent(), 0);
        return VERSION.SDK_INT >= 17 ? activity.getCreatorPackage() : activity.getTargetPackage();
    }

    public final String g() {
        try {
            return this.b.getPackageManager().getPackageInfo(f(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final int h() {
        int i = 0;
        try {
            return this.b.getPackageManager().getPackageInfo(f(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public final boolean i() {
        return this.b.checkCallingOrSelfPermission("android.permission.BIND_ACCESSIBILITY_SERVICE") == 0;
    }

    public final int j() {
        return e.a(this.b);
    }
}
