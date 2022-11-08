package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.common.a.c;
import javax.annotation.concurrent.GuardedBy;

public final class y {
    private static Object a = new Object();
    @GuardedBy("sLock")
    private static boolean b;
    private static String c;
    private static int d;

    public static String a(Context context) {
        c(context);
        return c;
    }

    public static int b(Context context) {
        c(context);
        return d;
    }

    private static void c(Context context) {
        synchronized (a) {
            if (b) {
                return;
            }
            b = true;
            try {
                Bundle bundle = c.a(context).a(context.getPackageName(), 128).metaData;
                if (bundle == null) {
                    return;
                }
                c = bundle.getString("com.google.app.id");
                d = bundle.getInt("com.google.android.gms.version");
            } catch (NameNotFoundException e) {
            }
        }
    }
}
