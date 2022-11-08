package com.facebook.imagepipeline.producers;

public abstract class m<I, O> extends b<I> {
    private final Consumer<O> a;

    public m(Consumer<O> consumer) {
        this.a = consumer;
    }

    public final Consumer<O> d() {
        return this.a;
    }

    protected void a(Throwable t) {
        this.a.b(t);
    }

    protected void a() {
        this.a.b();
    }

    protected void a(float progress) {
        this.a.b(progress);
    }
}
