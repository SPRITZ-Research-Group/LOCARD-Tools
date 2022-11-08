package com.facebook.imagepipeline.producers;

public final class as<T> implements aj<Void> {
    private final aj<T> a;

    public as(aj<T> inputProducer) {
        this.a = inputProducer;
    }

    public final void a(Consumer<Void> consumer, ak producerContext) {
        this.a.a(new m<T, Void>(this, consumer) {
            final /* synthetic */ as a;

            protected final void a(T t, int status) {
                if (b.a(status)) {
                    d().b(null, status);
                }
            }
        }, producerContext);
    }
}
