package com.facebook.ads.internal.q.a;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class b {
    private static boolean a = false;
    private static boolean b = false;

    @Nullable
    public static synchronized String a(String str) {
        String property;
        synchronized (b.class) {
            property = !a() ? null : System.getProperty("fb.e2e." + str);
        }
        return property;
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (b.class) {
            if (!b) {
                a = "true".equals(System.getProperty("fb.running_e2e"));
                b = true;
            }
            z = a;
        }
        return z;
    }

    public static synchronized boolean b(String str) {
        boolean z;
        synchronized (b.class) {
            z = !TextUtils.isEmpty(a(str));
        }
        return z;
    }
}
