package com.facebook.imagepipeline.e;

import com.facebook.common.f.a;
import com.facebook.imagepipeline.l.b;
import com.facebook.imagepipeline.producers.aj;
import com.facebook.imagepipeline.producers.aq;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class c<T> extends a<a<T>> {
    protected final /* synthetic */ void a(Object obj) {
        a.c((a) obj);
    }

    protected final /* synthetic */ void a(Object obj, int i) {
        super.a(a.b((a) obj), i);
    }

    public static <T> com.facebook.datasource.c<a<T>> a(aj<a<T>> producer, aq settableProducerContext, com.facebook.imagepipeline.h.c listener) {
        b.a();
        c<T> result = new c(producer, settableProducerContext, listener);
        b.a();
        return result;
    }

    private c(aj<a<T>> producer, aq settableProducerContext, com.facebook.imagepipeline.h.c listener) {
        super(producer, settableProducerContext, listener);
    }

    @Nullable
    public final /* synthetic */ Object d() {
        return a.b((a) super.d());
    }
}
