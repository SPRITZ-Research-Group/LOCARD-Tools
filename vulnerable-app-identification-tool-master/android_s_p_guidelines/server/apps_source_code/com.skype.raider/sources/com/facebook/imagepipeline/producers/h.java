package com.facebook.imagepipeline.producers;

import com.facebook.common.f.a;
import com.facebook.common.internal.e;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.image.c;
import com.facebook.imagepipeline.image.g;
import com.facebook.imagepipeline.l.b;

public class h implements aj<a<c>> {
    private final p<com.facebook.cache.a.c, c> a;
    private final f b;
    private final aj<a<c>> c;

    public h(p<com.facebook.cache.a.c, c> memoryCache, f cacheKeyFactory, aj<a<c>> inputProducer) {
        this.a = memoryCache;
        this.b = cacheKeyFactory;
        this.c = inputProducer;
    }

    public final void a(Consumer<a<c>> consumer, ak producerContext) {
        try {
            b.a();
            am listener = producerContext.c();
            String requestId = producerContext.b();
            listener.a(requestId, a());
            Object cacheKey = this.b.a(producerContext.a(), producerContext.d());
            a<c> cachedReference = this.a.a(cacheKey);
            if (cachedReference != null) {
                int i;
                boolean isFinal = ((c) cachedReference.a()).g().c();
                if (isFinal) {
                    listener.a(requestId, a(), listener.b(requestId) ? e.a("cached_value_found", "true") : null);
                    listener.a(requestId, a(), true);
                    consumer.b(1.0f);
                }
                if (isFinal) {
                    i = 1;
                } else {
                    i = 0;
                }
                consumer.b(cachedReference, i);
                cachedReference.close();
                if (isFinal) {
                    return;
                }
            }
            if (producerContext.e().a() >= com.facebook.imagepipeline.k.b.b.BITMAP_MEMORY_CACHE.a()) {
                listener.a(requestId, a(), listener.b(requestId) ? e.a("cached_value_found", "false") : null);
                listener.a(requestId, a(), false);
                consumer.b(null, 1);
                b.a();
                return;
            }
            Consumer<a<c>> wrappedConsumer = a(consumer, cacheKey, producerContext.a().o());
            listener.a(requestId, a(), listener.b(requestId) ? e.a("cached_value_found", "false") : null);
            b.a();
            this.c.a(wrappedConsumer, producerContext);
            b.a();
            b.a();
        } finally {
            b.a();
        }
    }

    protected Consumer<a<c>> a(Consumer<a<c>> consumer, final com.facebook.cache.a.c cacheKey, final boolean isMemoryCacheEnabled) {
        return new m<a<c>, a<c>>(this, consumer) {
            final /* synthetic */ h c;

            public final /* synthetic */ void a(Object obj, int i) {
                a aVar = null;
                obj = (a) obj;
                a a;
                try {
                    b.a();
                    boolean a2 = b.a(i);
                    if (obj == null) {
                        if (a2) {
                            d().b(null, i);
                        }
                        b.a();
                    } else if (((c) obj.a()).e() || b.a(i, 8)) {
                        d().b(obj, i);
                        b.a();
                    } else {
                        if (!a2) {
                            a = this.c.a.a(cacheKey);
                            if (a != null) {
                                g g = ((c) obj.a()).g();
                                g g2 = ((c) a.a()).g();
                                if (g2.c() || g2.a() >= g.a()) {
                                    d().b(a, i);
                                    a.c(a);
                                    b.a();
                                    return;
                                }
                                a.c(a);
                            }
                        }
                        if (isMemoryCacheEnabled) {
                            aVar = this.c.a.a(cacheKey, obj);
                        }
                        if (a2) {
                            d().b(1.0f);
                        }
                        Consumer d = d();
                        if (aVar != null) {
                            obj = aVar;
                        }
                        d.b(obj, i);
                        a.c(aVar);
                        b.a();
                    }
                } catch (Throwable th) {
                    b.a();
                }
            }
        };
    }

    protected String a() {
        return "BitmapMemoryCacheProducer";
    }
}
