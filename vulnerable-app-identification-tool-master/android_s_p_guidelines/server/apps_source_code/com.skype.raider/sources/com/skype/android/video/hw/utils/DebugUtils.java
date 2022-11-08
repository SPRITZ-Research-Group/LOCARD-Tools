package com.skype.android.video.hw.utils;

public final class DebugUtils {
    private static final int INDEX = 3;

    public static String getMethodName() {
        StackTraceElement ste;
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        if (3 < callStack.length) {
            ste = callStack[3];
        } else {
            ste = null;
        }
        if (ste != null) {
            return ste.getMethodName();
        }
        return null;
    }
}
