package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.image.c;
import com.facebook.imagepipeline.image.d;

public final class i implements aj<com.facebook.common.f.a<c>> {
    private final aj<com.facebook.common.f.a<c>> a;
    private final int b;
    private final int c;
    private final boolean d;

    private static class a extends m<com.facebook.common.f.a<c>, com.facebook.common.f.a<c>> {
        private final int a;
        private final int b;

        protected final /* synthetic */ void a(Object obj, int i) {
            com.facebook.common.f.a aVar = (com.facebook.common.f.a) obj;
            if (aVar != null && aVar.d()) {
                c cVar = (c) aVar.a();
                if (!(cVar == null || cVar.c() || !(cVar instanceof d))) {
                    Bitmap f = ((d) cVar).f();
                    if (f != null) {
                        int rowBytes = f.getRowBytes() * f.getHeight();
                        if (rowBytes >= this.a && rowBytes <= this.b) {
                            f.prepareToDraw();
                        }
                    }
                }
            }
            d().b(aVar, i);
        }

        a(Consumer<com.facebook.common.f.a<c>> consumer, int minBitmapSizeBytes, int maxBitmapSizeBytes) {
            super(consumer);
            this.a = minBitmapSizeBytes;
            this.b = maxBitmapSizeBytes;
        }
    }

    public i(aj<com.facebook.common.f.a<c>> inputProducer, int minBitmapSizeBytes, int maxBitmapSizeBytes, boolean preparePrefetch) {
        h.a(minBitmapSizeBytes <= maxBitmapSizeBytes);
        this.a = (aj) h.a((Object) inputProducer);
        this.b = minBitmapSizeBytes;
        this.c = maxBitmapSizeBytes;
        this.d = preparePrefetch;
    }

    public final void a(Consumer<com.facebook.common.f.a<c>> consumer, ak producerContext) {
        if (!producerContext.f() || this.d) {
            this.a.a(new a(consumer, this.b, this.c), producerContext);
        } else {
            this.a.a(consumer, producerContext);
        }
    }
}
