package com.facebook.imagepipeline.producers;

import com.facebook.imageformat.c;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;

public final class o implements aj<e> {
    private final com.facebook.imagepipeline.d.e a;
    private final com.facebook.imagepipeline.d.e b;
    private final f c;
    private final aj<e> d;

    private static class a extends m<e, e> {
        private final ak a;
        private final com.facebook.imagepipeline.d.e b;
        private final com.facebook.imagepipeline.d.e c;
        private final f d;

        /* synthetic */ a(Consumer x0, ak x1, com.facebook.imagepipeline.d.e x2, com.facebook.imagepipeline.d.e x3, f x4, byte b) {
            this(x0, x1, x2, x3, x4);
        }

        public final /* synthetic */ void a(Object obj, int i) {
            e eVar = (e) obj;
            if (b.b(i) || eVar == null || b.c(i) || eVar.e() == c.a) {
                d().b(eVar, i);
                return;
            }
            b a = this.a.a();
            com.facebook.cache.a.c a2 = this.d.a(a);
            if (a.a() == com.facebook.imagepipeline.k.b.a.SMALL) {
                this.c.a(a2, eVar);
            } else {
                this.b.a(a2, eVar);
            }
            d().b(eVar, i);
        }

        private a(Consumer<e> consumer, ak producerContext, com.facebook.imagepipeline.d.e defaultBufferedDiskCache, com.facebook.imagepipeline.d.e smallImageBufferedDiskCache, f cacheKeyFactory) {
            super(consumer);
            this.a = producerContext;
            this.b = defaultBufferedDiskCache;
            this.c = smallImageBufferedDiskCache;
            this.d = cacheKeyFactory;
        }
    }

    public o(com.facebook.imagepipeline.d.e defaultBufferedDiskCache, com.facebook.imagepipeline.d.e smallImageBufferedDiskCache, f cacheKeyFactory, aj<e> inputProducer) {
        this.a = defaultBufferedDiskCache;
        this.b = smallImageBufferedDiskCache;
        this.c = cacheKeyFactory;
        this.d = inputProducer;
    }

    public final void a(Consumer<e> consumer, ak producerContext) {
        if (producerContext.e().a() >= b.b.DISK_CACHE.a()) {
            consumer.b(null, 1);
            return;
        }
        Consumer aVar;
        if (producerContext.a().n()) {
            aVar = new a(consumer, producerContext, this.a, this.b, this.c, (byte) 0);
        } else {
            Consumer<e> aVar2 = consumer;
        }
        this.d.a(aVar2, producerContext);
    }
}
