package com.facebook.common.b;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public final class a extends AbstractExecutorService {
    private static final a a = new a();

    public static a a() {
        return a;
    }

    private a() {
    }

    public final void execute(Runnable command) {
        command.run();
    }

    public final boolean isShutdown() {
        return false;
    }

    public final void shutdown() {
    }

    public final List<Runnable> shutdownNow() {
        shutdown();
        return Collections.emptyList();
    }

    public final boolean isTerminated() {
        return false;
    }

    public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return true;
    }
}
