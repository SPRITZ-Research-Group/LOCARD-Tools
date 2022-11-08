package com.skype.smsmanager.nativesms.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.support.v4.content.a;
import com.skype.smsmanager.nativesms.SmsMmsLogger;

public class SmsObserverService extends JobService {
    private final String a = "SmsObserverService";

    public boolean onStartJob(JobParameters parameters) {
        boolean z;
        if (a.a((Context) this, "android.permission.READ_SMS") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            SmsRelayCoordinator.b(true, (Context) this);
            SmsMmsLogger.a("SmsObserverService", "Got a new SMS job");
            SmsRelayCoordinator.a(true, (Context) this);
        }
        return false;
    }

    public boolean onStopJob(JobParameters parameters) {
        SmsMmsLogger.a("SmsObserverService", "onStopJob called");
        return false;
    }
}
