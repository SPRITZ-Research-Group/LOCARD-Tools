package com.facebook.fresco.animation.factory;

import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.cache.a.c;
import com.facebook.common.internal.j;
import com.facebook.fresco.animation.bitmap.BitmapAnimationBackend;
import com.facebook.fresco.animation.bitmap.b.d;
import com.facebook.imagepipeline.a.a.e;
import com.facebook.imagepipeline.a.c.b;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.d.h;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public final class a implements com.facebook.imagepipeline.g.a {
    private final b a;
    private final ScheduledExecutorService b;
    private final ExecutorService c;
    private final com.facebook.common.time.b d;
    private final f e;
    private final h<c, com.facebook.imagepipeline.image.c> f;
    private final j<Integer> g;
    private final j<Integer> h;

    public static class a implements c {
        private final String a;

        public a(int imageId) {
            this.a = "anim://" + imageId;
        }

        public final boolean a(Uri uri) {
            return uri.toString().startsWith(this.a);
        }

        public final String a() {
            return this.a;
        }
    }

    public final /* synthetic */ Drawable b(com.facebook.imagepipeline.image.c cVar) {
        com.facebook.fresco.animation.bitmap.a aVar;
        com.facebook.fresco.animation.bitmap.b.a dVar;
        com.facebook.fresco.animation.bitmap.b.b bVar = null;
        e f = ((com.facebook.imagepipeline.image.a) cVar).f();
        com.facebook.imagepipeline.a.a.c a = f.a();
        com.facebook.imagepipeline.a.a.a a2 = this.a.a(f, new Rect(0, 0, a.getWidth(), a.getHeight()));
        switch (((Integer) this.g.a()).intValue()) {
            case 1:
                aVar = new com.facebook.fresco.animation.bitmap.a.a(a(f), true);
                break;
            case 2:
                aVar = new com.facebook.fresco.animation.bitmap.a.a(a(f), false);
                break;
            case 3:
                aVar = new com.facebook.fresco.animation.bitmap.a.b();
                break;
            default:
                aVar = new com.facebook.fresco.animation.bitmap.a.c();
                break;
        }
        com.facebook.fresco.animation.bitmap.b bVar2 = new com.facebook.fresco.animation.bitmap.c.b(aVar, a2);
        int intValue = ((Integer) this.h.a()).intValue();
        if (intValue > 0) {
            dVar = new d(intValue);
            bVar = new com.facebook.fresco.animation.bitmap.b.c(this.e, bVar2, Config.ARGB_8888, this.c);
        } else {
            dVar = null;
        }
        return new com.facebook.fresco.animation.b.a(com.facebook.fresco.animation.a.c.a(new BitmapAnimationBackend(this.e, aVar, new com.facebook.fresco.animation.bitmap.c.a(a2), bVar2, dVar, bVar), this.d, this.b));
    }

    public a(b animatedDrawableBackendProvider, ScheduledExecutorService scheduledExecutorServiceForUiThread, ExecutorService executorServiceForFramePreparing, com.facebook.common.time.b monotonicClock, f platformBitmapFactory, h<c, com.facebook.imagepipeline.image.c> backingCache, j<Integer> cachingStrategySupplier, j<Integer> numberOfFramesToPrepareSupplier) {
        this.a = animatedDrawableBackendProvider;
        this.b = scheduledExecutorServiceForUiThread;
        this.c = executorServiceForFramePreparing;
        this.d = monotonicClock;
        this.e = platformBitmapFactory;
        this.f = backingCache;
        this.g = cachingStrategySupplier;
        this.h = numberOfFramesToPrepareSupplier;
    }

    public final boolean a(com.facebook.imagepipeline.image.c image) {
        return image instanceof com.facebook.imagepipeline.image.a;
    }

    private com.facebook.imagepipeline.a.c.c a(e animatedImageResult) {
        return new com.facebook.imagepipeline.a.c.c(new a(animatedImageResult.hashCode()), this.f);
    }
}
