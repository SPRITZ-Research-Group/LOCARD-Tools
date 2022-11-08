package com.appboy.push;

import android.app.Notification;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.d;
import android.widget.RemoteViews;
import com.appboy.a.a;
import com.appboy.i;

public class b implements i {
    private static volatile b a = null;

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public final Notification a(a appConfigurationProvider, Context context, Bundle notificationExtras, Bundle appboyExtras) {
        d dVar;
        e.d(context, notificationExtras);
        d b = new d(context).b();
        e.a(b, notificationExtras);
        e.b(b, notificationExtras);
        e.c(b, notificationExtras);
        e.d(b, notificationExtras);
        e.a(context, b, notificationExtras);
        e.b(context, b, notificationExtras);
        int a = e.a(appConfigurationProvider, b);
        boolean a2 = e.a(context, appConfigurationProvider, b, notificationExtras);
        e.e(b, notificationExtras);
        if (VERSION.SDK_INT < 16) {
            RemoteViews a3 = c.a(context, notificationExtras, a, !a2);
            if (a3 != null) {
                b.L.contentView = a3;
                dVar = b;
                return dVar.e();
            }
        }
        e.f(b, notificationExtras);
        e.g(b, notificationExtras);
        e.a(context, b, notificationExtras, appboyExtras);
        a.a(context, b, notificationExtras);
        e.a(appConfigurationProvider, b, notificationExtras);
        e.h(b, notificationExtras);
        e.i(b, notificationExtras);
        e.b(context, appConfigurationProvider, b, notificationExtras);
        e.c(context, appConfigurationProvider, b, notificationExtras);
        e.j(b, notificationExtras);
        dVar = b;
        return dVar.e();
    }
}
