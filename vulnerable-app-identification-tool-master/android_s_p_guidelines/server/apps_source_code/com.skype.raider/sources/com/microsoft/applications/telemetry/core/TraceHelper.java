package com.microsoft.applications.telemetry.core;

public class TraceHelper extends Log {
    public static void TraceVerbose(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void TraceInformation(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void TraceWarning(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void TraceError(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void TraceError(String tag, String message, Exception e) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message, e);
        }
    }

    public static void TraceWtf(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.wtf(tag, message);
        }
    }

    public static void TraceDebug(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }
}
