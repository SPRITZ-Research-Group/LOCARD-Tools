package com.facebook.common.b;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

public final class f<V> implements RunnableFuture<V>, ScheduledFuture<V> {
    private final Handler a;
    private final FutureTask<V> b;

    public f(Handler handler, Callable<V> callable) {
        this.a = handler;
        this.b = new FutureTask(callable);
    }

    public f(Handler handler, Runnable runnable, @Nullable V result) {
        this.a = handler;
        this.b = new FutureTask(runnable, result);
    }

    public final long getDelay(TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    public final void run() {
        this.b.run();
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        return this.b.cancel(mayInterruptIfRunning);
    }

    public final boolean isCancelled() {
        return this.b.isCancelled();
    }

    public final boolean isDone() {
        return this.b.isDone();
    }

    public final V get() throws InterruptedException, ExecutionException {
        return this.b.get();
    }

    public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.b.get(timeout, unit);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        throw new UnsupportedOperationException();
    }
}
