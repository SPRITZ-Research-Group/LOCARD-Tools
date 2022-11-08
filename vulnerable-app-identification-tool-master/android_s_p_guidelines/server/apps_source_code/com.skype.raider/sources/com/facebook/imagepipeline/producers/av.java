package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.h;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public final class av<T> implements aj<T> {
    private final aj<T> a;
    private final int b = 5;
    @GuardedBy("this")
    private int c;
    @GuardedBy("this")
    private final ConcurrentLinkedQueue<Pair<Consumer<T>, ak>> d;
    private final Executor e;

    private class a extends m<T, T> {
        final /* synthetic */ av a;

        /* synthetic */ a(av x0, Consumer x1, byte b) {
            this(x0, x1);
        }

        private a(av avVar, Consumer<T> consumer) {
            this.a = avVar;
            super(consumer);
        }

        protected final void a(T newResult, int status) {
            d().b(newResult, status);
            if (b.a(status)) {
                c();
            }
        }

        protected final void a(Throwable t) {
            d().b(t);
            c();
        }

        protected final void a() {
            d().b();
            c();
        }

        private void c() {
            final Pair<Consumer<T>, ak> nextRequestPair;
            synchronized (this.a) {
                nextRequestPair = (Pair) this.a.d.poll();
                if (nextRequestPair == null) {
                    this.a.c = this.a.c - 1;
                }
            }
            if (nextRequestPair != null) {
                this.a.e.execute(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        this.b.a.b((Consumer) nextRequestPair.first, (ak) nextRequestPair.second);
                    }
                });
            }
        }
    }

    public av(Executor executor, aj<T> inputProducer) {
        this.e = (Executor) h.a((Object) executor);
        this.a = (aj) h.a((Object) inputProducer);
        this.d = new ConcurrentLinkedQueue();
        this.c = 0;
    }

    public final void a(Consumer<T> consumer, ak producerContext) {
        boolean delayRequest;
        producerContext.c().a(producerContext.b(), "ThrottlingProducer");
        synchronized (this) {
            if (this.c >= this.b) {
                this.d.add(Pair.create(consumer, producerContext));
                delayRequest = true;
            } else {
                this.c++;
                delayRequest = false;
            }
        }
        if (!delayRequest) {
            b(consumer, producerContext);
        }
    }

    final void b(Consumer<T> consumer, ak producerContext) {
        producerContext.c().a(producerContext.b(), "ThrottlingProducer", null);
        this.a.a(new a(this, consumer, (byte) 0), producerContext);
    }
}
