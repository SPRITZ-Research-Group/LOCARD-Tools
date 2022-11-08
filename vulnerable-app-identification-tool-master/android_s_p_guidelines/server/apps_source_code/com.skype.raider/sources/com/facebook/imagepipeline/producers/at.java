package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.h;

public final class at<T> implements aj<T> {
    private final aj<T> a;
    private final au b;

    public at(aj<T> inputProducer, au inputThreadHandoffProducerQueue) {
        this.a = (aj) h.a((Object) inputProducer);
        this.b = inputThreadHandoffProducerQueue;
    }

    public final void a(Consumer<T> consumer, ak context) {
        am producerListener = context.c();
        String requestId = context.b();
        final am amVar = producerListener;
        final String str = requestId;
        final Consumer<T> consumer2 = consumer;
        final ak akVar = context;
        final ar<T> statefulRunnable = new ar<T>(this, consumer, producerListener, "BackgroundThreadHandoffProducer", requestId) {
            final /* synthetic */ at f;

            protected final void a(T t) {
                amVar.a(str, "BackgroundThreadHandoffProducer", null);
                this.f.a.a(consumer2, akVar);
            }

            protected final void b(T t) {
            }

            protected final T c() throws Exception {
                return null;
            }
        };
        context.a(new e(this) {
            final /* synthetic */ at b;

            public final void a() {
                statefulRunnable.a();
                this.b.b.b(statefulRunnable);
            }
        });
        this.b.a(statefulRunnable);
    }
}
