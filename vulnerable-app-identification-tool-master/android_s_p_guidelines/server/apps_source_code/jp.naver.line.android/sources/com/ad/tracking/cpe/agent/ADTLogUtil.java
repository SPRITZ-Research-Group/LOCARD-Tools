package com.ad.tracking.cpe.agent;

import android.util.Log;

public class ADTLogUtil {
    public static boolean isDebug = false;

    public static void d(String str) {
    }

    public static void v(String str) {
    }

    public static void i(String str) {
        if (isDebug) {
            Log.i(ADTConstants.TAG, str);
        }
    }

    public static void e(String str) {
        if (isDebug) {
            Log.e(ADTConstants.TAG, str);
        }
    }

    public static void i(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }
}
