package com.facebook.common.logging;

public class FLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static b sHandler = a.a();

    public static void setLoggingDelegate(b delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException();
        }
        sHandler = delegate;
    }

    public static boolean isLoggable(int level) {
        return sHandler.b(level);
    }

    public static void setMinimumLoggingLevel(int level) {
        sHandler.a(level);
    }

    public static int getMinimumLoggingLevel() {
        return sHandler.b();
    }

    public static void v(String tag, String msg) {
        if (sHandler.b(2)) {
            sHandler.a(tag, msg);
        }
    }

    public static void v(String tag, String msg, Object arg1) {
        if (sHandler.b(2)) {
            sHandler.a(tag, formatString(msg, arg1));
        }
    }

    public static void v(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.b(2)) {
            sHandler.a(tag, formatString(msg, arg1, arg2));
        }
    }

    public static void v(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.b(2)) {
            sHandler.a(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void v(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.b(2)) {
            sHandler.a(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(Class<?> cls, String msg) {
        if (sHandler.b(2)) {
            sHandler.a(getTag(cls), msg);
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1) {
        if (sHandler.b(2)) {
            sHandler.a(getTag(cls), formatString(msg, arg1));
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.b(2)) {
            sHandler.a(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (isLoggable(2)) {
            v((Class) cls, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.b(2)) {
            sHandler.a(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(String tag, String msg, Object... args) {
        if (sHandler.b(2)) {
            sHandler.a(tag, formatString(msg, args));
        }
    }

    public static void v(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.b(2)) {
            sHandler.a(tag, formatString(msg, args), tr);
        }
    }

    public static void v(Class<?> cls, String msg, Object... args) {
        if (sHandler.b(2)) {
            sHandler.a(getTag(cls), formatString(msg, args));
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.b(2)) {
            sHandler.a(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (sHandler.b(2)) {
            sHandler.a(tag, msg, tr);
        }
    }

    public static void v(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.b(2)) {
            sHandler.a(getTag(cls), msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (sHandler.b(3)) {
            sHandler.b(tag, msg);
        }
    }

    public static void d(String tag, String msg, Object arg1) {
        if (sHandler.b(3)) {
            sHandler.b(tag, formatString(msg, arg1));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.b(3)) {
            sHandler.b(tag, formatString(msg, arg1, arg2));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.b(3)) {
            sHandler.b(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.b(3)) {
            sHandler.b(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(Class<?> cls, String msg) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), msg);
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), formatString(msg, arg1));
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(String tag, String msg, Object... args) {
        if (sHandler.b(3)) {
            d(tag, formatString(msg, args));
        }
    }

    public static void d(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.b(3)) {
            d(tag, formatString(msg, args), tr);
        }
    }

    public static void d(Class<?> cls, String msg, Object... args) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), formatString(msg, args));
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (sHandler.b(3)) {
            sHandler.b(tag, msg, tr);
        }
    }

    public static void d(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.b(3)) {
            sHandler.b(getTag(cls), msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (sHandler.b(4)) {
            sHandler.c(tag, msg);
        }
    }

    public static void i(String tag, String msg, Object arg1) {
        if (sHandler.b(4)) {
            sHandler.c(tag, formatString(msg, arg1));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.b(4)) {
            sHandler.c(tag, formatString(msg, arg1, arg2));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.b(4)) {
            sHandler.c(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.b(4)) {
            sHandler.c(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(Class<?> cls, String msg) {
        if (sHandler.b(4)) {
            sHandler.c(getTag(cls), msg);
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1) {
        if (sHandler.b(4)) {
            sHandler.c(getTag(cls), formatString(msg, arg1));
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.b(4)) {
            sHandler.c(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.b(4)) {
            sHandler.c(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.b(4)) {
            sHandler.c(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(String tag, String msg, Object... args) {
        if (sHandler.b(4)) {
            sHandler.c(tag, formatString(msg, args));
        }
    }

    public static void i(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.b(4)) {
            sHandler.c(tag, formatString(msg, args), tr);
        }
    }

    public static void i(Class<?> cls, String msg, Object... args) {
        if (sHandler.b(4)) {
            sHandler.c(getTag(cls), formatString(msg, args));
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(4)) {
            sHandler.c(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (sHandler.b(4)) {
            sHandler.c(tag, msg, tr);
        }
    }

    public static void i(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.b(4)) {
            sHandler.c(getTag(cls), msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (sHandler.b(5)) {
            sHandler.d(tag, msg);
        }
    }

    public static void w(Class<?> cls, String msg) {
        if (sHandler.b(5)) {
            sHandler.d(getTag(cls), msg);
        }
    }

    public static void w(String tag, String msg, Object... args) {
        if (sHandler.b(5)) {
            sHandler.d(tag, formatString(msg, args));
        }
    }

    public static void w(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.b(5)) {
            sHandler.d(tag, formatString(msg, args), tr);
        }
    }

    public static void w(Class<?> cls, String msg, Object... args) {
        if (sHandler.b(5)) {
            sHandler.d(getTag(cls), formatString(msg, args));
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(5)) {
            w((Class) cls, formatString(msg, args), tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (sHandler.b(5)) {
            sHandler.d(tag, msg, tr);
        }
    }

    public static void w(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.b(5)) {
            sHandler.d(getTag(cls), msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (sHandler.b(6)) {
            sHandler.e(tag, msg);
        }
    }

    public static void e(Class<?> cls, String msg) {
        if (sHandler.b(6)) {
            sHandler.e(getTag(cls), msg);
        }
    }

    public static void e(String tag, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.e(tag, formatString(msg, args));
        }
    }

    public static void e(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.e(tag, formatString(msg, args), tr);
        }
    }

    public static void e(Class<?> cls, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.e(getTag(cls), formatString(msg, args));
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.e(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (sHandler.b(6)) {
            sHandler.e(tag, msg, tr);
        }
    }

    public static void e(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.b(6)) {
            sHandler.e(getTag(cls), msg, tr);
        }
    }

    public static void wtf(String tag, String msg) {
        if (sHandler.b(6)) {
            sHandler.f(tag, msg);
        }
    }

    public static void wtf(Class<?> cls, String msg) {
        if (sHandler.b(6)) {
            sHandler.f(getTag(cls), msg);
        }
    }

    public static void wtf(String tag, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.f(tag, formatString(msg, args));
        }
    }

    public static void wtf(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.f(tag, formatString(msg, args), tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.f(getTag(cls), formatString(msg, args));
        }
    }

    public static void wtf(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.b(6)) {
            sHandler.f(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (sHandler.b(6)) {
            sHandler.f(tag, msg, tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.b(6)) {
            sHandler.f(getTag(cls), msg, tr);
        }
    }

    private static String formatString(String str, Object... args) {
        return String.format(null, str, args);
    }

    private static String getTag(Class<?> clazz) {
        return clazz.getSimpleName();
    }
}
