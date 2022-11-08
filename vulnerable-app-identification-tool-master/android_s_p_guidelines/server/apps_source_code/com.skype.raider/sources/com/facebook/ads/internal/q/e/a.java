package com.facebook.ads.internal.q.e;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.PowerManager;
import com.facebook.ads.internal.q.d.b;

public class a {
    private static final String a = a.class.getSimpleName();

    public static boolean a(Context context) {
        return b(context) && b.b(context);
    }

    public static boolean b(Context context) {
        if (context == null) {
            return true;
        }
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        } catch (Exception e) {
            com.facebook.ads.internal.q.d.a.a(context, "risky", b.q, e);
            return true;
        }
    }
}
