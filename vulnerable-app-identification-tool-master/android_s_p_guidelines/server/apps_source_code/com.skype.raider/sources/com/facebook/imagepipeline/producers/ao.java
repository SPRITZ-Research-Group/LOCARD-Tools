package com.facebook.imagepipeline.producers;

import com.facebook.common.e.h;
import com.facebook.imagepipeline.image.e;

public final class ao implements aj<com.facebook.common.f.a<h>> {
    private final aj<e> a;

    private class a extends m<e, com.facebook.common.f.a<h>> {
        final /* synthetic */ ao a;

        /* synthetic */ a(ao x0, Consumer x1, byte b) {
            this(x0, x1);
        }

        protected final /* synthetic */ void a(Object obj, int i) {
            e eVar = (e) obj;
            com.facebook.common.f.a aVar = null;
            try {
                if (e.e(eVar)) {
                    aVar = eVar.b();
                }
                d().b(aVar, i);
            } finally {
                com.facebook.common.f.a.c(aVar);
            }
        }

        private a(ao aoVar, Consumer<com.facebook.common.f.a<h>> consumer) {
            this.a = aoVar;
            super(consumer);
        }
    }

    public ao(aj<e> inputProducer) {
        this.a = inputProducer;
    }

    public final void a(Consumer<com.facebook.common.f.a<h>> consumer, ak context) {
        this.a.a(new a(this, consumer, (byte) 0), context);
    }
}
