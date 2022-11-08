package com.facebook.ads.internal.t;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class a {
    static volatile boolean a = false;
    private static final String b = a.class.getSimpleName();
    private static final Collection<String> c = new HashSet();
    private static final Collection<String> d;
    private static boolean e;
    private static String f;
    private static String g;
    private static String h;
    private static boolean i = false;
    private static boolean j;
    private static boolean k;

    static {
        Collection hashSet = new HashSet();
        d = hashSet;
        hashSet.add("sdk");
        d.add("google_sdk");
        d.add("vbox86p");
        d.add("vbox86tp");
    }

    public static String a() {
        return f;
    }

    public static void a(String str) {
        f = str;
    }

    public static void a(boolean z) {
        i = z;
    }

    public static boolean a(Context context) {
        if (i || e || d.contains(Build.PRODUCT)) {
            return true;
        }
        if (h == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
            CharSequence string = sharedPreferences.getString("deviceIdHash", null);
            h = string;
            if (TextUtils.isEmpty(string)) {
                h = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("deviceIdHash", h).apply();
            }
        }
        if (c.contains(h)) {
            return true;
        }
        String str = h;
        if (!a) {
            a = true;
            new StringBuilder("When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"").append(str).append("\");");
        }
        return false;
    }

    public static String b() {
        return g;
    }

    public static void b(String str) {
        g = str;
    }

    public static void b(boolean z) {
        j = z;
    }

    public static void c() {
        c.clear();
    }

    public static void c(String str) {
        c.add(str);
    }

    public static void c(boolean z) {
        k = z;
    }

    public static boolean d() {
        return j;
    }

    public static boolean e() {
        return k;
    }

    public static boolean f() {
        return i;
    }
}
