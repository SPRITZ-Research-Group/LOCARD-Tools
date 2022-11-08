package com.google.android.gms.common.a;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

public final class c {
    private static c b = new c();
    private b a = null;

    public static b a(Context context) {
        return b.b(context);
    }

    @VisibleForTesting
    private synchronized b b(Context context) {
        if (this.a == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.a = new b(context);
        }
        return this.a;
    }
}
