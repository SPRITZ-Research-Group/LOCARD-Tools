package com.facebook.common.b;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public final class c extends b implements g {
    public c(Executor executor) {
        super("SerialExecutor", executor, new LinkedBlockingQueue());
    }

    public final synchronized void execute(Runnable runnable) {
        super.execute(runnable);
    }
}
