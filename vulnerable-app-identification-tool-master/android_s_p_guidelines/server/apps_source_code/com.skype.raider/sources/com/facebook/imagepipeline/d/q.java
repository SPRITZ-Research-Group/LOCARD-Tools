package com.facebook.imagepipeline.d;

public final class q {
    public final int a;
    public final int b;
    public final int c;
    public final int d = Integer.MAX_VALUE;
    public final int e;

    public q(int maxCacheSize, int maxCacheEntries, int maxEvictionQueueSize, int maxCacheEntrySize) {
        this.a = maxCacheSize;
        this.b = maxCacheEntries;
        this.c = maxEvictionQueueSize;
        this.e = maxCacheEntrySize;
    }
}
