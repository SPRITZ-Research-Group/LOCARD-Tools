package com.facebook.imagepipeline.d;

import com.facebook.cache.a.c;
import com.facebook.common.e.d;
import com.facebook.common.e.h;
import com.facebook.common.internal.j;

public final class l {
    public static h<c, h> a(j<q> encodedMemoryCacheParamsSupplier, d memoryTrimmableRegistry) {
        h<c, h> countingCache = new h(new v<h>() {
            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return ((h) obj).a();
            }
        }, new s(), encodedMemoryCacheParamsSupplier);
        memoryTrimmableRegistry.a(countingCache);
        return countingCache;
    }
}
