package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.ah;
import com.google.android.gms.common.internal.y;
import com.google.android.gms.common.m.a;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
@Deprecated
public final class e {
    private static final Object a = new Object();
    @GuardedBy("sLock")
    private static e b;
    private final String c;
    private final Status d;
    private final boolean e;
    private final boolean f;

    @KeepForSdk
    @VisibleForTesting
    private e(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(a.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            if (z2) {
                z = false;
            }
            this.f = z;
            z = z2;
        } else {
            this.f = false;
        }
        this.e = z;
        Object a = y.a(context);
        if (a == null) {
            a = new ah(context).a("google_app_id");
        }
        if (TextUtils.isEmpty(a)) {
            this.d = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.c = null;
            return;
        }
        this.c = a;
        this.d = Status.a;
    }

    @KeepForSdk
    public static Status a(Context context) {
        Status status;
        ab.a((Object) context, (Object) "Context must not be null.");
        synchronized (a) {
            if (b == null) {
                b = new e(context);
            }
            status = b.d;
        }
        return status;
    }

    @KeepForSdk
    private static e a(String str) {
        e eVar;
        synchronized (a) {
            if (b == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            eVar = b;
        }
        return eVar;
    }

    @KeepForSdk
    public static String a() {
        return a("getGoogleAppId").c;
    }

    @KeepForSdk
    public static boolean b() {
        return a("isMeasurementExplicitlyDisabled").f;
    }
}
