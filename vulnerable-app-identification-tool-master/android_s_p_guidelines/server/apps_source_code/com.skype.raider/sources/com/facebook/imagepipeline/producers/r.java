package com.facebook.imagepipeline.producers;

import com.facebook.cache.a.c;
import com.facebook.common.e.h;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.l.b;
import java.util.Map;

public final class r implements aj<e> {
    private final p<c, h> a;
    private final f b;
    private final aj<e> c;

    private static class a extends m<e, e> {
        private final p<c, h> a;
        private final c b;
        private final boolean c;

        public final /* synthetic */ void a(Object obj, int i) {
            e eVar = (e) obj;
            com.facebook.common.f.a b;
            com.facebook.common.f.a a;
            e eVar2;
            try {
                b.a();
                if (b.b(i) || eVar == null || b.c(i) || eVar.e() == com.facebook.imageformat.c.a) {
                    d().b(eVar, i);
                    b.a();
                    return;
                }
                b = eVar.b();
                if (b != null) {
                    if (this.c) {
                        a = this.a.a(this.b, b);
                    } else {
                        a = null;
                    }
                    com.facebook.common.f.a.c(b);
                    if (a != null) {
                        eVar2 = new e(a);
                        eVar2.b(eVar);
                        com.facebook.common.f.a.c(a);
                        d().b(1.0f);
                        d().b(eVar2, i);
                        e.d(eVar2);
                        b.a();
                        return;
                    }
                }
                d().b(eVar, i);
                b.a();
            } catch (Throwable th) {
                b.a();
            }
        }

        public a(Consumer<e> consumer, p<c, h> memoryCache, c requestedCacheKey, boolean isMemoryCacheEnabled) {
            super(consumer);
            this.a = memoryCache;
            this.b = requestedCacheKey;
            this.c = isMemoryCacheEnabled;
        }
    }

    public r(p<c, h> memoryCache, f cacheKeyFactory, aj<e> inputProducer) {
        this.a = memoryCache;
        this.b = cacheKeyFactory;
        this.c = inputProducer;
    }

    public final void a(Consumer<e> consumer, ak producerContext) {
        Map map = null;
        try {
            b.a();
            String requestId = producerContext.b();
            am listener = producerContext.c();
            listener.a(requestId, "EncodedMemoryCacheProducer");
            Object cacheKey = this.b.a(producerContext.a());
            com.facebook.common.f.a cachedReference = this.a.a(cacheKey);
            String str;
            if (cachedReference != null) {
                e cachedEncodedImage;
                try {
                    cachedEncodedImage = new e(cachedReference);
                    str = "EncodedMemoryCacheProducer";
                    if (listener.b(requestId)) {
                        map = com.facebook.common.internal.e.a("cached_value_found", "true");
                    }
                    listener.a(requestId, str, map);
                    listener.a(requestId, "EncodedMemoryCacheProducer", true);
                    consumer.b(1.0f);
                    consumer.b(cachedEncodedImage, 1);
                    e.d(cachedEncodedImage);
                    com.facebook.common.f.a.c(cachedReference);
                } catch (Throwable th) {
                    com.facebook.common.f.a.c(cachedReference);
                }
            } else if (producerContext.e().a() >= com.facebook.imagepipeline.k.b.b.ENCODED_MEMORY_CACHE.a()) {
                str = "EncodedMemoryCacheProducer";
                if (listener.b(requestId)) {
                    map = com.facebook.common.internal.e.a("cached_value_found", "false");
                }
                listener.a(requestId, str, map);
                listener.a(requestId, "EncodedMemoryCacheProducer", false);
                consumer.b(null, 1);
                com.facebook.common.f.a.c(cachedReference);
                b.a();
            } else {
                Consumer consumerOfInputProducer = new a(consumer, this.a, cacheKey, producerContext.a().o());
                str = "EncodedMemoryCacheProducer";
                if (listener.b(requestId)) {
                    map = com.facebook.common.internal.e.a("cached_value_found", "false");
                }
                listener.a(requestId, str, map);
                this.c.a(consumerOfInputProducer, producerContext);
                com.facebook.common.f.a.c(cachedReference);
                b.a();
            }
        } finally {
            b.a();
        }
    }
}
