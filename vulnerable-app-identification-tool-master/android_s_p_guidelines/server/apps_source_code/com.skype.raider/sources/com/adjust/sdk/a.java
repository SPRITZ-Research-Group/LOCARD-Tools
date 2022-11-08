package com.adjust.sdk;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

final class a {
    String A;
    String B;
    Map<String, String> C;
    private boolean D = false;
    String a;
    Boolean b;
    String c;
    String d;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;
    String k;
    String l;
    String m;
    String n;
    String o;
    String p;
    String q;
    String r;
    String s;
    String t;
    String u;
    String v;
    String w;
    String x;
    String y;
    String z;

    a(Context context, String sdkPrefix) {
        String str;
        String str2 = null;
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Util.getLocale(configuration);
        int screenLayout = configuration.screenLayout;
        context.getContentResolver();
        a(context);
        this.h = context.getPackageName();
        this.i = b(context);
        int i = screenLayout & 15;
        switch (i) {
            case 1:
            case 2:
                str = "phone";
                break;
            case 3:
            case 4:
                str = "tablet";
                break;
            default:
                str = null;
                break;
        }
        this.j = str;
        this.k = Build.MODEL;
        this.l = Build.MANUFACTURER;
        this.m = "android";
        this.n = VERSION.RELEASE;
        this.o = VERSION.SDK_INT;
        this.p = locale.getLanguage();
        this.q = locale.getCountry();
        switch (i) {
            case 1:
                str = Constants.SMALL;
                break;
            case 2:
                str = Constants.NORMAL;
                break;
            case 3:
                str = Constants.LARGE;
                break;
            case 4:
                str = Constants.XLARGE;
                break;
            default:
                str = null;
                break;
        }
        this.r = str;
        switch (screenLayout & 48) {
            case 16:
                str = Constants.NORMAL;
                break;
            case 32:
                str = Constants.LONG;
                break;
            default:
                str = null;
                break;
        }
        this.s = str;
        int i2 = displayMetrics.densityDpi;
        if (i2 != 0) {
            if (i2 < 140) {
                str2 = Constants.LOW;
            } else if (i2 > 200) {
                str2 = Constants.HIGH;
            } else {
                str2 = Constants.MEDIUM;
            }
        }
        this.t = str2;
        this.u = String.valueOf(displayMetrics.widthPixels);
        this.v = String.valueOf(displayMetrics.heightPixels);
        if (sdkPrefix == null) {
            str = Constants.CLIENT_SDK;
        } else {
            str = Util.formatString("%s@%s", sdkPrefix, Constants.CLIENT_SDK);
        }
        this.g = str;
        this.f = c(context);
        this.C = Util.getPluginKeys(context);
        this.w = Build.DISPLAY;
        String[] supportedAbis = Util.getSupportedAbis();
        if (supportedAbis == null || supportedAbis.length == 0) {
            str = Util.getCpuAbi();
        } else {
            str = supportedAbis[0];
        }
        this.x = str;
        this.y = Build.ID;
        this.z = Util.getVmInstructionSet();
        this.A = d(context);
        this.B = e(context);
    }

    final void a(Context context) {
        String str = null;
        this.b = Util.isPlayTrackingEnabled(context);
        this.a = Util.getPlayAdId(context);
        if (this.a == null && !this.D) {
            String str2;
            if (!Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE", new Object[0]);
            }
            String macAddress = Util.getMacAddress(context);
            if (macAddress == null) {
                str2 = null;
            } else {
                str2 = Util.sha1(macAddress);
            }
            this.c = str2;
            if (macAddress != null) {
                str = Util.md5(macAddress.replaceAll(":", ""));
            }
            this.d = str;
            this.e = Util.getAndroidId(context);
            this.D = true;
        }
    }

    private static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    private static String c(Context context) {
        try {
            Cursor cursor = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
            if (cursor == null) {
                return null;
            }
            if (cursor.moveToFirst()) {
                String attributionId = cursor.getString(cursor.getColumnIndex("aid"));
                cursor.close();
                return attributionId;
            }
            cursor.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static String d(Context context) {
        try {
            return Util.dateFormatter.format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).firstInstallTime));
        } catch (Exception e) {
            return null;
        }
    }

    private static String e(Context context) {
        try {
            return Util.dateFormatter.format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).lastUpdateTime));
        } catch (Exception e) {
            return null;
        }
    }
}
