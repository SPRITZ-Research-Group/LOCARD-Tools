package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.ab;

public final class dz<T extends Context & ed> {
    private final T a;

    public dz(T t) {
        ab.a((Object) t);
        this.a = t;
    }

    private final void a(Runnable runnable) {
        eo a = bx.a(this.a);
        a.p().a(new ec(a, runnable));
    }

    public static boolean a(Context context) {
        ab.a((Object) context);
        return VERSION.SDK_INT >= 24 ? ew.a(context, "com.google.android.gms.measurement.AppMeasurementJobService") : ew.a(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    private final av c() {
        return bx.a(this.a).q();
    }

    @MainThread
    public final int a(Intent intent, int i) {
        av q = bx.a(this.a).q();
        if (intent == null) {
            q.y().a("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            q.C().a("Local AppMeasurementService called. startId, action", Integer.valueOf(i), action);
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                a(new ea(this, i, q, intent));
            }
        }
        return 2;
    }

    @MainThread
    public final IBinder a(Intent intent) {
        if (intent == null) {
            c().v().a("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new bz(bx.a(this.a));
        }
        c().y().a("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final void a() {
        bx.a(this.a).q().C().a("Local AppMeasurementService is starting up");
    }

    final /* synthetic */ void a(int i, av avVar, Intent intent) {
        if (((ed) this.a).a(i)) {
            avVar.C().a("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            c().C().a("Completed wakeful intent.");
            ((ed) this.a).a(intent);
        }
    }

    final /* synthetic */ void a(av avVar, JobParameters jobParameters) {
        avVar.C().a("AppMeasurementJobService processed last upload request.");
        ((ed) this.a).a(jobParameters);
    }

    @TargetApi(24)
    @MainThread
    public final boolean a(JobParameters jobParameters) {
        av q = bx.a(this.a).q();
        String string = jobParameters.getExtras().getString("action");
        q.C().a("Local AppMeasurementJobService called. action", string);
        if ("com.google.android.gms.measurement.UPLOAD".equals(string)) {
            a(new eb(this, q, jobParameters));
        }
        return true;
    }

    @MainThread
    public final void b() {
        bx.a(this.a).q().C().a("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final boolean b(Intent intent) {
        if (intent == null) {
            c().v().a("onUnbind called with null intent");
        } else {
            c().C().a("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }

    @MainThread
    public final void c(Intent intent) {
        if (intent == null) {
            c().v().a("onRebind called with null intent");
            return;
        }
        c().C().a("onRebind called. action", intent.getAction());
    }
}
