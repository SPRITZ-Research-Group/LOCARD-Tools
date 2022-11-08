package com.google.android.gms.internal.measurement;

import android.app.job.JobParameters;

final /* synthetic */ class eb implements Runnable {
    private final dz a;
    private final av b;
    private final JobParameters c;

    eb(dz dzVar, av avVar, JobParameters jobParameters) {
        this.a = dzVar;
        this.b = avVar;
        this.c = jobParameters;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
