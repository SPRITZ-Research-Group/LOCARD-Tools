package com.facebook.imagepipeline.producers;

import com.facebook.common.e.i;
import com.facebook.common.e.k;
import com.facebook.common.internal.h;
import com.facebook.imageformat.b;
import com.facebook.imageformat.c;
import com.facebook.imageformat.d;
import com.facebook.imagepipeline.image.e;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public final class az implements aj<e> {
    private final Executor a;
    private final i b;
    private final aj<e> c;

    private class a extends m<e, e> {
        final /* synthetic */ az a;
        private final ak b;
        private com.facebook.common.i.e c = com.facebook.common.i.e.UNSET;

        protected final /* synthetic */ void a(@Nullable Object obj, int i) {
            obj = (e) obj;
            if (this.c == com.facebook.common.i.e.UNSET && obj != null) {
                com.facebook.common.i.e a;
                h.a(obj);
                c a2 = d.a(obj.c());
                if (b.b(a2)) {
                    com.facebook.imagepipeline.nativecode.c a3 = com.facebook.imagepipeline.nativecode.d.a();
                    if (a3 != null) {
                        a = com.facebook.common.i.e.a(!a3.a());
                    }
                    a = com.facebook.common.i.e.NO;
                } else {
                    if (a2 == c.a) {
                        a = com.facebook.common.i.e.UNSET;
                    }
                    a = com.facebook.common.i.e.NO;
                }
                this.c = a;
            }
            if (this.c != com.facebook.common.i.e.NO) {
                if (!b.a(i)) {
                    return;
                }
                if (this.c == com.facebook.common.i.e.YES && obj != null) {
                    az.a(this.a, obj, d(), this.b);
                    return;
                }
            }
            d().b(obj, i);
        }

        public a(az azVar, Consumer<e> consumer, ak context) {
            this.a = azVar;
            super(consumer);
            this.b = context;
        }
    }

    public az(Executor executor, i pooledByteBufferFactory, aj<e> inputProducer) {
        this.a = (Executor) h.a((Object) executor);
        this.b = (i) h.a((Object) pooledByteBufferFactory);
        this.c = (aj) h.a((Object) inputProducer);
    }

    public final void a(Consumer<e> consumer, ak context) {
        this.c.a(new a(this, consumer, context), context);
    }

    static /* synthetic */ void a(az x0, e x1, Consumer x2, ak x3) {
        h.a((Object) x1);
        final e a = e.a(x1);
        x0.a.execute(new ar<e>(x0, x2, x3.c(), "WebpTranscodeProducer", x3.b()) {
            final /* synthetic */ az c;

            protected final /* synthetic */ void a(Object obj) {
                obj = (e) obj;
                e.d(a);
                super.a(obj);
            }

            protected final /* synthetic */ void b(Object obj) {
                e.d((e) obj);
            }

            protected final /* synthetic */ Object c() throws Exception {
                return d();
            }

            private e d() throws Exception {
                k outputStream = this.c.b.a();
                com.facebook.common.f.a ref;
                try {
                    e eVar = a;
                    c a = d.a(eVar.c());
                    if (a == b.f || a == b.h) {
                        com.facebook.imagepipeline.nativecode.d.a();
                        eVar.a(b.a);
                    } else if (a == b.g || a == b.i) {
                        com.facebook.imagepipeline.nativecode.d.a();
                        eVar.a(b.b);
                    } else {
                        throw new IllegalArgumentException("Wrong image format");
                    }
                    ref = com.facebook.common.f.a.a(outputStream.a());
                    e encodedImage = new e(ref);
                    encodedImage.b(a);
                    com.facebook.common.f.a.c(ref);
                    outputStream.close();
                    return encodedImage;
                } catch (Throwable th) {
                    outputStream.close();
                }
            }

            protected final void a(Exception e) {
                e.d(a);
                super.a(e);
            }

            protected final void b() {
                e.d(a);
                super.b();
            }
        });
    }
}
