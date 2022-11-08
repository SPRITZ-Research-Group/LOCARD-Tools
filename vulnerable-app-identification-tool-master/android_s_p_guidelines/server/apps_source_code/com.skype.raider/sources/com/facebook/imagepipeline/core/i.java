package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.common.e.h;
import com.facebook.common.internal.j;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.f.e;

public final class i {
    private final boolean a;
    private final com.facebook.common.j.a.a b;
    private final boolean c;
    private final com.facebook.common.j.a d;
    private final boolean e;
    private final boolean f;
    private final int g;
    private final int h;
    private boolean i;
    private final int j;
    private final boolean k;
    private final boolean l;
    private final c m;
    private final j<Boolean> n;

    public static class a {
        public boolean a = false;
        public j<Boolean> b;
        private final com.facebook.imagepipeline.core.h.a c;
        private boolean d = false;
        private com.facebook.common.j.a.a e;
        private boolean f = false;
        private com.facebook.common.j.a g;
        private boolean h = false;
        private boolean i = false;
        private int j = 0;
        private int k = 0;
        private int l = 2048;
        private boolean m = false;
        private boolean n = false;
        private c o;

        public a(com.facebook.imagepipeline.core.h.a configBuilder) {
            this.c = configBuilder;
        }
    }

    public interface c {
        l a(Context context, com.facebook.common.e.a aVar, com.facebook.imagepipeline.f.c cVar, e eVar, boolean z, boolean z2, boolean z3, e eVar2, com.facebook.common.e.i iVar, p<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> pVar, p<com.facebook.cache.a.c, h> pVar2, com.facebook.imagepipeline.d.e eVar3, com.facebook.imagepipeline.d.e eVar4, f fVar, com.facebook.imagepipeline.c.f fVar2, int i, int i2, boolean z4, int i3);
    }

    public static class b implements c {
        public final l a(Context context, com.facebook.common.e.a byteArrayPool, com.facebook.imagepipeline.f.c imageDecoder, e progressiveJpegConfig, boolean downsampleEnabled, boolean resizeAndRotateEnabledForNetwork, boolean decodeCancellationEnabled, e executorSupplier, com.facebook.common.e.i pooledByteBufferFactory, p<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> bitmapMemoryCache, p<com.facebook.cache.a.c, h> encodedMemoryCache, com.facebook.imagepipeline.d.e defaultBufferedDiskCache, com.facebook.imagepipeline.d.e smallImageBufferedDiskCache, f cacheKeyFactory, com.facebook.imagepipeline.c.f platformBitmapFactory, int bitmapPrepareToDrawMinSizeBytes, int bitmapPrepareToDrawMaxSizeBytes, boolean bitmapPrepareToDrawForPrefetch, int maxBitmapSize) {
            return new l(context, byteArrayPool, imageDecoder, progressiveJpegConfig, downsampleEnabled, resizeAndRotateEnabledForNetwork, decodeCancellationEnabled, executorSupplier, pooledByteBufferFactory, bitmapMemoryCache, encodedMemoryCache, defaultBufferedDiskCache, smallImageBufferedDiskCache, cacheKeyFactory, platformBitmapFactory, bitmapPrepareToDrawMinSizeBytes, bitmapPrepareToDrawMaxSizeBytes, bitmapPrepareToDrawForPrefetch, maxBitmapSize);
        }
    }

    /* synthetic */ i(a x0, byte b) {
        this(x0);
    }

    private i(a builder) {
        this.a = builder.d;
        this.b = builder.e;
        this.c = builder.f;
        this.d = builder.g;
        this.e = builder.h;
        this.f = builder.i;
        this.g = builder.j;
        this.h = builder.k;
        this.i = builder.a;
        this.j = builder.l;
        this.k = builder.m;
        this.l = builder.n;
        if (builder.o == null) {
            this.m = new b();
        } else {
            this.m = builder.o;
        }
        this.n = builder.b;
    }

    public final boolean a() {
        return this.e;
    }

    public final boolean b() {
        return this.a;
    }

    public final boolean c() {
        return this.c;
    }

    public final com.facebook.common.j.a d() {
        return this.d;
    }

    public final boolean e() {
        return this.f;
    }

    public final int f() {
        return this.g;
    }

    public final int g() {
        return this.h;
    }

    public final boolean h() {
        return this.k;
    }

    public final boolean i() {
        return this.l;
    }

    public final c j() {
        return this.m;
    }

    public final boolean k() {
        return this.i;
    }

    public final int l() {
        return this.j;
    }

    public final j<Boolean> m() {
        return this.n;
    }
}
