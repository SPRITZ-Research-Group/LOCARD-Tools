package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.g;

public class e {
    private static final e a = new e();
    public static final int b = k.b;

    e() {
    }

    @VisibleForTesting
    private static String a(@Nullable Context context, @Nullable String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gcore_");
        stringBuilder.append(b);
        stringBuilder.append("-");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append("-");
        if (context != null) {
            stringBuilder.append(context.getPackageName());
        }
        stringBuilder.append("-");
        if (context != null) {
            try {
                stringBuilder.append(c.a(context).b(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return stringBuilder.toString();
    }

    public static e b() {
        return a;
    }

    public int a(Context context) {
        return b(context, b);
    }

    @Nullable
    public PendingIntent a(Context context, int i, int i2) {
        return a(context, i, i2, null);
    }

    @Nullable
    public PendingIntent a(Context context, int i, int i2, @Nullable String str) {
        Intent a = a(context, i, str);
        return a == null ? null : PendingIntent.getActivity(context, i2, a, 134217728);
    }

    @Nullable
    public Intent a(Context context, int i, @Nullable String str) {
        switch (i) {
            case 1:
            case 2:
                return (context == null || !g.b(context)) ? n.a("com.google.android.gms", a(context, str)) : n.a();
            case 3:
                return n.a("com.google.android.gms");
            default:
                return null;
        }
    }

    public boolean a(int i) {
        return k.b(i);
    }

    public int b(Context context) {
        return k.f(context);
    }

    public int b(Context context, int i) {
        int a = k.a(context, i);
        return k.c(context, a) ? 18 : a;
    }

    public String b(int i) {
        return k.a(i);
    }
}
