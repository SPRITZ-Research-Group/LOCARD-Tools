package com.facebook.imagepipeline.e;

import com.facebook.common.internal.h;
import com.facebook.imagepipeline.h.c;
import com.facebook.imagepipeline.l.b;
import com.facebook.imagepipeline.producers.aj;
import com.facebook.imagepipeline.producers.aq;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class a<T> extends com.facebook.datasource.a<T> {
    private final aq a;
    private final c b;

    protected a(aj<T> producer, aq settableProducerContext, c requestListener) {
        b.a();
        this.a = settableProducerContext;
        this.b = requestListener;
        b.a();
        this.b.a(settableProducerContext.a(), this.a.d(), this.a.b(), this.a.f());
        b.a();
        b.a();
        producer.a(new com.facebook.imagepipeline.producers.b<T>(this) {
            final /* synthetic */ a a;

            {
                this.a = this$0;
            }

            protected final void a(@Nullable T newResult, int status) {
                this.a.a((Object) newResult, status);
            }

            protected final void a(Throwable throwable) {
                a.a(this.a, throwable);
            }

            protected final void a() {
                this.a.i();
            }

            protected final void a(float progress) {
                this.a.a(progress);
            }
        }, settableProducerContext);
        b.a();
        b.a();
    }

    protected void a(@Nullable T result, int status) {
        boolean isLast = com.facebook.imagepipeline.producers.b.a(status);
        if (super.a((Object) result, isLast) && isLast) {
            this.b.a(this.a.a(), this.a.b(), this.a.f());
        }
    }

    private synchronized void i() {
        h.b(a());
    }

    public final boolean h() {
        if (!super.h()) {
            return false;
        }
        if (!super.b()) {
            this.b.a_(this.a.b());
            this.a.i();
        }
        return true;
    }

    static /* synthetic */ void a(a x0, Throwable x1) {
        if (super.a(x1)) {
            x0.b.a(x0.a.a(), x0.a.b(), x1, x0.a.f());
        }
    }
}
