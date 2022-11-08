package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class b implements a {
    private static volatile a c;
    @VisibleForTesting
    final Map<String, Object> a = new ConcurrentHashMap();
    private final AppMeasurement b;

    private b(AppMeasurement appMeasurement) {
        ab.a((Object) appMeasurement);
        this.b = appMeasurement;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static a a(Context context) {
        ab.a((Object) context);
        ab.a(context.getApplicationContext());
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new b(AppMeasurement.getInstance(context));
                }
            }
        }
        return c;
    }

    @WorkerThread
    @KeepForSdk
    public final void a(@NonNull String str, @NonNull String str2, Bundle bundle) {
        if (!com.google.firebase.analytics.connector.internal.b.a(str)) {
            String str3 = "Origin not allowed : ";
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                str3.concat(valueOf);
            } else {
                valueOf = new String(str3);
            }
        } else if (com.google.firebase.analytics.connector.internal.b.a(str2, bundle) && com.google.firebase.analytics.connector.internal.b.a(str, str2, bundle)) {
            this.b.logEventInternal(str, str2, bundle);
        }
    }

    @KeepForSdk
    public final void a(@NonNull String str, @NonNull String str2, Object obj) {
        String str3;
        String valueOf;
        if (!com.google.firebase.analytics.connector.internal.b.a(str)) {
            str3 = "Origin not allowed : ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                str3.concat(valueOf);
            } else {
                valueOf = new String(str3);
            }
        } else if (!com.google.firebase.analytics.connector.internal.b.b(str2)) {
            str3 = "User Property not allowed : ";
            valueOf = String.valueOf(str2);
            if (valueOf.length() != 0) {
                str3.concat(valueOf);
            } else {
                valueOf = new String(str3);
            }
        } else if ((!str2.equals("_ce1") && !str2.equals("_ce2")) || str.equals("fcm") || str.equals("frc")) {
            this.b.a(str, str2, obj);
        } else {
            str3 = "User Property not allowed for this origin: ";
            valueOf = String.valueOf(str2);
            if (valueOf.length() != 0) {
                str3.concat(valueOf);
            } else {
                valueOf = new String(str3);
            }
        }
    }
}
