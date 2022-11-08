package a.a;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.a.a;
import com.appboy.f.c;
import com.appboy.f.i;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TimeZone;

public class aj implements an {
    private static final String b = c.a(aj.class);
    final SharedPreferences a;
    private final Context c;
    private final ao d;
    private final cb e;
    private String f;

    public aj(Context context, a aVar, String str, ao aoVar, cb cbVar) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.c = context;
        this.d = aoVar;
        this.e = cbVar;
        this.a = context.getSharedPreferences("com.appboy.storage.device_ad_info" + i.a(context, str, aVar.b().toString()), 0);
    }

    public final bg a() {
        String valueOf = String.valueOf(VERSION.SDK_INT);
        String f = f();
        String str = Build.MODEL;
        String locale = Locale.getDefault().toString();
        String id = TimeZone.getDefault().getID();
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return new bg(valueOf, f, str, locale, id, displayMetrics.widthPixels + "x" + displayMetrics.heightPixels, Boolean.valueOf(e()));
    }

    public final bg b() {
        this.e.a(a());
        return (bg) this.e.b();
    }

    public final String c() {
        String a = this.d.a();
        if (a == null) {
            c.g(b, "Error reading deviceId, received a null value.");
        }
        return a;
    }

    @VisibleForTesting
    private boolean e() {
        if (VERSION.SDK_INT >= 24) {
            NotificationManager notificationManager = (NotificationManager) this.c.getSystemService("notification");
            if (notificationManager != null) {
                return notificationManager.areNotificationsEnabled();
            }
            return true;
        } else if (VERSION.SDK_INT < 19) {
            return true;
        } else {
            try {
                Method a = cx.a("android.support.v4.app.NotificationManagerCompat", "from", Context.class);
                if (a == null) {
                    return true;
                }
                Object a2 = cx.a(null, a, this.c);
                if (a2 == null) {
                    return true;
                }
                Method a3 = cx.a(a2.getClass(), "areNotificationsEnabled", new Class[0]);
                if (a3 == null) {
                    return true;
                }
                a2 = cx.a(a2, a3, new Object[0]);
                return (a2 == null || !(a2 instanceof Boolean)) ? true : ((Boolean) a2).booleanValue();
            } catch (Throwable e) {
                c.d(b, "Failed to read notifications enabled state from NotificationManagerCompat.", e);
                return true;
            }
        }
    }

    private String f() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.c.getSystemService("phone");
            switch (telephonyManager.getPhoneType()) {
                case 0:
                    return null;
                case 1:
                case 2:
                    return telephonyManager.getNetworkOperatorName();
                default:
                    c.f(b, "Unknown phone type");
                    return null;
            }
        } catch (Throwable e) {
            c.d(b, "Caught resources not found exception while reading the phone carrier name.", e);
            return null;
        } catch (Throwable e2) {
            c.d(b, "Caught security exception while reading the phone carrier name.", e2);
            return null;
        }
    }

    public final String d() {
        if (this.f != null) {
            return this.f;
        }
        PackageInfo packageInfo;
        String packageName = this.c.getPackageName();
        try {
            packageInfo = this.c.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Throwable e) {
            c.d(b, "Unable to inspect package [" + packageName + "]", e);
            packageInfo = null;
        }
        if (packageInfo == null) {
            packageInfo = this.c.getPackageManager().getPackageArchiveInfo(this.c.getApplicationInfo().sourceDir, 0);
        }
        if (packageInfo != null) {
            this.f = packageInfo.versionName;
            return this.f;
        }
        c.b(b, "App version could not be read. Returning null");
        return null;
    }
}
