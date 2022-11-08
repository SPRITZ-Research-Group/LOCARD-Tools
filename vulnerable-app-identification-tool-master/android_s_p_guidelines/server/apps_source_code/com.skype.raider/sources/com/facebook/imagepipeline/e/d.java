package com.facebook.imagepipeline.e;

import com.facebook.datasource.c;
import com.facebook.imagepipeline.producers.aj;
import com.facebook.imagepipeline.producers.aq;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class d<T> extends a<T> {
    public static <T> c<T> a(aj<T> producer, aq settableProducerContext, com.facebook.imagepipeline.h.c listener) {
        return new d(producer, settableProducerContext, listener);
    }

    private d(aj<T> producer, aq settableProducerContext, com.facebook.imagepipeline.h.c listener) {
        super(producer, settableProducerContext, listener);
    }
}
