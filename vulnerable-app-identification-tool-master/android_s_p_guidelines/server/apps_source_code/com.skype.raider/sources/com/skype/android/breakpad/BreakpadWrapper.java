package com.skype.android.breakpad;

import java.util.concurrent.atomic.AtomicBoolean;

public class BreakpadWrapper {
    private static final BreakpadWrapper a = new BreakpadWrapper();
    private static final AtomicBoolean b = new AtomicBoolean(false);

    private static native synchronized void init(String str, String str2, String str3, boolean z, boolean z2);

    static {
        System.loadLibrary("BreakpadIntegration");
    }

    public static BreakpadWrapper a() {
        return a;
    }

    private BreakpadWrapper() {
    }

    public static void a(String path, String versionName, String versionCode) {
        init(path, versionName, versionCode, false, false);
    }
}
