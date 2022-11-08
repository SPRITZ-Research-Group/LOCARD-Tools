package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.common.e.a;
import com.facebook.common.e.h;
import com.facebook.common.e.i;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.f.c;
import com.facebook.imagepipeline.f.e;
import com.facebook.imagepipeline.producers.aa;
import com.facebook.imagepipeline.producers.ab;
import com.facebook.imagepipeline.producers.ac;
import com.facebook.imagepipeline.producers.ae;
import com.facebook.imagepipeline.producers.af;
import com.facebook.imagepipeline.producers.ag;
import com.facebook.imagepipeline.producers.ah;
import com.facebook.imagepipeline.producers.ai;
import com.facebook.imagepipeline.producers.aj;
import com.facebook.imagepipeline.producers.an;
import com.facebook.imagepipeline.producers.ap;
import com.facebook.imagepipeline.producers.as;
import com.facebook.imagepipeline.producers.at;
import com.facebook.imagepipeline.producers.au;
import com.facebook.imagepipeline.producers.av;
import com.facebook.imagepipeline.producers.az;
import com.facebook.imagepipeline.producers.g;
import com.facebook.imagepipeline.producers.k;
import com.facebook.imagepipeline.producers.n;
import com.facebook.imagepipeline.producers.o;
import com.facebook.imagepipeline.producers.q;
import com.facebook.imagepipeline.producers.r;
import com.facebook.imagepipeline.producers.v;
import com.facebook.imagepipeline.producers.w;
import com.facebook.imagepipeline.producers.x;
import com.facebook.imagepipeline.producers.y;

public final class l {
    private ContentResolver a;
    private Resources b;
    private AssetManager c;
    private final a d;
    private final c e;
    private final e f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final e j;
    private final i k;
    private final com.facebook.imagepipeline.d.e l;
    private final com.facebook.imagepipeline.d.e m;
    private final p<com.facebook.cache.a.c, h> n;
    private final p<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> o;
    private final f p;
    private final com.facebook.imagepipeline.c.f q;
    private final int r;
    private final int s;
    private boolean t;
    private final int u;

    public l(Context context, a byteArrayPool, c imageDecoder, e progressiveJpegConfig, boolean downsampleEnabled, boolean resizeAndRotateEnabledForNetwork, boolean decodeCancellationEnabled, e executorSupplier, i pooledByteBufferFactory, p<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> bitmapMemoryCache, p<com.facebook.cache.a.c, h> encodedMemoryCache, com.facebook.imagepipeline.d.e defaultBufferedDiskCache, com.facebook.imagepipeline.d.e smallImageBufferedDiskCache, f cacheKeyFactory, com.facebook.imagepipeline.c.f platformBitmapFactory, int bitmapPrepareToDrawMinSizeBytes, int bitmapPrepareToDrawMaxSizeBytes, boolean bitmapPrepareToDrawForPrefetch, int maxBitmapSize) {
        this.a = context.getApplicationContext().getContentResolver();
        this.b = context.getApplicationContext().getResources();
        this.c = context.getApplicationContext().getAssets();
        this.d = byteArrayPool;
        this.e = imageDecoder;
        this.f = progressiveJpegConfig;
        this.g = downsampleEnabled;
        this.h = resizeAndRotateEnabledForNetwork;
        this.i = decodeCancellationEnabled;
        this.j = executorSupplier;
        this.k = pooledByteBufferFactory;
        this.o = bitmapMemoryCache;
        this.n = encodedMemoryCache;
        this.l = defaultBufferedDiskCache;
        this.m = smallImageBufferedDiskCache;
        this.p = cacheKeyFactory;
        this.q = platformBitmapFactory;
        this.r = bitmapPrepareToDrawMinSizeBytes;
        this.s = bitmapPrepareToDrawMaxSizeBytes;
        this.t = bitmapPrepareToDrawForPrefetch;
        this.u = maxBitmapSize;
    }

    public static com.facebook.imagepipeline.producers.a a(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new com.facebook.imagepipeline.producers.a(inputProducer);
    }

    public final com.facebook.imagepipeline.producers.f b(aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> inputProducer) {
        return new com.facebook.imagepipeline.producers.f(this.o, this.p, inputProducer);
    }

    public final g c(aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> inputProducer) {
        return new g(this.p, inputProducer);
    }

    public final com.facebook.imagepipeline.producers.h d(aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> inputProducer) {
        return new com.facebook.imagepipeline.producers.h(this.o, this.p, inputProducer);
    }

    public final k a() {
        return new k(this.k);
    }

    public final com.facebook.imagepipeline.producers.l e(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new com.facebook.imagepipeline.producers.l(this.d, this.j.c(), this.e, this.f, this.g, this.h, this.i, inputProducer, this.u);
    }

    public final n f(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new n(this.l, this.m, this.p, inputProducer);
    }

    public final o g(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new o(this.l, this.m, this.p, inputProducer);
    }

    public final ag h(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new ag(this.l, this.p, this.k, this.d, inputProducer);
    }

    public final q i(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new q(this.p, inputProducer);
    }

    public final r j(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new r(this.n, this.p, inputProducer);
    }

    public final v b() {
        return new v(this.j.a(), this.k, this.c);
    }

    public final w c() {
        return new w(this.j.a(), this.k, this.a);
    }

    public final x d() {
        return new x(this.j.a(), this.k, this.a);
    }

    public final y e() {
        return new y(this.j.a(), this.k, this.a);
    }

    public final aa f() {
        return new aa(this.j.a(), this.k);
    }

    public final an g() {
        return new an(this.j.a(), this.k, this.a);
    }

    public final ab h() {
        return new ab(this.j.a(), this.k, this.b);
    }

    public final ac i() {
        return new ac(this.j.a(), this.a);
    }

    public final ae a(af networkFetcher) {
        return new ae(this.k, this.d, networkFetcher);
    }

    public final ah k(aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> inputProducer) {
        return new ah(this.o, this.p, inputProducer);
    }

    public final ai l(aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> inputProducer) {
        return new ai(inputProducer, this.q, this.j.d());
    }

    public final ap a(aj<com.facebook.imagepipeline.image.e> inputProducer, boolean isResizingEnabled, com.facebook.imagepipeline.transcoder.c imageTranscoderFactory) {
        return new ap(this.j.d(), this.k, inputProducer, isResizingEnabled, imageTranscoderFactory);
    }

    public static <T> as<T> m(aj<T> inputProducer) {
        return new as(inputProducer);
    }

    public static <T> at<T> a(aj<T> inputProducer, au inputThreadHandoffProducerQueue) {
        return new at(inputProducer, inputThreadHandoffProducerQueue);
    }

    public final <T> av<T> n(aj<T> inputProducer) {
        return new av(this.j.e(), inputProducer);
    }

    public final az o(aj<com.facebook.imagepipeline.image.e> inputProducer) {
        return new az(this.j.d(), this.k, inputProducer);
    }

    public final com.facebook.imagepipeline.producers.i p(aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> inputProducer) {
        return new com.facebook.imagepipeline.producers.i(inputProducer, this.r, this.s, this.t);
    }
}
