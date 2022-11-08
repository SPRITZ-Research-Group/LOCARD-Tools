package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.e;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.image.c;
import com.facebook.imagepipeline.k.b;
import com.facebook.imagepipeline.k.d;

public final class ah implements aj<com.facebook.common.f.a<c>> {
    private final p<com.facebook.cache.a.c, c> a;
    private final f b;
    private final aj<com.facebook.common.f.a<c>> c;

    public static class a extends m<com.facebook.common.f.a<c>, com.facebook.common.f.a<c>> {
        private final com.facebook.cache.a.c a;
        private final boolean b;
        private final p<com.facebook.cache.a.c, c> c;
        private final boolean d;

        protected final /* synthetic */ void a(Object obj, int i) {
            com.facebook.common.f.a aVar = null;
            obj = (com.facebook.common.f.a) obj;
            if (obj == null) {
                if (b.a(i)) {
                    d().b(null, i);
                }
            } else if (!b.b(i) || this.b) {
                if (this.d) {
                    aVar = this.c.a(this.a, obj);
                }
                try {
                    d().b(1.0f);
                    Consumer d = d();
                    if (aVar != null) {
                        obj = aVar;
                    }
                    d.b(obj, i);
                } finally {
                    com.facebook.common.f.a.c(aVar);
                }
            }
        }

        public a(Consumer<com.facebook.common.f.a<c>> consumer, com.facebook.cache.a.c cacheKey, boolean isRepeatedProcessor, p<com.facebook.cache.a.c, c> memoryCache, boolean isMemoryCachedEnabled) {
            super(consumer);
            this.a = cacheKey;
            this.b = isRepeatedProcessor;
            this.c = memoryCache;
            this.d = isMemoryCachedEnabled;
        }
    }

    public ah(p<com.facebook.cache.a.c, c> memoryCache, f cacheKeyFactory, aj<com.facebook.common.f.a<c>> inputProducer) {
        this.a = memoryCache;
        this.b = cacheKeyFactory;
        this.c = inputProducer;
    }

    public final void a(Consumer<com.facebook.common.f.a<c>> consumer, ak producerContext) {
        am listener = producerContext.c();
        String requestId = producerContext.b();
        b imageRequest = producerContext.a();
        Object callerContext = producerContext.d();
        d postprocessor = imageRequest.q();
        if (postprocessor == null || postprocessor.a() == null) {
            this.c.a(consumer, producerContext);
            return;
        }
        listener.a(requestId, "PostprocessedBitmapMemoryCacheProducer");
        Object cacheKey = this.b.b(imageRequest, callerContext);
        com.facebook.common.f.a<c> cachedReference = this.a.a(cacheKey);
        if (cachedReference != null) {
            listener.a(requestId, "PostprocessedBitmapMemoryCacheProducer", listener.b(requestId) ? e.a("cached_value_found", "true") : null);
            listener.a(requestId, "PostprocessedBitmapMemoryCacheProducer", true);
            consumer.b(1.0f);
            consumer.b(cachedReference, 1);
            cachedReference.close();
            return;
        }
        Consumer<com.facebook.common.f.a<c>> cachedConsumer = new a(consumer, cacheKey, postprocessor instanceof com.facebook.imagepipeline.k.e, this.a, producerContext.a().o());
        listener.a(requestId, "PostprocessedBitmapMemoryCacheProducer", listener.b(requestId) ? e.a("cached_value_found", "false") : null);
        this.c.a(cachedConsumer, producerContext);
    }
}
