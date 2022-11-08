package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.e;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public final class u {
    @GuardedBy("this")
    @VisibleForTesting
    e a = null;
    @GuardedBy("this")
    @VisibleForTesting
    int b = 0;
    @GuardedBy("this")
    @VisibleForTesting
    c c = c.IDLE;
    @GuardedBy("this")
    @VisibleForTesting
    long d = 0;
    @GuardedBy("this")
    @VisibleForTesting
    long e = 0;
    private final Executor f;
    private final a g;
    private final Runnable h = new Runnable(this) {
        final /* synthetic */ u a;

        {
            this.a = this$0;
        }

        public final void run() {
            u.a(this.a);
        }
    };
    private final Runnable i = new Runnable(this) {
        final /* synthetic */ u a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.f.execute(this.a.h);
        }
    };
    private final int j;

    public interface a {
        void a(e eVar, int i);
    }

    @VisibleForTesting
    static class b {
        private static ScheduledExecutorService a;

        static ScheduledExecutorService a() {
            if (a == null) {
                a = Executors.newSingleThreadScheduledExecutor();
            }
            return a;
        }
    }

    @VisibleForTesting
    enum c {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    public u(Executor executor, a jobRunnable, int minimumJobIntervalMs) {
        this.f = executor;
        this.g = jobRunnable;
        this.j = minimumJobIntervalMs;
    }

    public final void a() {
        e oldEncodedImage;
        synchronized (this) {
            oldEncodedImage = this.a;
            this.a = null;
            this.b = 0;
        }
        e.d(oldEncodedImage);
    }

    public final boolean a(e encodedImage, int status) {
        if (!b(encodedImage, status)) {
            return false;
        }
        e oldEncodedImage;
        synchronized (this) {
            oldEncodedImage = this.a;
            this.a = e.a(encodedImage);
            this.b = status;
        }
        e.d(oldEncodedImage);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean b() {
        long now = SystemClock.uptimeMillis();
        long when = 0;
        boolean shouldEnqueue = false;
        synchronized (this) {
            if (b(this.a, this.b)) {
                switch (this.c) {
                    case IDLE:
                        when = Math.max(this.e + ((long) this.j), now);
                        shouldEnqueue = true;
                        this.d = now;
                        this.c = c.QUEUED;
                        break;
                    case RUNNING:
                        this.c = c.RUNNING_AND_PENDING;
                        break;
                }
            }
            return false;
        }
    }

    private void a(long delay) {
        if (delay > 0) {
            b.a().schedule(this.i, delay, TimeUnit.MILLISECONDS);
        } else {
            this.i.run();
        }
    }

    private void d() {
        long now = SystemClock.uptimeMillis();
        long when = 0;
        boolean shouldEnqueue = false;
        synchronized (this) {
            if (this.c == c.RUNNING_AND_PENDING) {
                when = Math.max(this.e + ((long) this.j), now);
                shouldEnqueue = true;
                this.d = now;
                this.c = c.QUEUED;
            } else {
                this.c = c.IDLE;
            }
        }
        if (shouldEnqueue) {
            a(when - now);
        }
    }

    private static boolean b(e encodedImage, int status) {
        return b.a(status) || b.a(status, 4) || e.e(encodedImage);
    }

    public final synchronized long c() {
        return this.e - this.d;
    }

    static /* synthetic */ void a(u x0) {
        e eVar;
        int i;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (x0) {
            eVar = x0.a;
            i = x0.b;
            x0.a = null;
            x0.b = 0;
            x0.c = c.RUNNING;
            x0.e = uptimeMillis;
        }
        try {
            if (b(eVar, i)) {
                x0.g.a(eVar, i);
            }
            e.d(eVar);
            x0.d();
        } catch (Throwable th) {
            e.d(eVar);
            x0.d();
        }
    }
}
