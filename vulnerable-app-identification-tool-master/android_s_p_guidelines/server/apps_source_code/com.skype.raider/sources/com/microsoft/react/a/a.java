package com.microsoft.react.a;

import android.os.Looper;
import com.facebook.common.logging.FLog;

final class a {
    private final String a;
    private final String b;
    private final long c = System.nanoTime();

    a(String tag, String what) {
        this.a = tag;
        this.b = what;
    }

    final void a() {
        long durationInMillis = (System.nanoTime() - this.c) / 1000000;
        if ((Looper.getMainLooper().getThread() == Thread.currentThread() ? 1 : null) != null && durationInMillis > 16) {
            FLog.w(this.a, this.b + " took " + durationInMillis + "ms on the main thread, that's a problem! ");
        } else if (durationInMillis > 100) {
            FLog.i(this.a, this.b + " took " + durationInMillis + "ms off the main thread.");
        }
    }
}
