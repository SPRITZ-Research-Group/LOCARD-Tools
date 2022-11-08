package com.microsoft.dl.utils;

import com.microsoft.dl.BuildInfo;
import com.microsoft.dl.BuildInfo.Flavour;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public final class Log {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static final StackTraceElement a = new StackTraceElement("<unknown>", "<unknown>", "<unknown>", 0);
    private static final StackTraceElement b = new StackTraceElement("", "", "", 0);
    private static final boolean c;

    private static native int getMinLoggingLevel();

    private static native void log(String str, int i, String str2, String str3, String str4, int i2, String str5, String str6, String str7);

    static {
        boolean z = false;
        if (BuildInfo.FLAVOUR == Flavour.DEBUG) {
            z = true;
        }
        c = z;
    }

    public static void d(String tag, String msg) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 3, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
    }

    public static void d(String tag, String msg, Throwable t) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 3, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, a(t), null);
    }

    public static void i(String tag, String msg) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 4, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
    }

    public static void i(String tag, String msg, Throwable t) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 4, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, a(t), null);
    }

    public static void w(String tag, String msg) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 5, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
    }

    public static void w(String tag, String msg, Throwable t) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 5, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, a(t), null);
    }

    public static void e(String tag, String msg) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 6, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
    }

    public static void e(String tag, String msg, Throwable t) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 6, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, a(t), null);
    }

    public static void a(String tag, String msg) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 7, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, b());
    }

    public static void a(String tag, String msg, Throwable t) {
        StackTraceElement ste = c ? a() : b;
        a(tag, 7, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, a(t), b());
    }

    public static boolean isLoggable(String tag, int level) {
        try {
            int minLogLevel = getMinLoggingLevel();
            if (minLogLevel < 0 || level < minLogLevel) {
                return false;
            }
            return true;
        } catch (UnsatisfiedLinkError e) {
            return false;
        }
    }

    public static void log(int level, String tag, String msg) {
        if (isLoggable(tag, level)) {
            StackTraceElement ste = c ? a() : b;
            a(tag, level, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        }
    }

    public static void log(int level, String tag, String msg, Throwable t) {
        if (isLoggable(tag, level)) {
            StackTraceElement ste = c ? a() : b;
            a(tag, level, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, a(t), null);
        }
    }

    private static void a(String tag, int level, String fileName, String className, String methodName, int lineNum, String msg, String exception, String callStack) {
        try {
            log(tag, level, fileName, className, methodName, lineNum, msg, exception, callStack);
        } catch (UnsatisfiedLinkError e) {
        }
    }

    private static String a(Throwable t) {
        Writer writer = new StringWriter();
        try {
            writer.append(t.toString());
            writer.append("\n");
        } catch (IOException e) {
        }
        t.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    private static StackTraceElement a() {
        StackTraceElement[] stackDump = Thread.currentThread().getStackTrace();
        if (stackDump == null || stackDump.length <= 4) {
            return a;
        }
        return stackDump[4];
    }

    private static String b() {
        StringBuilder str = new StringBuilder();
        StackTraceElement[] stackDump = Thread.currentThread().getStackTrace();
        for (int i = 4; i < stackDump.length; i++) {
            StackTraceElement ste = stackDump[i];
            if (str.length() > 0) {
                str.append(10);
            }
            str.append("\t");
            str.append(ste.getClassName());
            str.append('.');
            str.append(ste.getMethodName());
            str.append('(');
            if (ste.isNativeMethod()) {
                str.append("Native method");
            } else {
                str.append(ste.getFileName());
                str.append(':');
                if (ste.getLineNumber() > 0) {
                    str.append(ste.getLineNumber());
                } else {
                    str.append("?");
                }
            }
            str.append(')');
        }
        return str.toString();
    }
}
