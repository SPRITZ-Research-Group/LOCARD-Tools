package com.appboy.f;

import android.content.Context;

public class g {
    private static final String a = c.a(g.class);
    private static String b;

    public static String a(Context context) {
        if (b != null) {
            return b;
        }
        String packageName = context.getPackageName();
        b = packageName;
        return packageName;
    }
}
