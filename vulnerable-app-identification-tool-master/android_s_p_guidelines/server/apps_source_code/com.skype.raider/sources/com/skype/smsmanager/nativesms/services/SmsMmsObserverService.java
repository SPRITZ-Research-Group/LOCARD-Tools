package com.skype.smsmanager.nativesms.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.support.v4.content.a;
import com.skype.smsmanager.nativesms.SmsMmsLogger;

public class SmsMmsObserverService extends JobService {
    private final String a = "SmsMmsObserverService";

    public boolean onStartJob(JobParameters parameters) {
        boolean z;
        if (a.a((Context) this, "android.permission.READ_SMS") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            SmsRelayCoordinator.b(false, (Context) this);
            SmsMmsLogger.a("SmsMmsObserverService", "Got a new MMS job");
            SmsRelayCoordinator.a(false, (Context) this);
        }
        return false;
    }

    public boolean onStopJob(JobParameters parameters) {
        SmsMmsLogger.a("SmsMmsObserverService", "onStopJob called");
        return false;
    }
}
