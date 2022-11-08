package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.measurement.dz;
import com.google.android.gms.internal.measurement.ed;

public final class AppMeasurementService extends Service implements ed {
    private dz<AppMeasurementService> a;

    private final dz<AppMeasurementService> a() {
        if (this.a == null) {
            this.a = new dz(this);
        }
        return this.a;
    }

    public final void a(JobParameters jobParameters) {
        throw new UnsupportedOperationException();
    }

    public final void a(Intent intent) {
        WakefulBroadcastReceiver.a(intent);
    }

    public final boolean a(int i) {
        return stopSelfResult(i);
    }

    @MainThread
    public final IBinder onBind(Intent intent) {
        return a().a(intent);
    }

    @MainThread
    public final void onCreate() {
        super.onCreate();
        a().a();
    }

    @MainThread
    public final void onDestroy() {
        a().b();
        super.onDestroy();
    }

    @MainThread
    public final void onRebind(Intent intent) {
        a().c(intent);
    }

    @MainThread
    public final int onStartCommand(Intent intent, int i, int i2) {
        return a().a(intent, i2);
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        return a().b(intent);
    }
}
