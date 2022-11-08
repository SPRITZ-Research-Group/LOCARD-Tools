package com.google.android.exoplayer2.scheduler;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import defpackage.beg;

@TargetApi(21)
public final class PlatformScheduler {

    public final class PlatformSchedulerService extends JobService {
        public final boolean onStopJob(JobParameters jobParameters) {
            return false;
        }

        public final boolean onStartJob(JobParameters jobParameters) {
            PersistableBundle extras = jobParameters.getExtras();
            if (new a(extras.getInt("requirements")).a(this)) {
                String string = extras.getString("service_action");
                String string2 = extras.getString("service_package");
                Intent intent = new Intent(string).setPackage(string2);
                StringBuilder stringBuilder = new StringBuilder("Starting service action: ");
                stringBuilder.append(string);
                stringBuilder.append(" package: ");
                stringBuilder.append(string2);
                beg.a((Context) this, intent);
            } else {
                jobFinished(jobParameters, true);
            }
            return false;
        }
    }
}
