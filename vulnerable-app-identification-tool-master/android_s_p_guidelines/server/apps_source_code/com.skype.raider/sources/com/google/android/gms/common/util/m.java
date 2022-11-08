package com.google.android.gms.common.util;

import android.os.Build.VERSION;

@VisibleForTesting
public final class m {
    public static boolean a() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean b() {
        return VERSION.SDK_INT >= 20;
    }

    public static boolean c() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean d() {
        return VERSION.SDK_INT >= 26;
    }
}
