package com.applovin.impl.sdk;

import android.content.Context;

public class ac {
    public static Boolean a(Context context) {
        return a(ef.g, context);
    }

    private static Boolean a(ef<Boolean> efVar, Context context) {
        return (Boolean) eg.b((ef) efVar, null, context);
    }

    private static boolean a(ef<Boolean> efVar, Boolean bool, Context context) {
        Boolean a = a((ef) efVar, context);
        eg.a((ef) efVar, (Object) bool, context);
        boolean z = true;
        if (a != null) {
            if (a != bool) {
                return true;
            }
            z = false;
        }
        return z;
    }

    public static boolean a(boolean z, Context context) {
        return a(ef.g, Boolean.valueOf(z), context);
    }

    public static Boolean b(Context context) {
        return a(ef.h, context);
    }

    public static boolean b(boolean z, Context context) {
        return a(ef.h, Boolean.valueOf(z), context);
    }
}
