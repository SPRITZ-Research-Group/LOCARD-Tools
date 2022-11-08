package com.facebook.ads.internal.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.m.c;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class l {
    private final c a;
    @Nullable
    private Application b;
    @Nullable
    private a c;
    private long d = 0;
    @Nullable
    private String e = null;
    @Nullable
    private a f = null;

    @TargetApi(14)
    private static class a implements ActivityLifecycleCallbacks {
        private final WeakReference<Activity> a;
        @Nullable
        private l b;

        public a(Activity activity, l lVar) {
            this.a = new WeakReference(activity);
            this.b = lVar;
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivityPaused(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
            if (this.b != null) {
                Activity activity2 = (Activity) this.a.get();
                if (activity2 == null || (activity2 != null && activity.equals(activity2))) {
                    this.b.a();
                    this.b = null;
                }
            }
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityStopped(Activity activity) {
        }
    }

    private l(c cVar, Activity activity) {
        this.a = cVar;
        this.b = activity.getApplication();
        this.c = new a(activity, this);
    }

    public static l a(c cVar, Activity activity) {
        return (activity == null || VERSION.SDK_INT < 14) ? null : new l(cVar, activity);
    }

    private void a(String str, long j, long j2, @Nullable a aVar) {
        Map hashMap = new HashMap();
        hashMap.put("leave_time", Long.toString(j));
        hashMap.put("back_time", Long.toString(j2));
        if (aVar != null) {
            hashMap.put("outcome", aVar.name());
        }
        this.a.i(str, hashMap);
    }

    @TargetApi(14)
    public final void a() {
        a(this.e, this.d, System.currentTimeMillis(), this.f);
        if (this.b != null && this.c != null) {
            this.b.unregisterActivityLifecycleCallbacks(this.c);
            this.c = null;
            this.b = null;
        }
    }

    public final void a(a aVar) {
        this.f = aVar;
    }

    @TargetApi(14)
    public final void a(String str) {
        this.e = str;
        if (this.c == null || this.b == null) {
            a(str, -1, -1, a.CANNOT_TRACK);
            return;
        }
        this.d = System.currentTimeMillis();
        this.b.registerActivityLifecycleCallbacks(this.c);
    }
}
