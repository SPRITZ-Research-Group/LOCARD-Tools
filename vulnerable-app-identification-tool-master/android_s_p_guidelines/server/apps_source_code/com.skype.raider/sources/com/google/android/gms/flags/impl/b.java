package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.flags.impl.a.a;

public final class b {
    private static SharedPreferences a = null;

    public static SharedPreferences a(Context context) throws Exception {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (a == null) {
                a = (SharedPreferences) a.a(new g(context));
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }
}
