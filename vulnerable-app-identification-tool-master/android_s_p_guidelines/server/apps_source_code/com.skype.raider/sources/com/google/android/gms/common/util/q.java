package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.l;

public final class q {
    public static boolean a(Context context, int i) {
        boolean z = false;
        if (!a(context, i, "com.google.android.gms")) {
            return z;
        }
        try {
            return l.a(context).a(context.getPackageManager().getPackageInfo("com.google.android.gms", 64));
        } catch (NameNotFoundException e) {
            return z;
        }
    }

    @TargetApi(19)
    public static boolean a(Context context, int i, String str) {
        return c.a(context).a(i, str);
    }
}
