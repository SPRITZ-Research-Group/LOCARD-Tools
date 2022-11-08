package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.m;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class b implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final b a = new b();
    private final AtomicBoolean b = new AtomicBoolean();
    private final AtomicBoolean c = new AtomicBoolean();
    @GuardedBy("sInstance")
    private final ArrayList<a> d = new ArrayList();
    @GuardedBy("sInstance")
    private boolean e = false;

    @KeepForSdk
    public interface a {
        @KeepForSdk
        void a(boolean z);
    }

    @KeepForSdk
    private b() {
    }

    @KeepForSdk
    public static b a() {
        return a;
    }

    @KeepForSdk
    public static void a(Application application) {
        synchronized (a) {
            if (!a.e) {
                application.registerActivityLifecycleCallbacks(a);
                application.registerComponentCallbacks(a);
                a.e = true;
            }
        }
    }

    private final void a(boolean z) {
        synchronized (a) {
            ArrayList arrayList = this.d;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((a) obj).a(z);
            }
        }
    }

    @KeepForSdk
    public final void a(a aVar) {
        synchronized (a) {
            this.d.add(aVar);
        }
    }

    @TargetApi(16)
    @KeepForSdk
    public final boolean b() {
        if (!this.c.get()) {
            if (!m.a()) {
                return true;
            }
            RunningAppProcessInfo runningAppProcessInfo = new RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.c.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.b.set(true);
            }
        }
        return this.b.get();
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.b.compareAndSet(true, false);
        this.c.set(true);
        if (compareAndSet) {
            a(false);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.b.compareAndSet(true, false);
        this.c.set(true);
        if (compareAndSet) {
            a(false);
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.b.compareAndSet(false, true)) {
            this.c.set(true);
            a(true);
        }
    }
}
