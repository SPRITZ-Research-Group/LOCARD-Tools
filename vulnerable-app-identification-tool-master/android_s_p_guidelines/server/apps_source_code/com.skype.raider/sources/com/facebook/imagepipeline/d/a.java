package com.facebook.imagepipeline.d;

import com.facebook.cache.a.c;
import com.facebook.common.e.d;
import com.facebook.common.internal.j;

public final class a {
    public static h<c, com.facebook.imagepipeline.image.c> a(j<q> bitmapMemoryCacheParamsSupplier, d memoryTrimmableRegistry, com.facebook.imagepipeline.d.h.a trimStrategy) {
        h<c, com.facebook.imagepipeline.image.c> countingCache = new h(new v<com.facebook.imagepipeline.image.c>() {
            public final /* synthetic */ int a(Object obj) {
                return ((com.facebook.imagepipeline.image.c) obj).d();
            }
        }, trimStrategy, bitmapMemoryCacheParamsSupplier);
        memoryTrimmableRegistry.a(countingCache);
        return countingCache;
    }
}
