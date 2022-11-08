package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.b;
import com.facebook.cache.disk.c;
import com.facebook.cache.disk.e;

public final class d implements c {
    public final c a(b diskCacheConfig) {
        return new e(diskCacheConfig.a(), diskCacheConfig.c(), diskCacheConfig.b(), diskCacheConfig.h());
    }
}
