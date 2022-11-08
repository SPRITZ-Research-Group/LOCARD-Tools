package com.facebook.drawee.backends.pipeline.info.a;

import com.facebook.common.time.b;
import com.facebook.drawee.backends.pipeline.info.g;
import com.facebook.imagepipeline.h.a;

public final class c extends a {
    private final b a;
    private final g b;

    public c(b monotonicClock, g imagePerfState) {
        this.a = monotonicClock;
        this.b = imagePerfState;
    }

    public final void a(com.facebook.imagepipeline.k.b request, Object callerContext, String requestId, boolean isPrefetch) {
        this.b.f(this.a.now());
        this.b.a(request);
        this.b.a(callerContext);
        this.b.b(requestId);
        this.b.a(isPrefetch);
    }

    public final void a(com.facebook.imagepipeline.k.b request, String requestId, boolean isPrefetch) {
        this.b.g(this.a.now());
        this.b.a(request);
        this.b.b(requestId);
        this.b.a(isPrefetch);
    }

    public final void a(com.facebook.imagepipeline.k.b request, String requestId, Throwable throwable, boolean isPrefetch) {
        this.b.g(this.a.now());
        this.b.a(request);
        this.b.b(requestId);
        this.b.a(isPrefetch);
    }

    public final void a_(String requestId) {
        this.b.g(this.a.now());
        this.b.b(requestId);
    }
}
