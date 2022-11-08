package com.appboy.f;

import android.content.Context;

public class h {
    private static final String a = c.a(h.class);

    public static boolean a(Context context, String permission) {
        try {
            return context.checkCallingOrSelfPermission(permission) == 0;
        } catch (Throwable th) {
            c.d(a, "Failure checking permission " + permission, th);
            return false;
        }
    }
}
