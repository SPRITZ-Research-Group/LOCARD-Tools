package net.hockeyapp.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import net.hockeyapp.android.f.c;
import net.hockeyapp.android.f.e;

public final class a {
    public static String a = null;
    public static String b = null;
    public static String c = null;
    public static String d = null;
    public static String e = null;
    public static String f = null;
    public static String g = null;
    static String h = null;
    static CountDownLatch i = new CountDownLatch(1);

    public static Future<String> a() {
        if (i.getCount() == 0) {
            return new c(h);
        }
        return net.hockeyapp.android.f.a.a(new Callable<String>() {
            public final /* synthetic */ Object call() throws Exception {
                a.i.await();
                return a.h;
            }
        });
    }

    public static void a(final Context context) {
        d = VERSION.RELEASE;
        e = Build.DISPLAY;
        f = Build.MODEL;
        g = Build.MANUFACTURER;
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                c = packageInfo.packageName;
                a = packageInfo.versionCode;
                b = packageInfo.versionName;
                int a = a(context, packageManager);
                if (a != 0 && a > packageInfo.versionCode) {
                    a = String.valueOf(a);
                }
            } catch (NameNotFoundException e) {
                e.f();
            }
        }
        if (h == null) {
            net.hockeyapp.android.f.a.a(new AsyncTask<Void, Object, String>() {
                protected final /* synthetic */ void onPostExecute(Object obj) {
                    a.h = (String) obj;
                    a.i.countDown();
                }

                protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("HockeyApp", 0);
                    Object string = sharedPreferences.getString("deviceIdentifier", null);
                    if (string != null) {
                        return string;
                    }
                    String uuid = UUID.randomUUID().toString();
                    sharedPreferences.edit().putString("deviceIdentifier", uuid).apply();
                    return uuid;
                }
            });
        }
    }

    public static File b(Context context) {
        File dir = new File(context.getExternalFilesDir(null), "HockeyApp");
        Object obj = (dir.exists() || dir.mkdirs()) ? 1 : null;
        if (obj == null) {
            e.d();
        }
        return dir;
    }

    private static int a(Context context, PackageManager packageManager) {
        try {
            Bundle metaData = packageManager.getApplicationInfo(context.getPackageName(), 128).metaData;
            if (metaData != null) {
                return metaData.getInt("buildNumber", 0);
            }
            return 0;
        } catch (NameNotFoundException e) {
            e.f();
            return 0;
        }
    }
}
