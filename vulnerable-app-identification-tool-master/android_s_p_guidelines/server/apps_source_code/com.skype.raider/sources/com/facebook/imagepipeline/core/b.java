package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.c;
import com.facebook.cache.disk.d;
import com.facebook.cache.disk.h;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class b implements f {
    private c a;

    public b(c diskStorageFactory) {
        this.a = diskStorageFactory;
    }

    public final h a(com.facebook.cache.disk.b diskCacheConfig) {
        c a = this.a.a(diskCacheConfig);
        Executor newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        return new d(a, diskCacheConfig.g(), new com.facebook.cache.disk.d.b(diskCacheConfig.f(), diskCacheConfig.e(), diskCacheConfig.d()), diskCacheConfig.i(), diskCacheConfig.h(), newSingleThreadExecutor, diskCacheConfig.j());
    }
}
