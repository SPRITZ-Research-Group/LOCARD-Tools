package com.microsoft.applications.telemetry.core;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.microsoft.applications.telemetry.AppLifecycleState;
import com.microsoft.applications.telemetry.EventProperties;
import com.microsoft.applications.telemetry.ILogger;
import com.microsoft.applications.telemetry.SessionState;
import java.util.concurrent.atomic.AtomicInteger;

public class LifecycleHandler implements ActivityLifecycleCallbacks {
    private static final String LOG_TAG = LifecycleHandler.class.getSimpleName();
    private AtomicInteger numActivitiesCreated = new AtomicInteger(0);
    private AtomicInteger numActivitiesStarted = new AtomicInteger(0);

    public synchronized ILogger getLogger() {
        return InternalMgrImpl.getLogger();
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("onActivityCreated, numActivitiesCreated:%s, numActivitiesStarted:%s", new Object[]{Integer.valueOf(this.numActivitiesCreated.get()), Integer.valueOf(this.numActivitiesStarted.get())}));
        if (InternalMgrImpl.getConfig() != null && InternalMgrImpl.getConfig().isAutoUserSessionEnabled() && this.numActivitiesCreated.incrementAndGet() == 1) {
            getLogger().logAppLifecycle(AppLifecycleState.LAUNCH, new EventProperties(""));
        }
    }

    public void onActivityStarted(Activity activity) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("onActivityStarted, numActivitiesCreated:%s, numActivitiesStarted:%s", new Object[]{Integer.valueOf(this.numActivitiesCreated.get()), Integer.valueOf(this.numActivitiesStarted.get())}));
        if (this.numActivitiesStarted.incrementAndGet() == 1) {
            if (InternalMgrImpl.getConfig().isPauseOnBackgroundEnabled()) {
                InternalMgrImpl.resumeTransmission(false);
            }
            if (InternalMgrImpl.getConfig() != null && InternalMgrImpl.getConfig().isAutoUserSessionEnabled()) {
                getLogger().logAppLifecycle(AppLifecycleState.FOREGROUND, new EventProperties(""));
                getLogger().logSession(SessionState.STARTED, new EventProperties(""));
            }
        }
    }

    public void onActivityResumed(Activity activity) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("onActivityResumed, numActivitiesCreated:%s, numActivitiesStarted:%s", new Object[]{Integer.valueOf(this.numActivitiesCreated.get()), Integer.valueOf(this.numActivitiesStarted.get())}));
        if (InternalMgrImpl.getConfig() != null && InternalMgrImpl.getConfig().isAutoUserSessionEnabled()) {
            getLogger().logAppLifecycle(AppLifecycleState.RESUME, new EventProperties(""));
        }
    }

    public void onActivityPaused(Activity activity) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("onActivityPaused, numActivitiesCreated:%s, numActivitiesStarted:%s", new Object[]{Integer.valueOf(this.numActivitiesCreated.get()), Integer.valueOf(this.numActivitiesStarted.get())}));
        if (InternalMgrImpl.getConfig() != null && InternalMgrImpl.getConfig().isAutoUserSessionEnabled()) {
            getLogger().logAppLifecycle(AppLifecycleState.SUSPEND, new EventProperties(""));
        }
    }

    public void onActivityStopped(Activity activity) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("onActivityStopped, numActivitiesCreated:%s, numActivitiesStarted:%s", new Object[]{Integer.valueOf(this.numActivitiesCreated.get()), Integer.valueOf(this.numActivitiesStarted.get())}));
        if (this.numActivitiesStarted.decrementAndGet() == 0) {
            if (InternalMgrImpl.getConfig().isPauseOnBackgroundEnabled()) {
                InternalMgrImpl.pauseTransmission(false);
            }
            if (InternalMgrImpl.getConfig() != null && InternalMgrImpl.getConfig().isAutoUserSessionEnabled()) {
                getLogger().logSession(SessionState.ENDED, new EventProperties(""));
                getLogger().logAppLifecycle(AppLifecycleState.BACKGROUND, new EventProperties(""));
            }
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("onActivityDestroyed, numActivitiesCreated:%s, numActivitiesStarted:%s", new Object[]{Integer.valueOf(this.numActivitiesCreated.get()), Integer.valueOf(this.numActivitiesStarted.get())}));
        if (InternalMgrImpl.getConfig() != null && InternalMgrImpl.getConfig().isAutoUserSessionEnabled() && this.numActivitiesCreated.decrementAndGet() == 0) {
            getLogger().logAppLifecycle(AppLifecycleState.EXIT, new EventProperties(""));
        }
    }
}
