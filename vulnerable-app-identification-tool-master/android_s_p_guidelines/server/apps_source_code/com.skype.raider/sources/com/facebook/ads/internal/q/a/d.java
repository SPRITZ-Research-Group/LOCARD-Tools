package com.facebook.ads.internal.q.a;

import android.content.Context;
import com.facebook.ads.internal.t.a;

public final class d {
    public static void a() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, String str) {
        if (a.a(context)) {
            new StringBuilder().append(str).append(" (displayed for test ads only)");
        }
    }
}
