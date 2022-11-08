package com.facebook.imagepipeline.producers;

import com.facebook.common.e.i;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.transcoder.b;
import com.facebook.imagepipeline.transcoder.c;
import com.facebook.imagepipeline.transcoder.d;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public final class ap implements aj<e> {
    private final Executor a;
    private final i b;
    private final aj<e> c;
    private final boolean d;
    private final c e;

    private class a extends m<e, e> {
        final /* synthetic */ ap a;
        private final boolean b;
        private final c c;
        private final ak d;
        private boolean e = false;
        private final u f;

        static /* synthetic */ void a(a x0, e x1, int x2, b x3) {
            Map a;
            Throwable e;
            x0.d.c().a(x0.d.b(), "ResizeAndRotateProducer");
            com.facebook.imagepipeline.k.b a2 = x0.d.a();
            OutputStream a3 = x0.a.b.a();
            try {
                com.facebook.imagepipeline.transcoder.a transcode = x3.transcode(x1, a3, a2.g(), a2.f(), null, Integer.valueOf(85));
                if (transcode.a() == 2) {
                    throw new RuntimeException("Error while transcoding the image");
                }
                com.facebook.imagepipeline.common.e f = a2.f();
                String identifier = x3.getIdentifier();
                if (x0.d.c().b(x0.d.b())) {
                    Object obj;
                    String str = x1.h() + "x" + x1.i();
                    if (f != null) {
                        obj = f.a + "x" + f.b;
                    } else {
                        obj = "Unspecified";
                    }
                    Map hashMap = new HashMap();
                    hashMap.put("Image format", String.valueOf(x1.e()));
                    hashMap.put("Original size", str);
                    hashMap.put("Requested size", obj);
                    hashMap.put("queueTime", String.valueOf(x0.f.c()));
                    hashMap.put("Transcoder id", identifier);
                    hashMap.put("Transcoding result", String.valueOf(transcode));
                    a = com.facebook.common.internal.e.a(hashMap);
                } else {
                    a = null;
                }
                try {
                    com.facebook.common.f.a a4 = com.facebook.common.f.a.a(a3.a());
                    e eVar;
                    try {
                        eVar = new e(a4);
                        eVar.a(com.facebook.imageformat.b.a);
                        eVar.n();
                        x0.d.c().a(x0.d.b(), "ResizeAndRotateProducer", a);
                        if (transcode.a() != 1) {
                            x2 |= 16;
                        }
                        x0.d().b(eVar, x2);
                        e.d(eVar);
                        com.facebook.common.f.a.c(a4);
                        a3.close();
                        return;
                    } catch (Throwable th) {
                        com.facebook.common.f.a.c(a4);
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                a = null;
            }
            try {
                x0.d.c().a(x0.d.b(), "ResizeAndRotateProducer", e, a);
                if (b.a(x2)) {
                    x0.d().b(e);
                }
                a3.close();
            } catch (Throwable th2) {
                a3.close();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected final /* synthetic */ void a(@Nullable Object obj, int i) {
            e eVar = (e) obj;
            if (!this.e) {
                boolean a = b.a(i);
                if (eVar != null) {
                    com.facebook.common.i.e eVar2;
                    com.facebook.imagepipeline.k.b a2 = this.d.a();
                    b bVar = (b) h.a(this.c.a(eVar.e(), this.b));
                    if (eVar == null || eVar.e() == com.facebook.imageformat.c.a) {
                        eVar2 = com.facebook.common.i.e.UNSET;
                    } else if (bVar.canTranscode(eVar.e())) {
                        int i2;
                        boolean z;
                        RotationOptions g = a2.g();
                        if (!g.f()) {
                            if (d.a(g, eVar) == 0) {
                                boolean z2;
                                if (!g.d() || g.f()) {
                                    eVar.d();
                                    z2 = false;
                                } else {
                                    z2 = d.a.contains(Integer.valueOf(eVar.g()));
                                }
                            }
                            i2 = 1;
                            z = i2 == 0 || bVar.canResize(eVar, a2.g(), a2.f());
                            eVar2 = com.facebook.common.i.e.a(z);
                        }
                        i2 = 0;
                        if (i2 == 0) {
                        }
                        eVar2 = com.facebook.common.i.e.a(z);
                    } else {
                        eVar2 = com.facebook.common.i.e.NO;
                    }
                    if (!a && eVar2 == com.facebook.common.i.e.UNSET) {
                        return;
                    }
                    if (eVar2 != com.facebook.common.i.e.YES) {
                        if (!(this.d.a().g().f() || eVar.f() == 0 || eVar.f() == -1)) {
                            e a3 = e.a(eVar);
                            eVar.close();
                            a3.c(0);
                            obj = a3;
                        }
                        d().b(obj, i);
                    } else if (!this.f.a(eVar, i)) {
                    } else {
                        if (a || this.d.h()) {
                            this.f.b();
                        }
                    }
                } else if (a) {
                    d().b(null, 1);
                }
            }
        }

        a(final ap apVar, final Consumer<e> consumer, ak producerContext, boolean isResizingEnabled, c imageTranscoderFactory) {
            this.a = apVar;
            super(consumer);
            this.d = producerContext;
            this.b = isResizingEnabled;
            this.c = imageTranscoderFactory;
            this.f = new u(apVar.a, new com.facebook.imagepipeline.producers.u.a(this) {
                final /* synthetic */ a b;

                public final void a(e encodedImage, int status) {
                    a.a(this.b, encodedImage, status, (b) h.a(this.b.c.a(encodedImage.e(), this.b.b)));
                }
            }, 100);
            this.d.a(new e(this) {
                final /* synthetic */ a c;

                public final void c() {
                    if (this.c.d.h()) {
                        this.c.f.b();
                    }
                }

                public final void a() {
                    this.c.f.a();
                    this.c.e = true;
                    consumer.b();
                }
            });
        }
    }

    public ap(Executor executor, i pooledByteBufferFactory, aj<e> inputProducer, boolean isResizingEnabled, c imageTranscoderFactory) {
        this.a = (Executor) h.a((Object) executor);
        this.b = (i) h.a((Object) pooledByteBufferFactory);
        this.c = (aj) h.a((Object) inputProducer);
        this.e = (c) h.a((Object) imageTranscoderFactory);
        this.d = isResizingEnabled;
    }

    public final void a(Consumer<e> consumer, ak context) {
        this.c.a(new a(this, consumer, context, this.d, this.e), context);
    }
}
