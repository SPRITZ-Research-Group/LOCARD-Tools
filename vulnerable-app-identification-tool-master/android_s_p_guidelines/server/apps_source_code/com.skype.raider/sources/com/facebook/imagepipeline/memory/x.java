package com.facebook.imagepipeline.memory;

import com.facebook.common.e.d;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class x extends s {
    protected final /* synthetic */ Object b(int i) {
        return g(i);
    }

    protected final /* synthetic */ r f(int i) {
        return g(i);
    }

    public x(d memoryTrimmableRegistry, ad poolParams, ae nativeMemoryChunkPoolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, nativeMemoryChunkPoolStatsTracker);
    }

    private static NativeMemoryChunk g(int bucketedSize) {
        return new NativeMemoryChunk(bucketedSize);
    }
}
