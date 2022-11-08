package com.microsoft.react.push;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;

public class PushHandlingService extends JobService implements d {
    private static final String b = PushHandlingService.class.getSimpleName();
    private static SparseArray<a> c = new SparseArray();

    private class a {
        JobParameters a;
        Handler b;
        Runnable c;
        final /* synthetic */ PushHandlingService d;

        private a(PushHandlingService pushHandlingService) {
            this.d = pushHandlingService;
        }

        /* synthetic */ a(PushHandlingService x0, byte b) {
            this(x0);
        }
    }

    public void onCreate() {
        super.onCreate();
        FLog.i(b, "Service created. Number of pending Jobs: " + c.size());
    }

    public void onDestroy() {
        FLog.i(b, "Service destroyed. Number of pending Jobs: " + c.size());
        super.onDestroy();
    }

    private static int a(PersistableBundle bundle) {
        return bundle.getInt("com.microsoft.react.push.PushConstants.extra.pushId", Integer.MIN_VALUE);
    }

    public boolean onStartJob(JobParameters params) {
        PersistableBundle bundle = params.getExtras();
        String action = bundle.getString("com.microsoft.react.push.PushConstants.ACTION");
        final int pushId;
        if ("com.microsoft.react.push.PushConstants.ACTION_START_PUSH_HANDLING".equals(action)) {
            pushId = a(bundle);
            final long pushLifetime = bundle.getLong("com.microsoft.react.push.PushConstants.extra.pushHandlingLifetime", a);
            FLog.i(b, "Start push handling. pushId: " + pushId + " jobId: " + params.getJobId() + " " + params.toString());
            a jobContext = new a();
            jobContext.a = params;
            jobContext.b = new Handler(Looper.getMainLooper());
            jobContext.c = new Runnable(this) {
                final /* synthetic */ PushHandlingService c;

                public final void run() {
                    FLog.w(PushHandlingService.b, "PushHandling Service has not released PushID " + pushId + " in its processing time window of " + pushLifetime + " milliseconds. Will complete the job now.");
                    this.c.a(pushId);
                }
            };
            c.put(pushId, jobContext);
            jobContext.b.postDelayed(jobContext.c, pushLifetime);
            return true;
        } else if ("com.microsoft.react.push.PushConstants.ACTION_STOP_PUSH_HANDLING".equals(action)) {
            pushId = a(bundle);
            FLog.i(b, "Stop push handling. pushId: " + pushId + " jobId: " + params.getJobId() + " - " + params.toString());
            a(pushId);
            return false;
        } else {
            FLog.e(b, "Unknown action! " + action);
            return false;
        }
    }

    private void a(int jobId) {
        a currentJob = (a) c.get(jobId);
        c.delete(jobId);
        if (currentJob == null) {
            FLog.w(b, "Unknown push JobID: " + jobId);
            return;
        }
        FLog.i(b, "Done with push JobID: " + currentJob.a.getJobId() + " queue size " + c.size() + " - " + currentJob.toString());
        currentJob.b.removeCallbacks(currentJob.c);
        jobFinished(currentJob.a, false);
    }

    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
