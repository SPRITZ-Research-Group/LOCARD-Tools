package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.d;
import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.common.internal.j;
import com.facebook.common.logging.FLog;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.drawee.backends.pipeline.info.e;
import com.facebook.drawee.backends.pipeline.info.f;
import com.facebook.drawee.c.q;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.a;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.c;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class b extends a<com.facebook.common.f.a<c>, ImageInfo> {
    private static final Class<?> a = b.class;
    private final Resources b;
    private final com.facebook.imagepipeline.g.a c;
    @Nullable
    private final d<com.facebook.imagepipeline.g.a> d;
    @Nullable
    private final p<com.facebook.cache.a.c, c> e;
    private com.facebook.cache.a.c f;
    private j<com.facebook.datasource.c<com.facebook.common.f.a<c>>> g;
    private boolean h;
    @Nullable
    private d<com.facebook.imagepipeline.g.a> i;
    @Nullable
    private f j;
    @GuardedBy("this")
    @Nullable
    private Set<com.facebook.imagepipeline.h.c> k;
    @GuardedBy("this")
    @Nullable
    private com.facebook.drawee.backends.pipeline.info.b l;
    private com.facebook.drawee.backends.pipeline.a.a m;

    protected final /* synthetic */ void a(@Nullable Object obj) {
        com.facebook.common.f.a.c((com.facebook.common.f.a) obj);
    }

    protected final /* synthetic */ int b(@Nullable Object obj) {
        com.facebook.common.f.a aVar = (com.facebook.common.f.a) obj;
        return aVar != null ? aVar.e() : 0;
    }

    protected final /* synthetic */ Object c() {
        return m();
    }

    protected final /* synthetic */ Object c(Object obj) {
        com.facebook.common.f.a aVar = (com.facebook.common.f.a) obj;
        h.b(com.facebook.common.f.a.a(aVar));
        return (ImageInfo) aVar.a();
    }

    protected final /* synthetic */ Drawable d(Object obj) {
        return a((com.facebook.common.f.a) obj);
    }

    public b(Resources resources, com.facebook.drawee.a.a deferredReleaser, com.facebook.imagepipeline.g.a animatedDrawableFactory, Executor uiThreadExecutor, @Nullable p<com.facebook.cache.a.c, c> memoryCache, @Nullable d<com.facebook.imagepipeline.g.a> globalDrawableFactories) {
        super(deferredReleaser, uiThreadExecutor);
        this.b = resources;
        this.c = new a(resources, animatedDrawableFactory);
        this.d = globalDrawableFactories;
        this.e = memoryCache;
    }

    public final void a(j<com.facebook.datasource.c<com.facebook.common.f.a<c>>> dataSourceSupplier, String id, com.facebook.cache.a.c cacheKey, Object callerContext, @Nullable d<com.facebook.imagepipeline.g.a> customDrawableFactories, @Nullable com.facebook.drawee.backends.pipeline.info.b imageOriginListener) {
        com.facebook.imagepipeline.l.b.a();
        super.b(id, callerContext);
        this.g = dataSourceSupplier;
        a(null);
        this.f = cacheKey;
        this.i = customDrawableFactories;
        synchronized (this) {
            this.l = null;
        }
        a(null);
        a(imageOriginListener);
        com.facebook.imagepipeline.l.b.a();
    }

    protected final synchronized void a(@Nullable e imagePerfDataListener) {
        if (this.j != null) {
            this.j.a();
        }
        if (imagePerfDataListener != null) {
            if (this.j == null) {
                this.j = new f(AwakeTimeSinceBootClock.get(), this);
            }
            this.j.a(imagePerfDataListener);
            this.j.a(true);
        }
    }

    public final void a(boolean drawDebugOverlay) {
        this.h = drawDebugOverlay;
    }

    public final synchronized void a(com.facebook.imagepipeline.h.c requestListener) {
        if (this.k == null) {
            this.k = new HashSet();
        }
        this.k.add(requestListener);
    }

    public final synchronized void b(com.facebook.imagepipeline.h.c requestListener) {
        if (this.k != null) {
            this.k.remove(requestListener);
        }
    }

    public final synchronized void a(com.facebook.drawee.backends.pipeline.info.b imageOriginListener) {
        if (this.l instanceof com.facebook.drawee.backends.pipeline.info.a) {
            ((com.facebook.drawee.backends.pipeline.info.a) this.l).a(imageOriginListener);
        } else if (this.l != null) {
            this.l = new com.facebook.drawee.backends.pipeline.info.a(this.l, imageOriginListener);
        } else {
            this.l = imageOriginListener;
        }
    }

    public final synchronized void b(com.facebook.drawee.backends.pipeline.info.b imageOriginListener) {
        if (this.l instanceof com.facebook.drawee.backends.pipeline.info.a) {
            ((com.facebook.drawee.backends.pipeline.info.a) this.l).b(imageOriginListener);
        } else if (this.l != null) {
            this.l = new com.facebook.drawee.backends.pipeline.info.a(this.l, imageOriginListener);
        } else {
            this.l = imageOriginListener;
        }
    }

    @Nullable
    public final synchronized com.facebook.imagepipeline.h.c a() {
        com.facebook.imagepipeline.h.c requestListener;
        com.facebook.imagepipeline.h.c imageOriginRequestListener = null;
        if (this.l != null) {
            imageOriginRequestListener = new com.facebook.drawee.backends.pipeline.info.c(e(), this.l);
        }
        if (this.k != null) {
            requestListener = new com.facebook.imagepipeline.h.b(this.k);
            if (imageOriginRequestListener != null) {
                requestListener.a(imageOriginRequestListener);
            }
        } else {
            requestListener = imageOriginRequestListener;
        }
        return requestListener;
    }

    protected final com.facebook.datasource.c<com.facebook.common.f.a<c>> b() {
        com.facebook.imagepipeline.l.b.a();
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x: getDataSource", Integer.valueOf(System.identityHashCode(this)));
        }
        com.facebook.datasource.c<com.facebook.common.f.a<c>> result = (com.facebook.datasource.c) this.g.a();
        com.facebook.imagepipeline.l.b.a();
        return result;
    }

    private Drawable a(com.facebook.common.f.a<c> image) {
        try {
            com.facebook.imagepipeline.l.b.a();
            h.b(com.facebook.common.f.a.a((com.facebook.common.f.a) image));
            c closeableImage = (c) image.a();
            a(closeableImage);
            Drawable drawable = a(this.i, closeableImage);
            if (drawable != null) {
                return drawable;
            }
            drawable = a(this.d, closeableImage);
            if (drawable != null) {
                com.facebook.imagepipeline.l.b.a();
                return drawable;
            }
            drawable = this.c.b(closeableImage);
            if (drawable != null) {
                com.facebook.imagepipeline.l.b.a();
                return drawable;
            }
            throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
        } finally {
            com.facebook.imagepipeline.l.b.a();
        }
    }

    private static Drawable a(@Nullable d<com.facebook.imagepipeline.g.a> drawableFactories, c closeableImage) {
        if (drawableFactories == null) {
            return null;
        }
        Iterator it = drawableFactories.iterator();
        while (it.hasNext()) {
            com.facebook.imagepipeline.g.a factory = (com.facebook.imagepipeline.g.a) it.next();
            if (factory.a(closeableImage)) {
                Drawable drawable = factory.b(closeableImage);
                if (drawable != null) {
                    return drawable;
                }
            }
        }
        return null;
    }

    public final void a(@Nullable com.facebook.drawee.interfaces.a hierarchy) {
        super.a(hierarchy);
        a(null);
    }

    private void a(@Nullable c image) {
        if (this.h) {
            if (i() == null) {
                com.facebook.drawee.b.a controllerOverlay = new com.facebook.drawee.b.a();
                com.facebook.drawee.b.a.a overlayImageLoadListener = new com.facebook.drawee.b.a.a(controllerOverlay);
                this.m = new com.facebook.drawee.backends.pipeline.a.a();
                a((ControllerListener) overlayImageLoadListener);
                b((Drawable) controllerOverlay);
            }
            if (this.l == null) {
                a(this.m);
            }
            if (i() instanceof com.facebook.drawee.b.a) {
                com.facebook.drawee.b.a debugOverlay = (com.facebook.drawee.b.a) i();
                debugOverlay.a(e());
                com.facebook.drawee.interfaces.a draweeHierarchy = h();
                com.facebook.drawee.c.q.b scaleType = null;
                if (draweeHierarchy != null) {
                    com.facebook.drawee.c.p scaleTypeDrawable = q.a(draweeHierarchy.a());
                    scaleType = scaleTypeDrawable != null ? scaleTypeDrawable.b() : null;
                }
                debugOverlay.a(scaleType);
                debugOverlay.b(this.m.a());
                if (image != null) {
                    debugOverlay.a(image.a(), image.b());
                    debugOverlay.a(image.d());
                    return;
                }
                debugOverlay.a();
            }
        }
    }

    protected final void a(@Nullable Drawable drawable) {
        if (drawable instanceof com.facebook.drawable.a.a) {
            ((com.facebook.drawable.a.a) drawable).a();
        }
    }

    private com.facebook.common.f.a<c> m() {
        com.facebook.imagepipeline.l.b.a();
        try {
            if (this.e == null || this.f == null) {
                com.facebook.imagepipeline.l.b.a();
                return null;
            }
            com.facebook.common.f.a<c> closeableImage = this.e.a(this.f);
            if (closeableImage == null || ((c) closeableImage.a()).g().c()) {
                com.facebook.imagepipeline.l.b.a();
                return closeableImage;
            }
            closeableImage.close();
            com.facebook.imagepipeline.l.b.a();
            return null;
        } catch (Throwable th) {
            com.facebook.imagepipeline.l.b.a();
        }
    }

    public String toString() {
        return g.a(this).a("super", super.toString()).a("dataSourceSupplier", this.g).toString();
    }
}
