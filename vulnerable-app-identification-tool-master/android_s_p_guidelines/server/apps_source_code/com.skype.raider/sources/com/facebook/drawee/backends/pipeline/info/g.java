package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.k.b;
import javax.annotation.Nullable;

public final class g {
    @Nullable
    private String a;
    @Nullable
    private String b;
    @Nullable
    private b c;
    @Nullable
    private Object d;
    @Nullable
    private ImageInfo e;
    private long f = -1;
    private long g = -1;
    private long h = -1;
    private long i = -1;
    private long j = -1;
    private long k = -1;
    private long l = -1;
    private int m = -1;
    private boolean n;
    private int o = -1;
    private int p = -1;
    private int q = -1;
    private int r = -1;
    private long s = -1;
    private long t = -1;

    public final void a() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = -1;
        this.l = -1;
        this.m = 1;
        this.n = false;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = -1;
    }

    public final void a(int imageLoadStatus) {
        this.q = imageLoadStatus;
    }

    public final int b() {
        return this.q;
    }

    public final void a(@Nullable String controllerId) {
        this.a = controllerId;
    }

    public final void b(@Nullable String requestId) {
        this.b = requestId;
    }

    public final void a(@Nullable b imageRequest) {
        this.c = imageRequest;
    }

    public final void a(@Nullable Object callerContext) {
        this.d = callerContext;
    }

    public final void a(long controllerSubmitTimeMs) {
        this.f = controllerSubmitTimeMs;
    }

    public final void b(long controllerIntermediateImageSetTimeMs) {
        this.g = controllerIntermediateImageSetTimeMs;
    }

    public final void c(long controllerFinalImageSetTimeMs) {
        this.h = controllerFinalImageSetTimeMs;
    }

    public final void d(long controllerFailureTimeMs) {
        this.i = controllerFailureTimeMs;
    }

    public final void e(long controllerCancelTimeMs) {
        this.j = controllerCancelTimeMs;
    }

    public final void f(long imageRequestStartTimeMs) {
        this.k = imageRequestStartTimeMs;
    }

    public final void g(long imageRequestEndTimeMs) {
        this.l = imageRequestEndTimeMs;
    }

    public final void h(long visibilityEventTimeMs) {
        this.s = visibilityEventTimeMs;
    }

    public final void i(long invisibilityEventTimeMs) {
        this.t = invisibilityEventTimeMs;
    }

    public final void b(int imageOrigin) {
        this.m = imageOrigin;
    }

    public final void a(boolean prefetch) {
        this.n = prefetch;
    }

    public final void a(@Nullable ImageInfo imageInfo) {
        this.e = imageInfo;
    }

    public final void c(int onScreenWidthPx) {
        this.o = onScreenWidthPx;
    }

    public final void d(int onScreenHeightPx) {
        this.p = onScreenHeightPx;
    }

    public final void b(boolean visible) {
        this.r = visible ? 1 : 2;
    }

    public final d c() {
        return new d(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.r, this.s, this.t);
    }
}
