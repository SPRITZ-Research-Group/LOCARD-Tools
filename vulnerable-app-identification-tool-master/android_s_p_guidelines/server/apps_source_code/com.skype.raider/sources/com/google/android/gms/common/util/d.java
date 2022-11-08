package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.a.c;

public final class d {
    public static boolean a(Context context, String str) {
        "com.google.android.gms".equals(str);
        try {
            return (c.a(context).a(str, 0).flags & 2097152) != 0;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
