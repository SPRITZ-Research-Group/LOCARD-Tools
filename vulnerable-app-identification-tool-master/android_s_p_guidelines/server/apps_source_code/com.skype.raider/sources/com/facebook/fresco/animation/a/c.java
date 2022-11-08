package com.facebook.fresco.animation.a;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.facebook.common.time.b;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public final class c<T extends a> extends b<T> {
    private final b a;
    private final ScheduledExecutorService b;
    private boolean c = false;
    private long d;
    private long e = 2000;
    private long f = 1000;
    @Nullable
    private a g;
    private final Runnable h = new Runnable(this) {
        final /* synthetic */ c a;

        {
            this.a = this$0;
        }

        public final void run() {
            synchronized (this.a) {
                this.a.c = false;
                if (!c.b(this.a)) {
                    this.a.f();
                } else if (this.a.g != null) {
                    this.a.g.f();
                }
            }
        }
    };

    public interface a {
        void f();
    }

    public static <T extends a & a> b<T> a(T backend, b monotonicClock, ScheduledExecutorService scheduledExecutorServiceForUiThread) {
        return new c(backend, (a) backend, monotonicClock, scheduledExecutorServiceForUiThread);
    }

    private c(@Nullable T animationBackend, @Nullable a inactivityListener, b monotonicClock, ScheduledExecutorService scheduledExecutorServiceForUiThread) {
        super(animationBackend);
        this.g = inactivityListener;
        this.a = monotonicClock;
        this.b = scheduledExecutorServiceForUiThread;
    }

    public final boolean a(Drawable parent, Canvas canvas, int frameNumber) {
        this.d = this.a.now();
        boolean result = super.a(parent, canvas, frameNumber);
        f();
        return result;
    }

    private synchronized void f() {
        if (!this.c) {
            this.c = true;
            this.b.schedule(this.h, this.f, TimeUnit.MILLISECONDS);
        }
    }

    static /* synthetic */ boolean b(c x0) {
        return x0.a.now() - x0.d > x0.e;
    }
}
