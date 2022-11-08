package com.facebook.imagepipeline.memory;

import com.facebook.common.e.d;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class j extends s {
    protected final /* synthetic */ Object b(int i) {
        return g(i);
    }

    protected final /* synthetic */ r f(int i) {
        return g(i);
    }

    public j(d memoryTrimmableRegistry, ad poolParams, ae bufferMemoryChunkPoolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, bufferMemoryChunkPoolStatsTracker);
    }

    private static i g(int bucketedSize) {
        return new i(bucketedSize);
    }
}
