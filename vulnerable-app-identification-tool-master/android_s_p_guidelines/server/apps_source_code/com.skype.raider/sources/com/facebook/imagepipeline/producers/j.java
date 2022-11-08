package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;

public final class j implements aj<e> {
    private final aj<e> a;
    private final aj<e> b;

    private class a extends m<e, e> {
        final /* synthetic */ j a;
        private ak b;

        /* synthetic */ a(j x0, Consumer x1, ak x2, byte b) {
            this(x0, x1, x2);
        }

        protected final /* synthetic */ void a(Object obj, int i) {
            e eVar = (e) obj;
            b a = this.b.a();
            boolean a2 = b.a(i);
            boolean a3 = ay.a(eVar, a.f());
            if (eVar != null && (a3 || a.k())) {
                if (a2 && a3) {
                    d().b(eVar, i);
                } else {
                    d().b(eVar, i & -2);
                }
            }
            if (a2 && !a3) {
                e.d(eVar);
                this.a.b.a(d(), this.b);
            }
        }

        private a(j jVar, Consumer<e> consumer, ak producerContext) {
            this.a = jVar;
            super(consumer);
            this.b = producerContext;
        }

        protected final void a(Throwable t) {
            this.a.b.a(d(), this.b);
        }
    }

    public j(aj<e> inputProducer1, aj<e> inputProducer2) {
        this.a = inputProducer1;
        this.b = inputProducer2;
    }

    public final void a(Consumer<e> consumer, ak context) {
        this.a.a(new a(this, consumer, context, (byte) 0), context);
    }
}
