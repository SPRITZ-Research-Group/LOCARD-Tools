package com.google.android.gms.common.a;

import android.content.Context;
import com.google.android.gms.common.util.m;

public final class a {
    private static Context a;
    private static Boolean b;

    public static synchronized boolean a(Context context) {
        boolean booleanValue;
        synchronized (a.class) {
            Context applicationContext = context.getApplicationContext();
            if (a == null || b == null || a != applicationContext) {
                b = null;
                if (m.d()) {
                    b = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        b = Boolean.valueOf(true);
                    } catch (ClassNotFoundException e) {
                        b = Boolean.valueOf(false);
                    }
                }
                a = applicationContext;
                booleanValue = b.booleanValue();
            } else {
                booleanValue = b.booleanValue();
            }
        }
        return booleanValue;
    }
}
