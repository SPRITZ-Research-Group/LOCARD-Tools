package com.facebook.react.bridge;

import android.os.SystemClock;
import com.facebook.common.logging.FLog;

public final class ak {
    private static long a;
    private static boolean b;
    private long c;

    public static void a() {
        if (b) {
            throw new IllegalStateException("Shouldn't be initialized twice");
        }
        b = true;
        a = 100;
    }

    public final void b() {
        if (b) {
            this.c = SystemClock.uptimeMillis();
        }
    }

    public final void a(String module, String method) {
        if (b) {
            long duration = SystemClock.uptimeMillis() - this.c;
            if (duration > a) {
                FLog.w("ReactMethodWatchdog", "Method " + module + "." + method + " invocation took too long: " + duration + "ms");
            }
        }
    }
}
