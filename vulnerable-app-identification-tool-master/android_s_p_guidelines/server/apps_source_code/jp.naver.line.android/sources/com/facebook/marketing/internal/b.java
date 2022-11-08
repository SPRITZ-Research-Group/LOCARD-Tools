package com.facebook.marketing.internal;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.facebook.s;
import org.apache.cordova.networkinformation.NetworkManager;

public class b {
    private static final String a = b.class.getCanonicalName();

    public static String a() {
        Context f = s.f();
        try {
            return f.getPackageManager().getPackageInfo(f.getPackageName(), 0).versionName;
        } catch (Throwable e) {
            Log.e(a, "Failed to get app version.", e);
            return "";
        }
    }

    public static boolean b() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith(NetworkManager.TYPE_UNKNOWN) || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT));
    }
}
