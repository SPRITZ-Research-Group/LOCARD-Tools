package com.skype.react.upgrade;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;

public class SimpleWakefulService extends JobService implements UpgradeConstants {
    private static SparseArray<a> b = new SparseArray();

    private class a {
        JobParameters a;
        Handler b;
        Runnable c;
        final /* synthetic */ SimpleWakefulService d;

        private a(SimpleWakefulService simpleWakefulService) {
            this.d = simpleWakefulService;
        }

        /* synthetic */ a(SimpleWakefulService x0, byte b) {
            this(x0);
        }
    }

    public void onCreate() {
        super.onCreate();
        FLog.i("SimpleWakefulService", "Service created. Number of pending Jobs: " + b.size());
    }

    public void onDestroy() {
        FLog.i("SimpleWakefulService", "Service destroyed. Number of pending Jobs: " + b.size());
        super.onDestroy();
    }

    public boolean onStartJob(JobParameters params) {
        PersistableBundle bundle = params.getExtras();
        String action = bundle.getString("com.skype.Upgrade.ACTION");
        final int taskId = bundle.getInt("WakeEventReceiver.APP_WAKE_TASK_ID", 0);
        if ("startWakeLock".equals(action)) {
            FLog.i("SimpleWakefulService", "Acquire WAKE_LOCK for taskId: " + taskId);
            a jobContext = new a();
            jobContext.a = params;
            jobContext.b = new Handler(Looper.getMainLooper());
            jobContext.c = new Runnable(this) {
                final /* synthetic */ SimpleWakefulService b;

                public final void run() {
                    FLog.w("SimpleWakefulService", "SimpleWakefulService has not released TaskID " + taskId + " in its processing time window of " + UpgradeConstants.b_ + " milliseconds. Will complete the job now.");
                    this.b.a(taskId);
                }
            };
            b.put(taskId, jobContext);
            jobContext.b.postDelayed(jobContext.c, b_);
            return true;
        } else if ("stopWakeLock".equals(action)) {
            FLog.i("SimpleWakefulService", "Release WAKE_LOCK for taskId: " + taskId);
            a(taskId);
            return false;
        } else {
            FLog.e("SimpleWakefulService", "Unknown action! " + action);
            return false;
        }
    }

    private void a(int taskId) {
        a currentJob = (a) b.get(taskId);
        b.delete(taskId);
        if (currentJob == null) {
            FLog.w("SimpleWakefulService", "Unknown push JobID: " + taskId);
            return;
        }
        FLog.i("SimpleWakefulService", "Done with push JobID: " + currentJob.a.getJobId() + " queue size " + b.size() + " - " + currentJob.toString());
        currentJob.b.removeCallbacks(currentJob.c);
        jobFinished(currentJob.a, false);
    }

    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
