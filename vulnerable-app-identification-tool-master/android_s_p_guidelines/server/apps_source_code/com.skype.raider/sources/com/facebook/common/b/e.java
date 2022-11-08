package com.facebook.common.b;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class e extends AbstractExecutorService implements d {
    private final Handler a;

    protected /* synthetic */ RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return a(runnable, obj);
    }

    protected /* synthetic */ RunnableFuture newTaskFor(Callable callable) {
        return a(callable);
    }

    public /* synthetic */ Future submit(Runnable runnable, @Nullable Object obj) {
        return b(runnable, obj);
    }

    public e(Handler handler) {
        this.a = handler;
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    public void execute(Runnable command) {
        this.a.post(command);
    }

    private <T> f<T> a(Runnable runnable, T value) {
        return new f(this.a, runnable, value);
    }

    private <T> f<T> a(Callable<T> callable) {
        return new f(this.a, callable);
    }

    private <T> ScheduledFuture<T> b(Runnable task, @Nullable T result) {
        if (task == null) {
            throw new NullPointerException();
        }
        f<T> future = a(task, result);
        execute(future);
        return future;
    }

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        f<?> future = a(command, null);
        this.a.postDelayed(future, unit.toMillis(delay));
        return future;
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        f<V> future = a(callable);
        this.a.postDelayed(future, unit.toMillis(delay));
        return future;
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    public final boolean a() {
        return Thread.currentThread() == this.a.getLooper().getThread();
    }

    public /* synthetic */ Future submit(Callable callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        Object a = a(callable);
        execute(a);
        return a;
    }

    public /* synthetic */ Future submit(Runnable runnable) {
        return b(runnable, null);
    }
}
