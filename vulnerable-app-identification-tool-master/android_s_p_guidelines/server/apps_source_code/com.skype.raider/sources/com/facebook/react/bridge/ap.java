package com.facebook.react.bridge;

import android.os.Handler;
import android.os.Looper;
import javax.annotation.Nullable;

public class ap {
    @Nullable
    private static Handler a;

    public static boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void b() {
        an.a(a(), "Expected to run on UI thread!");
    }

    public static void c() {
        an.a(!a(), "Expected not to run on UI thread!");
    }

    public static void a(Runnable runnable) {
        synchronized (ap.class) {
            if (a == null) {
                a = new Handler(Looper.getMainLooper());
            }
        }
        a.post(runnable);
    }
}
