package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import com.adjust.sdk.Constants;

@TargetApi(14)
@MainThread
final class dd implements ActivityLifecycleCallbacks {
    private final /* synthetic */ cw a;

    private dd(cw cwVar) {
        this.a = cwVar;
    }

    /* synthetic */ dd(cw cwVar, byte b) {
        this(cwVar);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.a.q().C().a("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null && data.isHierarchical()) {
                    if (bundle == null) {
                        Bundle a = this.a.n().a(data);
                        this.a.n();
                        String str = ew.a(intent) ? "gs" : "auto";
                        if (a != null) {
                            this.a.a(str, "_cmp", a);
                        }
                    }
                    Object queryParameter = data.getQueryParameter(Constants.REFERRER);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        Object obj = (queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content"))) ? 1 : null;
                        if (obj == null) {
                            this.a.q().B().a("Activity created with data 'referrer' param without gclid and at least one utm field");
                            return;
                        }
                        this.a.q().B().a("Activity created with referrer", queryParameter);
                        if (!TextUtils.isEmpty(queryParameter)) {
                            this.a.a("auto", "_ldl", queryParameter);
                        }
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            this.a.q().v().a("Throwable caught in onActivityCreated", e);
        }
        this.a.i().a(activity, bundle);
    }

    public final void onActivityDestroyed(Activity activity) {
        this.a.i().c(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.a.i().b(activity);
        cs o = this.a.o();
        o.p().a(new ei(o, o.j().b()));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        this.a.i().a(activity);
        cs o = this.a.o();
        o.p().a(new eh(o, o.j().b()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.a.i().b(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
