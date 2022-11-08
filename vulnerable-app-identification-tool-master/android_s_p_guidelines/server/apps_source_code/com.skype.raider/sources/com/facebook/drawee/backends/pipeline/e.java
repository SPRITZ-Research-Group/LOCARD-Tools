package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import com.facebook.cache.a.c;
import com.facebook.common.internal.d;
import com.facebook.common.internal.j;
import com.facebook.drawee.a.a;
import com.facebook.imagepipeline.d.p;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public final class e {
    private Resources a;
    private a b;
    private com.facebook.imagepipeline.g.a c;
    private Executor d;
    private p<c, com.facebook.imagepipeline.image.c> e;
    @Nullable
    private d<com.facebook.imagepipeline.g.a> f;
    @Nullable
    private j<Boolean> g;

    public final void a(Resources resources, a deferredReleaser, com.facebook.imagepipeline.g.a animatedDrawableFactory, Executor uiThreadExecutor, p<c, com.facebook.imagepipeline.image.c> memoryCache, @Nullable d<com.facebook.imagepipeline.g.a> dVar, @Nullable j<Boolean> jVar) {
        this.a = resources;
        this.b = deferredReleaser;
        this.c = animatedDrawableFactory;
        this.d = uiThreadExecutor;
        this.e = memoryCache;
        this.f = null;
        this.g = null;
    }

    public final b a() {
        b controller = new b(this.a, this.b, this.c, this.d, this.e, this.f);
        if (this.g != null) {
            controller.a(((Boolean) this.g.a()).booleanValue());
        }
        return controller;
    }
}
