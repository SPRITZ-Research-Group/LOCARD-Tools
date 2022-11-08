package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.k.b;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class aq extends d {
    public aq(b overrideRequest, ak context) {
        this(overrideRequest, context.b(), context.c(), context.d(), context.e(), context.f(), context.h(), context.g());
    }

    public aq(b imageRequest, String id, am producerListener, Object callerContext, b.b lowestPermittedRequestLevel, boolean isPrefetch, boolean isIntermediateResultExpected, d priority) {
        super(imageRequest, id, producerListener, callerContext, lowestPermittedRequestLevel, isPrefetch, isIntermediateResultExpected, priority);
    }
}
