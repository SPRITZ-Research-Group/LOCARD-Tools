package com.appboy.f;

import a.a.as;
import a.a.cy;
import android.util.Log;

public class c {
    private static as a;
    private static final String b = a(c.class);
    private static boolean c;
    private static int d = 4;
    private static final int e = 15;
    private static final int f = (80 - e);

    public static synchronized void a() {
        synchronized (c.class) {
            String a = cy.a("log.tag.APPBOY", "");
            if (!i.c(a) && a.trim().equalsIgnoreCase("verbose")) {
                c = true;
                d = 2;
                a(b, "AppboyLogger log level set to " + a + " via device system property. Note that subsequent calls to AppboyLogger.setLogLevel() will have no effect.", null, true);
            }
        }
    }

    public static int a(String tag, String msg) {
        if (d <= 2) {
            return Log.v(tag, msg);
        }
        return 0;
    }

    public static int a(String tag, String msg, Throwable tr) {
        return a(tag, msg, tr, true);
    }

    public static int c(String tag, String msg) {
        return a(tag, msg, null, false);
    }

    private static int a(String tag, String msg, Throwable tr, boolean includeInTestUserLog) {
        if (includeInTestUserLog) {
            e(tag, msg, null);
        }
        if (d > 3) {
            return 0;
        }
        if (tr != null) {
            return Log.d(tag, msg, tr);
        }
        return Log.d(tag, msg);
    }

    public static int e(String tag, String msg) {
        return a(tag, msg, null, false);
    }

    public static int f(String tag, String msg) {
        e(tag, msg, null);
        if (d <= 5) {
            return Log.w(tag, msg);
        }
        return 0;
    }

    public static int c(String tag, String msg, Throwable tr) {
        e(tag, msg, tr);
        if (d <= 5) {
            return Log.w(tag, msg, tr);
        }
        return 0;
    }

    public static int g(String tag, String msg) {
        e(tag, msg, null);
        if (d <= 6) {
            return Log.e(tag, msg);
        }
        return 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        e(tag, msg, null);
        if (d <= 6) {
            return Log.e(tag, msg, tr);
        }
        return 0;
    }

    public static String a(Class classForTag) {
        String name = classForTag.getName();
        int length = name.length();
        if (length > f) {
            name = name.substring(length - f);
        }
        return "Appboy v2.5.1 ." + name;
    }

    public static void a(as testUserDeviceLoggingManager) {
        a = testUserDeviceLoggingManager;
    }

    private static void e(String str, String str2, Throwable th) {
        if (a != null && a.a() && str != null) {
            a.a(str, str2, th);
        }
    }

    public static int b(String tag, String msg) {
        return a(tag, msg, null, true);
    }

    public static int d(String tag, String msg) {
        return a(tag, msg, null, true);
    }

    public static int b(String tag, String msg, Throwable tr) {
        e(tag, msg, null);
        if (d <= 4) {
            return Log.i(tag, msg, tr);
        }
        return 0;
    }
}
