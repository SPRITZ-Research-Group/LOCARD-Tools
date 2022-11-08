package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.measurement.dz;
import com.google.android.gms.internal.measurement.ed;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements ed {
    private dz<AppMeasurementJobService> a;

    private final dz<AppMeasurementJobService> a() {
        if (this.a == null) {
            this.a = new dz(this);
        }
        return this.a;
    }

    @TargetApi(24)
    public final void a(JobParameters jobParameters) {
        jobFinished(jobParameters, false);
    }

    public final void a(Intent intent) {
    }

    public final boolean a(int i) {
        throw new UnsupportedOperationException();
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

    public final boolean onStartJob(JobParameters jobParameters) {
        return a().a(jobParameters);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        return a().b(intent);
    }
}
