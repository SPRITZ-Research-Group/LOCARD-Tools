package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.e;

public final class a implements aj<e> {
    private final aj<e> a;

    private static class a extends m<e, e> {
        /* synthetic */ a(Consumer x0, byte b) {
            this(x0);
        }

        protected final /* synthetic */ void a(Object obj, int i) {
            e eVar = (e) obj;
            if (eVar == null) {
                d().b(null, i);
                return;
            }
            if (!e.c(eVar)) {
                eVar.n();
            }
            d().b(eVar, i);
        }

        private a(Consumer<e> consumer) {
            super(consumer);
        }
    }

    public a(aj<e> inputProducer) {
        this.a = inputProducer;
    }

    public final void a(Consumer<e> consumer, ak context) {
        this.a.a(new a(consumer, (byte) 0), context);
    }
}
