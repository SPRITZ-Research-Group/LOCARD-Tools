package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.d;
import java.util.Collections;
import java.util.List;

public final class b {
    private static final Object a = new Object();
    private static volatile b b;
    @VisibleForTesting
    private static boolean c = false;
    private final List<String> d = Collections.EMPTY_LIST;
    private final List<String> e = Collections.EMPTY_LIST;
    private final List<String> f = Collections.EMPTY_LIST;
    private final List<String> g = Collections.EMPTY_LIST;

    private b() {
    }

    public static b a() {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    @SuppressLint({"UntrackedBindService"})
    public static void a(Context context, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }

    public static boolean a(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        ComponentName component = intent.getComponent();
        return component == null ? false : d.a(context, component.getPackageName()) ? false : context.bindService(intent, serviceConnection, i);
    }

    public static void b() {
    }

    public static boolean b(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        context.getClass().getName();
        return a(context, intent, serviceConnection, i);
    }

    public static void c() {
    }
}
