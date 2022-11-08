package com.microsoft.urlrequest;

import android.app.job.JobParameters;
import android.support.annotation.Nullable;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;

final class c {
    private static Timer a = new Timer();
    private final String b;
    private final Call c;
    @Nullable
    private final Integer d;
    @Nullable
    private final Integer e;
    private final a f;
    @Nullable
    private TimerTask g;
    @Nullable
    private TimerTask h;
    private volatile boolean i = false;
    private JobParameters j;

    interface a {
        void a(String str);
    }

    c(String id, Call call, @Nullable Integer completeTimeout, @Nullable Integer progressTimeout, a callback, JobParameters jobParameters) {
        this.b = id;
        this.c = call;
        this.d = completeTimeout;
        this.e = progressTimeout;
        this.f = callback;
        this.j = jobParameters;
        if (completeTimeout != null) {
            this.g = a(completeTimeout.longValue());
        }
        if (progressTimeout != null) {
            this.h = a(progressTimeout.longValue());
        }
    }

    private TimerTask a(long timeout) {
        TimerTask task = new TimerTask(this) {
            final /* synthetic */ c a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.i = true;
                this.a.f.a(this.a.b);
            }
        };
        a.schedule(task, timeout);
        return task;
    }

    final void a() {
        if (this.h != null) {
            this.h.cancel();
            this.h = a(this.e.longValue());
        }
    }

    final boolean b() {
        return this.i;
    }

    public final void a(boolean cancel) {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        if (this.h != null) {
            this.h.cancel();
            this.h = null;
        }
        if (cancel) {
            this.c.cancel();
        }
    }

    public final JobParameters c() {
        return this.j;
    }
}
