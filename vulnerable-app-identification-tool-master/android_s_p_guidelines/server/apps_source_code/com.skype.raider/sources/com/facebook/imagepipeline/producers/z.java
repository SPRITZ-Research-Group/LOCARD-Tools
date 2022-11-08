package com.facebook.imagepipeline.producers;

import com.facebook.common.e.h;
import com.facebook.common.e.i;
import com.facebook.common.f.a;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public abstract class z implements aj<e> {
    private final Executor a;
    private final i b;

    protected abstract e a(b bVar) throws IOException;

    protected abstract String a();

    protected z(Executor executor, i pooledByteBufferFactory) {
        this.a = executor;
        this.b = pooledByteBufferFactory;
    }

    public final void a(Consumer<e> consumer, ak producerContext) {
        am listener = producerContext.c();
        String requestId = producerContext.b();
        final b imageRequest = producerContext.a();
        final am amVar = listener;
        final String str = requestId;
        final ar cancellableProducerRunnable = new ar<e>(this, consumer, listener, a(), requestId) {
            final /* synthetic */ z e;

            protected final /* synthetic */ void b(Object obj) {
                e.d((e) obj);
            }

            protected final /* synthetic */ Object c() throws Exception {
                Object a = this.e.a(imageRequest);
                if (a == null) {
                    amVar.a(str, this.e.a(), false);
                    return null;
                }
                a.n();
                amVar.a(str, this.e.a(), true);
                return a;
            }
        };
        producerContext.a(new e(this) {
            final /* synthetic */ z b;

            public final void a() {
                cancellableProducerRunnable.a();
            }
        });
        this.a.execute(cancellableProducerRunnable);
    }

    protected final e a(InputStream inputStream, int length) throws IOException {
        a ref;
        a<h> ref2 = null;
        if (length <= 0) {
            try {
                ref = a.a(this.b.a(inputStream));
            } catch (Throwable th) {
                com.facebook.common.internal.b.a(inputStream);
                a.c(ref2);
            }
        } else {
            ref = a.a(this.b.a(inputStream, length));
        }
        e eVar = new e(ref);
        com.facebook.common.internal.b.a(inputStream);
        a.c(ref);
        return eVar;
    }

    protected final e b(InputStream inputStream, int length) throws IOException {
        return a(inputStream, length);
    }
}
