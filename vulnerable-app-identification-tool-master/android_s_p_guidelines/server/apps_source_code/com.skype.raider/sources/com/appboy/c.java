package com.appboy;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.appboy.push.AppboyNotificationRoutingActivity;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;

public final class c implements ActivityLifecycleCallbacks {
    private final boolean a;
    private final boolean b;

    public c() {
        this((byte) 0);
    }

    private c(byte b) {
        this.a = true;
        this.b = true;
    }

    public final void onActivityStarted(Activity activity) {
        if (this.b && !a(activity)) {
            a.a(activity.getApplicationContext()).a(activity);
        }
    }

    public final void onActivityStopped(Activity activity) {
        if (this.b && !a(activity)) {
            a.a(activity.getApplicationContext()).b(activity);
        }
    }

    public final void onActivityResumed(Activity activity) {
        if (this.a && !a(activity)) {
            AppboyInAppMessageManager.getInstance().registerInAppMessageManager(activity);
        }
    }

    public final void onActivityPaused(Activity activity) {
        if (this.a && !a(activity)) {
            AppboyInAppMessageManager.getInstance().unregisterInAppMessageManager(activity);
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.a && !a(activity)) {
            AppboyInAppMessageManager.getInstance().ensureSubscribedToInAppMessageEvents(activity.getApplicationContext());
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    private static boolean a(Activity activity) {
        return activity.getClass().equals(AppboyNotificationRoutingActivity.class);
    }
}
