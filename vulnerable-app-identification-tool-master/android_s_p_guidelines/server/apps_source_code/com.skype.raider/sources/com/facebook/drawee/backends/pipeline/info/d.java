package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.k.b;
import javax.annotation.Nullable;

public final class d {
    @Nullable
    private final String a;
    @Nullable
    private final String b;
    @Nullable
    private final Object c;
    @Nullable
    private final b d;
    @Nullable
    private final ImageInfo e;
    private final long f;
    private final long g;
    private final long h;
    private final long i;
    private final long j;
    private final long k;
    private final long l;
    private final int m;
    private final boolean n;
    private final int o;
    private final int p;
    private final int q;
    private final long r;
    private final long s;

    public d(@Nullable String controllerId, @Nullable String requestId, @Nullable b imageRequest, @Nullable Object callerContext, @Nullable ImageInfo imageInfo, long controllerSubmitTimeMs, long controllerIntermediateImageSetTimeMs, long controllerFinalImageSetTimeMs, long controllerFailureTimeMs, long controllerCancelTimeMs, long imageRequestStartTimeMs, long imageRequestEndTimeMs, int imageOrigin, boolean isPrefetch, int onScreenWidthPx, int onScreenHeightPx, int visibilityState, long visibilityEventTimeMs, long invisibilityEventTime) {
        this.a = controllerId;
        this.b = requestId;
        this.d = imageRequest;
        this.c = callerContext;
        this.e = imageInfo;
        this.f = controllerSubmitTimeMs;
        this.g = controllerIntermediateImageSetTimeMs;
        this.h = controllerFinalImageSetTimeMs;
        this.i = controllerFailureTimeMs;
        this.j = controllerCancelTimeMs;
        this.k = imageRequestStartTimeMs;
        this.l = imageRequestEndTimeMs;
        this.m = imageOrigin;
        this.n = isPrefetch;
        this.o = onScreenWidthPx;
        this.p = onScreenHeightPx;
        this.q = visibilityState;
        this.r = visibilityEventTimeMs;
        this.s = invisibilityEventTime;
    }
}
