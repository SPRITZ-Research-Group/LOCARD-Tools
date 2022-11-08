package com.microsoft.skypemessagetextinput.d;

import com.facebook.common.b.i;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class a {
    private final ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();

    public final void a() {
        this.a.shutdownNow();
    }

    public final ScheduledFuture<?> a(final Runnable task, long timeoutInMilliseconds) {
        return this.a.schedule(new Runnable(this) {
            final /* synthetic */ a b;

            public final void run() {
                i.b().execute(task);
            }
        }, timeoutInMilliseconds, TimeUnit.MILLISECONDS);
    }
}
