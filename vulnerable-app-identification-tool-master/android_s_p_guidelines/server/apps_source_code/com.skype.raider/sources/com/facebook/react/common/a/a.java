package com.facebook.react.common.a;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

public final class a<T> implements Future<T> {
    private final CountDownLatch a = new CountDownLatch(1);
    @Nullable
    private T b;
    @Nullable
    private Exception c;

    public final void a(@Nullable T result) {
        b();
        this.b = result;
        this.a.countDown();
    }

    public final void a(Exception exception) {
        b();
        this.c = exception;
        this.a.countDown();
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return this.a.getCount() == 0;
    }

    @Nullable
    public final T get() throws InterruptedException, ExecutionException {
        this.a.await();
        if (this.c == null) {
            return this.b;
        }
        throw new ExecutionException(this.c);
    }

    @Nullable
    public final T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!this.a.await(timeout, unit)) {
            throw new TimeoutException("Timed out waiting for result");
        } else if (this.c == null) {
            return this.b;
        } else {
            throw new ExecutionException(this.c);
        }
    }

    @Nullable
    public final T a() {
        Exception e;
        try {
            return get();
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        throw new RuntimeException(e);
    }

    private void b() {
        if (this.a.getCount() == 0) {
            throw new RuntimeException("Result has already been set!");
        }
    }
}
