package com.facebook.imagepipeline.producers;

import com.facebook.cache.a.c;
import com.facebook.common.f.a;
import com.facebook.imagepipeline.d.p;

public final class f extends h {
    public f(p<c, com.facebook.imagepipeline.image.c> memoryCache, com.facebook.imagepipeline.d.f cacheKeyFactory, aj<a<com.facebook.imagepipeline.image.c>> inputProducer) {
        super(memoryCache, cacheKeyFactory, inputProducer);
    }

    protected final Consumer<a<com.facebook.imagepipeline.image.c>> a(Consumer<a<com.facebook.imagepipeline.image.c>> consumer, c cacheKey, boolean isMemoryCacheEnabled) {
        return consumer;
    }

    protected final String a() {
        return "BitmapMemoryCacheGetProducer";
    }
}
