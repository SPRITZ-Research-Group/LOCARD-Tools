package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.net.Uri;
import com.facebook.common.e.h;
import com.facebook.common.f.a;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import com.facebook.imagepipeline.producers.af;
import com.facebook.imagepipeline.producers.aj;
import com.facebook.imagepipeline.producers.ao;
import com.facebook.imagepipeline.producers.au;
import com.facebook.imagepipeline.producers.aw;
import com.facebook.imagepipeline.producers.ax;
import com.facebook.imagepipeline.producers.j;
import com.facebook.imagepipeline.transcoder.c;
import java.util.HashMap;
import java.util.Map;

public final class m {
    private final boolean A;
    private final c B;
    private aj<e> C;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> a;
    @VisibleForTesting
    aj<e> b;
    @VisibleForTesting
    aj<e> c;
    @VisibleForTesting
    aj<a<h>> d;
    @VisibleForTesting
    aj<a<h>> e;
    @VisibleForTesting
    aj<Void> f;
    @VisibleForTesting
    aj<Void> g;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> h;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> i;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> j;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> k;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> l;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> m;
    @VisibleForTesting
    aj<a<com.facebook.imagepipeline.image.c>> n;
    @VisibleForTesting
    Map<aj<a<com.facebook.imagepipeline.image.c>>, aj<a<com.facebook.imagepipeline.image.c>>> o = new HashMap();
    @VisibleForTesting
    Map<aj<a<com.facebook.imagepipeline.image.c>>, aj<Void>> p = new HashMap();
    @VisibleForTesting
    Map<aj<a<com.facebook.imagepipeline.image.c>>, aj<a<com.facebook.imagepipeline.image.c>>> q = new HashMap();
    private final ContentResolver r;
    private final l s;
    private final af t;
    private final boolean u;
    private final boolean v;
    private final boolean w;
    private final au x;
    private final boolean y;
    private final boolean z;

    public m(ContentResolver contentResolver, l producerFactory, af networkFetcher, boolean resizeAndRotateEnabledForNetwork, boolean webpSupportEnabled, au threadHandoffProducerQueue, boolean downSampleEnabled, boolean useBitmapPrepareToDraw, boolean partialImageCachingEnabled, boolean diskCacheEnabled, c imageTranscoderFactory) {
        this.r = contentResolver;
        this.s = producerFactory;
        this.t = networkFetcher;
        this.u = resizeAndRotateEnabledForNetwork;
        this.v = webpSupportEnabled;
        this.x = threadHandoffProducerQueue;
        this.y = downSampleEnabled;
        this.z = useBitmapPrepareToDraw;
        this.w = partialImageCachingEnabled;
        this.A = diskCacheEnabled;
        this.B = imageTranscoderFactory;
    }

    public final aj<a<h>> a(b imageRequest) {
        aj<a<h>> a;
        try {
            com.facebook.imagepipeline.l.b.a();
            d(imageRequest);
            Uri uri = imageRequest.b();
            switch (imageRequest.c()) {
                case 0:
                    a = a();
                    break;
                case 2:
                case 3:
                    a = b();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + a(uri));
            }
        } finally {
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return a;
    }

    private aj<a<h>> a() {
        synchronized (this) {
            com.facebook.imagepipeline.l.b.a();
            if (this.e == null) {
                com.facebook.imagepipeline.l.b.a();
                this.e = new ao(d());
                com.facebook.imagepipeline.l.b.a();
            }
            com.facebook.imagepipeline.l.b.a();
        }
        return this.e;
    }

    private aj<a<h>> b() {
        synchronized (this) {
            com.facebook.imagepipeline.l.b.a();
            if (this.d == null) {
                com.facebook.imagepipeline.l.b.a();
                this.d = new ao(h());
                com.facebook.imagepipeline.l.b.a();
            }
            com.facebook.imagepipeline.l.b.a();
        }
        return this.d;
    }

