package com.applovin.impl.sdk;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class dz implements ActivityLifecycleCallbacks {
    final /* synthetic */ dy a;

    dz(dy dyVar) {
        this.a = dyVar;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.a.j();
    }

    public void onActivityDestroyed(Activity activity) {
        this.a.j();
    }

    public void onActivityPaused(Activity activity) {
        this.a.e();
    }

    public void onActivityResumed(Activity activity) {
        this.a.i();
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.a.f();
    }

    public void onActivityStarted(Activity activity) {
        this.a.h();
    }

    public void onActivityStopped(Activity activity) {
        this.a.g();
    }
}
