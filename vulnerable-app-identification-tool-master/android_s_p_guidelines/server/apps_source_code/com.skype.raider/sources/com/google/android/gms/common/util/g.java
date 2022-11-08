package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;

public final class g {
    private static Boolean a;
    private static Boolean b;
    private static Boolean c;

    @TargetApi(20)
    public static boolean a(Context context) {
        if (a == null) {
            boolean z = m.b() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
            a = Boolean.valueOf(z);
        }
        return a.booleanValue();
    }

    public static boolean c(Context context) {
        if (c == null) {
            boolean z = context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded");
            c = Boolean.valueOf(z);
        }
        return c.booleanValue();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @TargetApi(24)
    public static boolean b(Context context) {
        if (VERSION.SDK_INT >= 24) {
            if (b == null) {
                boolean z = m.c() && context.getPackageManager().hasSystemFeature("cn.google");
                b = Boolean.valueOf(z);
            }
        }
        if (a(context)) {
            return true;
        }
        return false;
    }
}
