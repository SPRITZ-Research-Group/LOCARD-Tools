package com.facebook.imagepipeline.producers;

import b.h;
import com.facebook.cache.a.c;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import com.facebook.imagepipeline.k.b.a;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class n implements aj<e> {
    private final com.facebook.imagepipeline.d.e a;
    private final com.facebook.imagepipeline.d.e b;
    private final f c;
    private final aj<e> d;

    public n(com.facebook.imagepipeline.d.e defaultBufferedDiskCache, com.facebook.imagepipeline.d.e smallImageBufferedDiskCache, f cacheKeyFactory, aj<e> inputProducer) {
        this.a = defaultBufferedDiskCache;
        this.b = smallImageBufferedDiskCache;
        this.c = cacheKeyFactory;
        this.d = inputProducer;
    }

    public final void a(Consumer<e> consumer, ak producerContext) {
        int i = 1;
        b imageRequest = producerContext.a();
        if (imageRequest.n()) {
            producerContext.c().a(producerContext.b(), "DiskCacheProducer");
            c cacheKey = this.c.a(imageRequest);
            if (imageRequest.a() != a.SMALL) {
                i = 0;
            }
            com.facebook.imagepipeline.d.e preferredCache = i != 0 ? this.b : this.a;
            final AtomicBoolean isCancelled = new AtomicBoolean(false);
            h<e> diskLookupTask = preferredCache.a(cacheKey, isCancelled);
            final String b = producerContext.b();
            final am c = producerContext.c();
            final Consumer<e> consumer2 = consumer;
            final ak akVar = producerContext;
            diskLookupTask.a(new b.f<e, Void>(this) {
                final /* synthetic */ n e;

                public final /* synthetic */ Object a(h hVar) throws Exception {
                    boolean z;
                    if (hVar.b() || (hVar.c() && (hVar.e() instanceof CancellationException))) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        c.b(b, "DiskCacheProducer", null);
                        consumer2.b();
                    } else if (hVar.c()) {
                        c.a(b, "DiskCacheProducer", hVar.e(), null);
                        this.e.d.a(consumer2, akVar);
                    } else {
                        e eVar = (e) hVar.d();
                        if (eVar != null) {
                            c.a(b, "DiskCacheProducer", n.a(c, b, true, eVar.l()));
                            c.a(b, "DiskCacheProducer", true);
                            consumer2.b(1.0f);
                            consumer2.b(eVar, 1);
                            eVar.close();
                        } else {
                            c.a(b, "DiskCacheProducer", n.a(c, b, false, 0));
                            this.e.d.a(consumer2, akVar);
                        }
                    }
                    return null;
                }
            });
            producerContext.a(new e(this) {
                final /* synthetic */ n b;

                public final void a() {
                    isCancelled.set(true);
                }
            });
        } else if (producerContext.e().a() >= b.b.DISK_CACHE.a()) {
            consumer.b(null, 1);
        } else {
            this.d.a(consumer, producerContext);
        }
    }

    @VisibleForTesting
    static Map<String, String> a(am listener, String requestId, boolean valueFound, int sizeInBytes) {
        if (!listener.b(requestId)) {
            return null;
        }
        if (valueFound) {
            return com.facebook.common.internal.e.a("cached_value_found", String.valueOf(valueFound), "encodedImageSize", String.valueOf(sizeInBytes));
        }
        return com.facebook.common.internal.e.a("cached_value_found", String.valueOf(valueFound));
    }
}
