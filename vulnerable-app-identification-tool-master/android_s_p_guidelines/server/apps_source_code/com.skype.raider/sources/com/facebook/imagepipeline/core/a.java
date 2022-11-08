package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class a implements e {
    private final Executor a = Executors.newFixedThreadPool(2, new k("FrescoIoBoundExecutor"));
    private final Executor b;
    private final Executor c;
    private final Executor d;

    public a(int numCpuBoundThreads) {
        this.b = Executors.newFixedThreadPool(numCpuBoundThreads, new k("FrescoDecodeExecutor"));
        this.c = Executors.newFixedThreadPool(numCpuBoundThreads, new k("FrescoBackgroundExecutor"));
        this.d = Executors.newFixedThreadPool(1, new k("FrescoLightWeightBackgroundExecutor"));
    }

    public final Executor a() {
        return this.a;
    }

    public final Executor b() {
        return this.a;
    }

    public final Executor c() {
        return this.b;
    }

    public final Executor d() {
        return this.c;
    }

    public final Executor e() {
        return this.d;
    }
}
