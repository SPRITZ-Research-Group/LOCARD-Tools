package net.hockeyapp.android.f;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class c<T> implements Future<T> {
    private final T a;

    public c(T result) {
        this.a = result;
    }

    public final boolean cancel(boolean b) {
        return false;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public final T get() throws InterruptedException, ExecutionException {
        return this.a;
    }

    public final T get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.a;
    }
}
