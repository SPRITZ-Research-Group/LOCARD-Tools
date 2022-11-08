package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.facebook.common.e.h;
import com.facebook.common.f.a;
import com.facebook.common.internal.i;
import com.facebook.common.internal.j;
import com.facebook.datasource.d;
import com.facebook.imagepipeline.d.e;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.h.b;
import com.facebook.imagepipeline.h.c;
import com.facebook.imagepipeline.producers.aj;
import com.facebook.imagepipeline.producers.aq;
import com.facebook.imagepipeline.producers.au;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class g {
    private static final CancellationException a = new CancellationException("Prefetching is not enabled");
    private final m b;
    private final c c;
    private final j<Boolean> d;
    private final p<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> e;
    private final p<com.facebook.cache.a.c, h> f;
    private final e g;
    private final e h;
    private final f i;
    private final au j;
    private final j<Boolean> k;
    private AtomicLong l = new AtomicLong();
    private final j<Boolean> m;

    public g(m producerSequenceFactory, Set<c> requestListeners, j<Boolean> isPrefetchEnabledSupplier, p<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> bitmapMemoryCache, p<com.facebook.cache.a.c, h> encodedMemoryCache, e mainBufferedDiskCache, e smallImageBufferedDiskCache, f cacheKeyFactory, au threadHandoffProducerQueue, j<Boolean> suppressBitmapPrefetchingSupplier, j<Boolean> lazyDataSource) {
        this.b = producerSequenceFactory;
        this.c = new b((Set) requestListeners);
        this.d = isPrefetchEnabledSupplier;
        this.e = bitmapMemoryCache;
        this.f = encodedMemoryCache;
        this.g = mainBufferedDiskCache;
        this.h = smallImageBufferedDiskCache;
        this.i = cacheKeyFactory;
        this.j = threadHandoffProducerQueue;
        this.k = suppressBitmapPrefetchingSupplier;
        this.m = lazyDataSource;
    }

    private String e() {
        return String.valueOf(this.l.getAndIncrement());
    }

    public final com.facebook.datasource.c<a<com.facebook.imagepipeline.image.c>> a(com.facebook.imagepipeline.k.b imageRequest, Object callerContext) {
        return a(imageRequest, callerContext, com.facebook.imagepipeline.k.b.b.FULL_FETCH, null);
    }

    public final com.facebook.datasource.c<a<com.facebook.imagepipeline.image.c>> a(com.facebook.imagepipeline.k.b imageRequest, Object callerContext, com.facebook.imagepipeline.k.b.b lowestPermittedRequestLevelOnSubmit, @Nullable c requestListener) {
        try {
            return a(this.b.c(imageRequest), imageRequest, lowestPermittedRequestLevelOnSubmit, callerContext, requestListener);
        } catch (Throwable e) {
            return d.a(e);
        }
    }

    public final com.facebook.datasource.c<a<h>> a(com.facebook.imagepipeline.k.b imageRequest) {
        com.facebook.common.internal.h.a(imageRequest.b());
        try {
            aj producerSequence = this.b.a(imageRequest);
            if (imageRequest.f() != null) {
                imageRequest = com.facebook.imagepipeline.k.c.a(imageRequest).a(null).q();
            }
            return a(producerSequence, imageRequest, com.facebook.imagepipeline.k.b.b.FULL_FETCH, null, null);
        } catch (Throwable e) {
            return d.a(e);
        }
    }

    public final com.facebook.datasource.c<Void> b(com.facebook.imagepipeline.k.b imageRequest, Object callerContext) {
        return a(imageRequest, callerContext, com.facebook.imagepipeline.common.d.MEDIUM);
    }

    private com.facebook.datasource.c<Void> a(com.facebook.imagepipeline.k.b imageRequest, Object callerContext, com.facebook.imagepipeline.common.d priority) {
        if (!((Boolean) this.d.a()).booleanValue()) {
            return d.a(a);
        }
        try {
            return a(this.b.b(imageRequest), imageRequest, com.facebook.imagepipeline.k.b.b.FULL_FETCH, callerContext, priority);
        } catch (Throwable e) {
            return d.a(e);
        }
    }

    public final void a() {
        i allPredicate = new i<com.facebook.cache.a.c>(this) {
            final /* synthetic */ g a;

            {
                this.a = this$0;
            }

            public final /* bridge */ /* synthetic */ boolean a(Object obj) {
                return true;
            }
        };
        this.e.a(allPredicate);
        this.f.a(allPredicate);
    }

    public final void b() {
        a();
        this.g.a();
        this.h.a();
    }

    public final boolean b(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.e.b(d(uri));
    }

    public final p<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> c() {
        return this.e;
    }

    public final boolean c(Uri uri) {
        return a(uri, com.facebook.imagepipeline.k.b.a.SMALL) || a(uri, com.facebook.imagepipeline.k.b.a.DEFAULT);
    }

    private boolean a(Uri uri, com.facebook.imagepipeline.k.b.a cacheChoice) {
        com.facebook.imagepipeline.k.b imageRequest = com.facebook.imagepipeline.k.c.a(uri).a(cacheChoice).q();
        com.facebook.cache.a.c a = this.i.a(imageRequest);
        switch (imageRequest.a()) {
            case DEFAULT:
                return this.g.b(a);
            case SMALL:
                return this.h.b(a);
            default:
                return false;
        }
    }

    public final com.facebook.datasource.c<Boolean> b(com.facebook.imagepipeline.k.b imageRequest) {
        final com.facebook.cache.a.c cacheKey = this.i.a(imageRequest);
        final com.facebook.datasource.h<Boolean> dataSource = com.facebook.datasource.h.i();
        this.g.a(cacheKey).b(new b.f<Boolean, b.h<Boolean>>(this) {
            final /* synthetic */ g b;

            public final /* synthetic */ Object a(b.h hVar) throws Exception {
                if (hVar.b() || hVar.c() || !((Boolean) hVar.d()).booleanValue()) {
                    return this.b.h.a(cacheKey);
                }
                return b.h.a(Boolean.valueOf(true));
            }
        }).a(new b.f<Boolean, Void>(this) {
            final /* synthetic */ g b;

            public final /* synthetic */ Object a(b.h hVar) throws Exception {
                com.facebook.datasource.h hVar2 = dataSource;
                boolean z = (hVar.b() || hVar.c() || !((Boolean) hVar.d()).booleanValue()) ? false : true;
                hVar2.b(Boolean.valueOf(z));
                return null;
            }
        });
        return dataSource;
    }

    private <T> com.facebook.datasource.c<a<T>> a(aj<a<T>> producerSequence, com.facebook.imagepipeline.k.b imageRequest, com.facebook.imagepipeline.k.b.b lowestPermittedRequestLevelOnSubmit, Object callerContext, @Nullable c requestListener) {
        com.facebook.datasource.c<a<T>> a;
        boolean z = false;
        com.facebook.imagepipeline.l.b.a();
        c finalRequestListener = a(imageRequest, requestListener);
        try {
            com.facebook.imagepipeline.k.b.b lowestPermittedRequestLevel = com.facebook.imagepipeline.k.b.b.a(imageRequest.m(), lowestPermittedRequestLevelOnSubmit);
            String e = e();
            if (imageRequest.j() || !com.facebook.common.i.f.b(imageRequest.b())) {
                z = true;
            }
            a = com.facebook.imagepipeline.e.c.a(producerSequence, new aq(imageRequest, e, finalRequestListener, callerContext, lowestPermittedRequestLevel, false, z, imageRequest.l()), finalRequestListener);
        } catch (Throwable e2) {
            a = d.a(e2);
        } finally {
            com.facebook.imagepipeline.l.b.a();
        }
        return a;
    }

    private com.facebook.datasource.c<Void> a(aj<Void> producerSequence, com.facebook.imagepipeline.k.b imageRequest, com.facebook.imagepipeline.k.b.b lowestPermittedRequestLevelOnSubmit, Object callerContext, com.facebook.imagepipeline.common.d priority) {
        c requestListener = a(imageRequest, null);
        try {
            return com.facebook.imagepipeline.e.d.a(producerSequence, new aq(imageRequest, e(), requestListener, callerContext, com.facebook.imagepipeline.k.b.b.a(imageRequest.m(), lowestPermittedRequestLevelOnSubmit), true, false, priority), requestListener);
        } catch (Throwable e) {
            return d.a(e);
        }
    }

    private c a(com.facebook.imagepipeline.k.b imageRequest, @Nullable c requestListener) {
        if (requestListener == null) {
            if (imageRequest.r() == null) {
                return this.c;
            }
            return new b(this.c, imageRequest.r());
        } else if (imageRequest.r() == null) {
            return new b(this.c, requestListener);
        } else {
            return new b(this.c, requestListener, imageRequest.r());
        }
    }

    private i<com.facebook.cache.a.c> d(final Uri uri) {
        return new i<com.facebook.cache.a.c>(this) {
            final /* synthetic */ g b;

            public final /* bridge */ /* synthetic */ boolean a(Object obj) {
                return ((com.facebook.cache.a.c) obj).a(uri);
            }
        };
    }

    public final f d() {
        return this.i;
    }

    public final void a(Uri uri) {
        i d = d(uri);
        this.e.a(d);
        this.f.a(d);
        com.facebook.cache.a.c a = this.i.a(com.facebook.imagepipeline.k.b.a(uri));
        this.g.c(a);
        this.h.c(a);
    }
}
