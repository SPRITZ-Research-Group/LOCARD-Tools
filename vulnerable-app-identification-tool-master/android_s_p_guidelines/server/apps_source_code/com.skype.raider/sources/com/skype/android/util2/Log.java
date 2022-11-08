package com.skype.android.util2;

public class Log {
    private static final StackTraceElement a = new StackTraceElement("<unknown>", "<unknown>", "<unknown>", 0);
    private static final int b = getLoggingLevel();

    private static native int getLoggingLevel();

    private static native void log(String str, int i, String str2, String str3, String str4, int i2, String str5, String str6, String str7);

    public static int a(String tag, String msg) {
        StackTraceElement ste = a();
        log(tag, 3, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static int b(String tag, String msg) {
        StackTraceElement ste = a();
        log(tag, 4, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static int c(String tag, String msg) {
        StackTraceElement ste = a();
        log(tag, 6, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static boolean a(int level) {
        return b >= 0 && level > b;
    }

    private static StackTraceElement a() {
        StackTraceElement[] stackDump = Thread.currentThread().getStackTrace();
        if (stackDump == null || stackDump.length <= 4) {
            return a;
        }
        return stackDump[4];
    }
}
