package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.h;
import com.facebook.imagepipeline.image.e;

public final class aw implements aj<e> {
    private final ax<e>[] a;

    private class a extends m<e, e> {
        final /* synthetic */ aw a;
        private final ak b;
        private final int c;
        private final com.facebook.imagepipeline.common.e d = this.b.a().f();

        protected final /* synthetic */ void a(Object obj, int i) {
            e eVar = (e) obj;
            if (eVar != null && (b.b(i) || ay.a(eVar, this.d))) {
                d().b(eVar, i);
            } else if (b.a(i)) {
                e.d(eVar);
                if (!this.a.a(this.c + 1, d(), this.b)) {
                    d().b(null, 1);
                }
            }
        }

        public a(aw awVar, Consumer<e> consumer, ak producerContext, int producerIndex) {
            this.a = awVar;
            super(consumer);
            this.b = producerContext;
            this.c = producerIndex;
        }

        protected final void a(Throwable t) {
            if (!this.a.a(this.c + 1, d(), this.b)) {
                d().b(t);
            }
        }
    }

    public aw(ax<e>... thumbnailProducers) {
        this.a = (ax[]) h.a((Object) thumbnailProducers);
        h.a(0, this.a.length);
    }

    public final void a(Consumer<e> consumer, ak context) {
        if (context.a().f() == null) {
            consumer.b(null, 1);
        } else if (!a(0, consumer, context)) {
            consumer.b(null, 1);
        }
    }

    private boolean a(int startIndex, Consumer<e> consumer, ak context) {
        int producerIndex;
        com.facebook.imagepipeline.common.e f = context.a().f();
        while (startIndex < this.a.length) {
            if (this.a[startIndex].a(f)) {
                producerIndex = startIndex;
                break;
            }
            startIndex++;
        }
        producerIndex = -1;
        if (producerIndex == -1) {
            return false;
        }
        this.a[producerIndex].a(new a(this, consumer, context, producerIndex), context);
        return true;
    }
}
