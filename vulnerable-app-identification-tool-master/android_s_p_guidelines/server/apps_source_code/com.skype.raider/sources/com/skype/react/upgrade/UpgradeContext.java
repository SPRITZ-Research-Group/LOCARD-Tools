package com.skype.react.upgrade;

import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class UpgradeContext implements UpgradeConstants {
    private static AppUpgradeModule b;
    private static final Queue<Intent> c = new ConcurrentLinkedQueue();
    private static final AtomicInteger d = new AtomicInteger(1);

    static synchronized AppUpgradeModule a(ag reactContext) {
        AppUpgradeModule appUpgradeModule;
        synchronized (UpgradeContext.class) {
            appUpgradeModule = new AppUpgradeModule(reactContext, c);
            b = appUpgradeModule;
        }
        return appUpgradeModule;
    }

    static void a(Context context, Intent intent) {
        FLog.i("UpgradeContext", "onReceive action %s", intent.getAction());
        if (intent.getAction() != null) {
            int taskId = intent.getIntExtra("WakeEventReceiver.APP_WAKE_TASK_ID", 0);
            if (taskId == 0 && !"WakeEventReceiver.ACTION_NOTIFICATION_CLICKED".equals(intent.getAction())) {
                taskId = a(context);
                intent.putExtra("WakeEventReceiver.APP_WAKE_TASK_ID", taskId);
            }
            if (b == null) {
                FLog.i("UpgradeContext", "UpgradeModuleNotReady: task %d, intent pushed to delayedIntentQueue", Integer.valueOf(taskId));
                c.add(intent);
                return;
            }
            b.handleIntent(intent);
        }
    }

    static synchronized int a(Context context) {
        int taskId;
        synchronized (UpgradeContext.class) {
            taskId = "UpgradeContext".hashCode();
            FLog.i("UpgradeContext", "acquireWakeLock taskId %d", Integer.valueOf(taskId));
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            PersistableBundle bundle = new PersistableBundle();
            bundle.putString("com.skype.Upgrade.ACTION", "startWakeLock");
            bundle.putInt("WakeEventReceiver.APP_WAKE_TASK_ID", taskId);
            jobScheduler.schedule(new Builder(taskId, new ComponentName(context, SimpleWakefulService.class)).setExtras(bundle).setOverrideDeadline(100).build());
        }
        return taskId;
    }

    static synchronized void a(Context context, int taskId) {
        synchronized (UpgradeContext.class) {
            FLog.i("UpgradeContext", "releaseWakeLock taskId %d", Integer.valueOf(taskId));
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            PersistableBundle bundle = new PersistableBundle();
            bundle.putString("com.skype.Upgrade.ACTION", "stopWakeLock");
            bundle.putInt("WakeEventReceiver.APP_WAKE_TASK_ID", taskId);
            jobScheduler.schedule(new Builder(taskId, new ComponentName(context, SimpleWakefulService.class)).setExtras(bundle).setOverrideDeadline(100).build());
        }
    }
}
