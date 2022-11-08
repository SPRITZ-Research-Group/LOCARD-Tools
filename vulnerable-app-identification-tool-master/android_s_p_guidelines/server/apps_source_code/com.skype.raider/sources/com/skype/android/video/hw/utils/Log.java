package com.skype.android.video.hw.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Log {
    public static final int ASSERT = 7;
    private static final int CALL_STACK_LEVEL = 4;
    public static final int DEBUG = 3;
    private static final StackTraceElement EMPTY_STACK_TRACE_ELEMENT = new StackTraceElement("<unknown>", "<unknown>", "<unknown>", 0);
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static final int loggingLevel = getLogLevel();

    private static native int getLogLevel();

    private static native void log(String str, int i, String str2, String str3, String str4, int i2, String str5, String str6, String str7);

    public static int v(String tag, String msg) {
        StackTraceElement ste = getStackTrace();
        log(tag, 2, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static int v(String tag, String msg, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 2, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, getStackTraceString(t), null);
        return 0;
    }

    public static int d(String tag, String msg) {
        StackTraceElement ste = getStackTrace();
        log(tag, 3, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static int d(String tag, String msg, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 3, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, getStackTraceString(t), null);
        return 0;
    }

    public static int i(String tag, String msg) {
        StackTraceElement ste = getStackTrace();
        log(tag, 4, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static int i(String tag, String msg, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 4, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, getStackTraceString(t), null);
        return 0;
    }

    public static int w(String tag, String msg) {
        StackTraceElement ste = getStackTrace();
        log(tag, 5, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static int w(String tag, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 5, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), null, getStackTraceString(t), null);
        return 0;
    }

    public static int w(String tag, String msg, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 5, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, getStackTraceString(t), null);
        return 0;
    }

    public static int e(String tag, String msg) {
        StackTraceElement ste = getStackTrace();
        log(tag, 6, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, null);
        return 0;
    }

    public static int e(String tag, String msg, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 6, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, getStackTraceString(t), null);
        return 0;
    }

    public static int wfile(String tag, String msg) {
        StackTraceElement ste = getStackTrace();
        log(tag, 7, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, null, getStackTraceString());
        return 0;
    }

    public static int wfile(String tag, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 7, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), null, getStackTraceString(t), getStackTraceString());
        return 0;
    }

    public static int wfile(String tag, String msg, Throwable t) {
        StackTraceElement ste = getStackTrace();
        log(tag, 7, ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), msg, getStackTraceString(t), getStackTraceString());
        return 0;
    }

    public static int println(int priority, String tag, String msg) {
        return android.util.Log.println(priority, tag, msg);
    }

    public static boolean isLoggable(String tag, int level) {
        return loggingLevel >= 0 && level >= loggingLevel;
    }

    public static String getStackTraceString(Throwable t) {
        Writer writer = new StringWriter();
        try {
            writer.append(t.toString());
            writer.append("\n");
        } catch (IOException e) {
        }
        t.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    private static StackTraceElement getStackTrace() {
        StackTraceElement[] stackDump = Thread.currentThread().getStackTrace();
        if (stackDump == null || stackDump.length <= 4) {
            return EMPTY_STACK_TRACE_ELEMENT;
        }
        return stackDump[4];
    }

    private static String getStackTraceString() {
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