    public final aj<Void> b(b imageRequest) {
        d(imageRequest);
        switch (imageRequest.c()) {
            case 0:
                return e();
            case 2:
            case 3:
                return g();
            default:
                throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + a(imageRequest.b()));
        }
    }

    private static void d(b imageRequest) {
        com.facebook.common.internal.h.a((Object) imageRequest);
        com.facebook.common.internal.h.a(imageRequest.m().a() <= b.b.ENCODED_MEMORY_CACHE.a());
    }

    public final aj<a<com.facebook.imagepipeline.image.c>> c(b imageRequest) {
        com.facebook.imagepipeline.l.b.a();
        aj<a<com.facebook.imagepipeline.image.c>> pipelineSequence = e(imageRequest);
        if (imageRequest.q() != null) {
            pipelineSequence = e((aj) pipelineSequence);
        }
        if (this.z) {
            pipelineSequence = f(pipelineSequence);
        }
        com.facebook.imagepipeline.l.b.a();
        return pipelineSequence;
    }

    private aj<a<com.facebook.imagepipeline.image.c>> e(b imageRequest) {
        aj<a<com.facebook.imagepipeline.image.c>> c;
        try {
            com.facebook.imagepipeline.l.b.a();
            com.facebook.common.internal.h.a((Object) imageRequest);
            Uri uri = imageRequest.b();
            com.facebook.common.internal.h.a((Object) uri, (Object) "Uri is null.");
            switch (imageRequest.c()) {
                case 0:
                    c = c();
                    break;
                case 2:
                    c = j();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                case 3:
                    c = i();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                case 4:
                    if (!com.facebook.common.d.a.a(this.r.getType(uri))) {
                        c = k();
                        com.facebook.imagepipeline.l.b.a();
                        break;
                    }
                    c = j();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                case 5:
                    c = n();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                case 6:
                    c = m();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                case 7:
                    c = o();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                case 8:
                    c = l();
                    com.facebook.imagepipeline.l.b.a();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + a(uri));
            }
        } finally {
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return c;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> c() {
        com.facebook.imagepipeline.l.b.a();
        if (this.a == null) {
            com.facebook.imagepipeline.l.b.a();
            this.a = b(f());
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return this.a;
    }

    private synchronized aj<e> d() {
        com.facebook.imagepipeline.l.b.a();
        if (this.c == null) {
            com.facebook.imagepipeline.l.b.a();
            this.c = l.a(f(), this.x);
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return this.c;
    }

    private synchronized aj<Void> e() {
        com.facebook.imagepipeline.l.b.a();
        if (this.g == null) {
            com.facebook.imagepipeline.l.b.a();
            this.g = l.m(d());
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return this.g;
    }

    private synchronized aj<e> f() {
        com.facebook.imagepipeline.l.b.a();
        if (this.C == null) {
            com.facebook.imagepipeline.l.b.a();
            this.C = l.a(c(this.s.a(this.t)));
            l lVar = this.s;
            aj ajVar = this.C;
            boolean z = this.u && !this.y;
            this.C = lVar.a(ajVar, z, this.B);
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return this.C;
    }

    private synchronized aj<Void> g() {
        com.facebook.imagepipeline.l.b.a();
        if (this.f == null) {
            com.facebook.imagepipeline.l.b.a();
            this.f = l.m(h());
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return this.f;
    }

    private synchronized aj<e> h() {
        com.facebook.imagepipeline.l.b.a();
        if (this.b == null) {
            com.facebook.imagepipeline.l.b.a();
            this.b = l.a(c(this.s.f()), this.x);
            com.facebook.imagepipeline.l.b.a();
        }
        com.facebook.imagepipeline.l.b.a();
        return this.b;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> i() {
        if (this.h == null) {
            this.h = a(this.s.f());
        }
        return this.h;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> j() {
        if (this.i == null) {
            this.i = d(this.s.i());
        }
        return this.i;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> k() {
        if (this.j == null) {
            this.j = a(this.s.c(), new ax[]{this.s.d(), this.s.e()});
        }
        return this.j;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> l() {
        if (this.n == null) {
            this.n = a(this.s.g());
        }
        return this.n;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> m() {
        if (this.k == null) {
            this.k = a(this.s.h());
        }
        return this.k;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> n() {
        if (this.l == null) {
            this.l = a(this.s.b());
        }
        return this.l;
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> o() {
        if (this.m == null) {
            aj inputProducer = this.s.a();
            if (com.facebook.common.j.b.a && (!this.v || com.facebook.common.j.b.d == null)) {
                inputProducer = this.s.o(inputProducer);
            }
            this.m = b(this.s.a(l.a(inputProducer), true, this.B));
        }
        return this.m;
    }

    private aj<a<com.facebook.imagepipeline.image.c>> a(aj<e> inputProducer) {
        return a(inputProducer, new ax[]{this.s.e()});
    }

    private aj<a<com.facebook.imagepipeline.image.c>> a(aj<e> inputProducer, ax<e>[] thumbnailProducers) {
        return b(new j(this.s.a(new aw(thumbnailProducers), true, this.B), this.s.n(this.s.a(l.a(c((aj) inputProducer)), true, this.B))));
    }

    private aj<a<com.facebook.imagepipeline.image.c>> b(aj<e> inputProducer) {
        com.facebook.imagepipeline.l.b.a();
        aj<a<com.facebook.imagepipeline.image.c>> result = d(this.s.e(inputProducer));
        com.facebook.imagepipeline.l.b.a();
        return result;
    }

    private aj<e> c(aj<e> inputProducer) {
        if (com.facebook.common.j.b.a && (!this.v || com.facebook.common.j.b.d == null)) {
            inputProducer = this.s.o(inputProducer);
        }
        if (this.A) {
            aj g;
            com.facebook.imagepipeline.l.b.a();
            if (this.w) {
                g = this.s.g(this.s.h(inputProducer));
            } else {
                g = this.s.g(inputProducer);
            }
            inputProducer = this.s.f(g);
            com.facebook.imagepipeline.l.b.a();
        }
        return this.s.i(this.s.j(inputProducer));
    }

    private aj<a<com.facebook.imagepipeline.image.c>> d(aj<a<com.facebook.imagepipeline.image.c>> inputProducer) {
        return this.s.b(l.a(this.s.c(this.s.d(inputProducer)), this.x));
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> e(aj<a<com.facebook.imagepipeline.image.c>> inputProducer) {
        if (!this.o.containsKey(inputProducer)) {
            this.o.put(inputProducer, this.s.k(this.s.l(inputProducer)));
        }
        return (aj) this.o.get(inputProducer);
    }

    private synchronized aj<a<com.facebook.imagepipeline.image.c>> f(aj<a<com.facebook.imagepipeline.image.c>> inputProducer) {
        aj<a<com.facebook.imagepipeline.image.c>> bitmapPrepareProducer;
        bitmapPrepareProducer = (aj) this.q.get(inputProducer);
        if (bitmapPrepareProducer == null) {
            bitmapPrepareProducer = this.s.p(inputProducer);
            this.q.put(inputProducer, bitmapPrepareProducer);
        }
        return bitmapPrepareProducer;
    }

    private static String a(Uri uri) {
        String uriString = String.valueOf(uri);
        return uriString.length() > 30 ? uriString.substring(0, 30) + "..." : uriString;
    }
}
