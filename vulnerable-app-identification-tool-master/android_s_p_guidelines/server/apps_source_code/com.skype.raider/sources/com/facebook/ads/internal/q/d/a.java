package com.facebook.ads.internal.q.d;

import android.content.Context;
import com.facebook.ads.internal.f.e;
import com.facebook.ads.internal.i.c;
import java.util.Map;

public final class a {
    public static void a(Context context, String str, int i, Exception exception) {
        if (Math.random() >= 1.0d - (com.facebook.ads.internal.l.a.i(context).contains(new StringBuilder().append(str).append(":").append(i).toString()) ? ((double) (com.facebook.ads.internal.l.a.k(context) * com.facebook.ads.internal.l.a.j(context))) / 10000.0d : ((double) com.facebook.ads.internal.l.a.k(context)) / 100.0d)) {
            Map b = new c(context, false).b();
            b.put("subtype", str);
            b.put("subtype_code", String.valueOf(i));
            e.a(exception, context, b);
        }
    }
}
