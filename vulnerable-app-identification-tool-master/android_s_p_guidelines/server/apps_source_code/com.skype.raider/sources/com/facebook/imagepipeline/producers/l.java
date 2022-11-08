package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.i.f;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.f.e;
import com.facebook.imagepipeline.image.d;
import com.facebook.imagepipeline.image.g;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class l implements aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> {
    private final com.facebook.common.e.a a;
    private final Executor b;
    private final com.facebook.imagepipeline.f.c c;
    private final e d;
    private final aj<com.facebook.imagepipeline.image.e> e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final int i;

    private abstract class c extends m<com.facebook.imagepipeline.image.e, com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> {
        private final String a = "ProgressiveDecoder";
        final /* synthetic */ l b;
        private final ak c;
        private final am d;
        private final com.facebook.imagepipeline.common.b e;
        @GuardedBy("this")
        private boolean f;
        private final u g;

        protected abstract int a(com.facebook.imagepipeline.image.e eVar);

        protected abstract g c();

        public final /* synthetic */ void a(Object obj, int i) {
            com.facebook.imagepipeline.image.e eVar = (com.facebook.imagepipeline.image.e) obj;
            try {
                com.facebook.imagepipeline.l.b.a();
                boolean a = b.a(i);
                if (a && !com.facebook.imagepipeline.image.e.e(eVar)) {
                    c(new com.facebook.common.i.a("Encoded image is not valid."));
                } else if (a(eVar, i)) {
                    boolean a2 = b.a(i, 4);
                    if (a || a2 || this.c.h()) {
                        this.g.b();
                    }
                    com.facebook.imagepipeline.l.b.a();
                } else {
                    com.facebook.imagepipeline.l.b.a();
                }
            } finally {
                com.facebook.imagepipeline.l.b.a();
            }
        }

        public c(final l lVar, Consumer<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> consumer, final ak producerContext, final boolean decodeCancellationEnabled, final int maxBitmapSize) {
            this.b = lVar;
            super(consumer);
            this.c = producerContext;
            this.d = producerContext.c();
            this.e = producerContext.a().i();
            this.f = false;
            this.g = new u(lVar.b, new com.facebook.imagepipeline.producers.u.a(this) {
                final /* synthetic */ c d;

                public final void a(com.facebook.imagepipeline.image.e encodedImage, int status) {
                    if (encodedImage != null) {
                        if (this.d.b.f || !b.a(status, 16)) {
                            com.facebook.imagepipeline.k.b request = producerContext.a();
                            if (this.d.b.g || !f.b(request.b())) {
                                encodedImage.d(p.a(request.g(), request.f(), encodedImage, maxBitmapSize));
                            }
                        }
                        c.a(this.d, encodedImage, status);
                    }
                }
            }, this.e.a);
            this.c.a(new e(this) {
                final /* synthetic */ c c;

                public final void c() {
                    if (this.c.c.h()) {
                        this.c.g.b();
                    }
                }

                public final void a() {
                    if (decodeCancellationEnabled) {
                        this.c.f();
                    }
                }
            });
        }

        protected final void a(float progress) {
            super.a(0.99f * progress);
        }

        public final void a(Throwable t) {
            c(t);
        }

        public final void a() {
            f();
        }

        protected boolean a(com.facebook.imagepipeline.image.e ref, int status) {
            return this.g.a(ref, status);
        }

        private Map<String, String> a(@Nullable com.facebook.imagepipeline.image.c image, long queueTime, g quality, boolean isFinal, String imageFormatName, String encodedImageSize, String requestImageSize, String sampleSize) {
            if (!this.d.b(this.c.b())) {
                return null;
            }
            String queueStr = String.valueOf(queueTime);
            String qualityStr = String.valueOf(quality.b());
            String finalStr = String.valueOf(isFinal);
            Map<String, String> tmpMap;
            if (image instanceof d) {
                Bitmap bitmap = ((d) image).f();
                String sizeStr = bitmap.getWidth() + "x" + bitmap.getHeight();
                tmpMap = new HashMap(8);
                tmpMap.put("bitmapSize", sizeStr);
                tmpMap.put("queueTime", queueStr);
                tmpMap.put("hasGoodQuality", qualityStr);
                tmpMap.put("isFinal", finalStr);
                tmpMap.put("encodedImageSize", encodedImageSize);
                tmpMap.put("imageFormat", imageFormatName);
                tmpMap.put("requestedImageSize", requestImageSize);
                tmpMap.put("sampleSize", sampleSize);
                return com.facebook.common.internal.e.a(tmpMap);
            }
            tmpMap = new HashMap(7);
            tmpMap.put("queueTime", queueStr);
            tmpMap.put("hasGoodQuality", qualityStr);
            tmpMap.put("isFinal", finalStr);
            tmpMap.put("encodedImageSize", encodedImageSize);
            tmpMap.put("imageFormat", imageFormatName);
            tmpMap.put("requestedImageSize", requestImageSize);
            tmpMap.put("sampleSize", sampleSize);
            return com.facebook.common.internal.e.a(tmpMap);
        }

        private synchronized boolean e() {
            return this.f;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(boolean shouldFinish) {
            synchronized (this) {
                if (shouldFinish) {
                    if (!this.f) {
                        d().b(1.0f);
                        this.f = true;
                        this.g.a();
                    }
                }
            }
        }

        private void c(Throwable t) {
            a(true);
            d().b(t);
        }

        private void f() {
            a(true);
            d().b();
        }

        static /* synthetic */ void a(c x0, com.facebook.imagepipeline.image.e x1, int x2) {
            if ((x1.e() == com.facebook.imageformat.b.a || !b.b(x2)) && !x0.e() && com.facebook.imagepipeline.image.e.e(x1)) {
                String a;
                String str;
                com.facebook.imageformat.c e = x1.e();
                if (e != null) {
                    a = e.a();
                } else {
                    a = "unknown";
                }
                String str2 = x1.h() + "x" + x1.i();
                String valueOf = String.valueOf(x1.j());
                boolean a2 = b.a(x2);
                Object obj = (!a2 || b.a(x2, 8)) ? null : 1;
                boolean a3 = b.a(x2, 4);
                com.facebook.imagepipeline.common.e f = x0.c.a().f();
                if (f != null) {
                    str = f.a + "x" + f.b;
                } else {
                    str = "unknown";
                }
                com.facebook.common.f.a a4;
                try {
                    int l;
                    g gVar;
                    long c = x0.g.c();
                    String valueOf2 = String.valueOf(x0.c.a().b());
                    if (obj != null || a3) {
                        l = x1.l();
                    } else {
                        l = x0.a(x1);
                    }
                    if (obj != null || a3) {
                        gVar = com.facebook.imagepipeline.image.f.a;
                    } else {
                        gVar = x0.c();
                    }
                    x0.d.a(x0.c.b(), "DecodeProducer");
                    com.facebook.imagepipeline.image.c cVar = null;
                    try {
                        Closeable a5 = x0.b.c.a(x1, l, gVar, x0.e);
                        if (x1.j() != 1) {
                            x2 |= 16;
                        }
                        x0.d.a(x0.c.b(), "DecodeProducer", x0.a(a5, c, gVar, a2, a, str2, str, valueOf));
                        a4 = com.facebook.common.f.a.a(a5);
                        x0.a(b.a(x2));
                        x0.d().b(a4, x2);
                        com.facebook.common.f.a.c(a4);
                        com.facebook.imagepipeline.image.e.d(x1);
                    } catch (com.facebook.imagepipeline.f.a e2) {
                        com.facebook.imagepipeline.image.e a6 = e2.a();
                        FLog.w("ProgressiveDecoder", "%s, {uri: %s, firstEncodedBytes: %s, length: %d}", e2.getMessage(), valueOf2, a6.m(), Integer.valueOf(a6.l()));
                        throw e2;
                    } catch (Throwable e3) {
                        Throwable th = e3;
                        x0.d.a(x0.c.b(), "DecodeProducer", th, x0.a(cVar, c, gVar, a2, a, str2, str, valueOf));
                        x0.c(th);
                        com.facebook.imagepipeline.image.e.d(x1);
                    }
                } catch (Throwable th2) {
                    com.facebook.imagepipeline.image.e.d(x1);
                }
            }
        }
    }

    private class a extends c {
        final /* synthetic */ l a;

        public a(l lVar, Consumer<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> consumer, ak producerContext, boolean decodeCancellationEnabled, int maxBitmapSize) {
            this.a = lVar;
            super(lVar, consumer, producerContext, decodeCancellationEnabled, maxBitmapSize);
        }

        protected final synchronized boolean a(com.facebook.imagepipeline.image.e encodedImage, int status) {
            boolean z;
            if (b.b(status)) {
                z = false;
            } else {
                z = super.a(encodedImage, status);
            }
            return z;
        }

        protected final int a(com.facebook.imagepipeline.image.e encodedImage) {
            return encodedImage.l();
        }

        protected final g c() {
            return com.facebook.imagepipeline.image.f.a(0, false, false);
        }
    }

    private class b extends c {
        final /* synthetic */ l a;
        private final com.facebook.imagepipeline.f.f c;
        private final e d;
        private int e = 0;

        public b(l lVar, Consumer<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> consumer, ak producerContext, com.facebook.imagepipeline.f.f progressiveJpegParser, e progressiveJpegConfig, boolean decodeCancellationEnabled, int maxBitmapSize) {
            this.a = lVar;
            super(lVar, consumer, producerContext, decodeCancellationEnabled, maxBitmapSize);
            this.c = (com.facebook.imagepipeline.f.f) h.a((Object) progressiveJpegParser);
            this.d = (e) h.a((Object) progressiveJpegConfig);
        }

        protected final synchronized boolean a(com.facebook.imagepipeline.image.e encodedImage, int status) {
            boolean ret;
            ret = super.a(encodedImage, status);
            if ((b.b(status) || b.a(status, 8)) && !b.a(status, 4) && com.facebook.imagepipeline.image.e.e(encodedImage) && encodedImage.e() == com.facebook.imageformat.b.a) {
                if (this.c.a(encodedImage)) {
                    int scanNum = this.c.b();
                    if (scanNum <= this.e) {
                        ret = false;
                    } else if (scanNum >= this.d.a(this.e) || this.c.c()) {
                        this.e = scanNum;
                    } else {
                        ret = false;
                    }
                } else {
                    ret = false;
                }
            }
            return ret;
        }

        protected final int a(com.facebook.imagepipeline.image.e encodedImage) {
            return this.c.a();
        }

        protected final g c() {
            return this.d.b(this.c.b());
        }
    }

    public l(com.facebook.common.e.a byteArrayPool, Executor executor, com.facebook.imagepipeline.f.c imageDecoder, e progressiveJpegConfig, boolean downsampleEnabled, boolean downsampleEnabledForNetwork, boolean decodeCancellationEnabled, aj<com.facebook.imagepipeline.image.e> inputProducer, int maxBitmapSize) {
        this.a = (com.facebook.common.e.a) h.a((Object) byteArrayPool);
        this.b = (Executor) h.a((Object) executor);
        this.c = (com.facebook.imagepipeline.f.c) h.a((Object) imageDecoder);
        this.d = (e) h.a((Object) progressiveJpegConfig);
        this.f = downsampleEnabled;
        this.g = downsampleEnabledForNetwork;
        this.e = (aj) h.a((Object) inputProducer);
        this.h = decodeCancellationEnabled;
        this.i = maxBitmapSize;
    }

    public final void a(Consumer<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> consumer, ak producerContext) {
        try {
            c progressiveDecoder;
            com.facebook.imagepipeline.l.b.a();
            if (f.b(producerContext.a().b())) {
                progressiveDecoder = new b(this, consumer, producerContext, new com.facebook.imagepipeline.f.f(this.a), this.d, this.h, this.i);
            } else {
                progressiveDecoder = new a(this, consumer, producerContext, this.h, this.i);
            }
            this.e.a(progressiveDecoder, producerContext);
        } finally {
            com.facebook.imagepipeline.l.b.a();
        }
    }
}
