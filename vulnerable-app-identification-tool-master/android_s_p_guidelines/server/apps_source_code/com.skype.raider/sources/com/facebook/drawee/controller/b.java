package com.facebook.drawee.controller;

import android.content.Context;
import android.graphics.drawable.Animatable;
import com.facebook.common.internal.h;
import com.facebook.common.internal.j;
import com.facebook.datasource.d;
import com.facebook.datasource.f;
import com.facebook.datasource.g;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.c;
import com.facebook.infer.annotation.ReturnsOwnership;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

public abstract class b<BUILDER extends b<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements c {
    private static final ControllerListener<Object> a = new BaseControllerListener<Object>() {
        public final void onFinalImageSet(String id, @Nullable Object info, @Nullable Animatable anim) {
            if (anim != null) {
                anim.start();
            }
        }
    };
    private static final NullPointerException b = new NullPointerException("No image request was specified!");
    private static final AtomicLong r = new AtomicLong();
    private final Context c;
    private final Set<ControllerListener> d;
    @Nullable
    private Object e;
    @Nullable
    private REQUEST f;
    @Nullable
    private REQUEST g;
    @Nullable
    private REQUEST[] h;
    private boolean i;
    @Nullable
    private j<com.facebook.datasource.c<IMAGE>> j;
    @Nullable
    private ControllerListener<? super INFO> k;
    @Nullable
    private c l;
    private boolean m;
    private boolean n;
    private boolean o;
    private String p;
    @Nullable
    private DraweeController q;

    public enum a {
        FULL_FETCH,
        DISK_CACHE,
        BITMAP_MEMORY_CACHE
    }

    protected abstract com.facebook.datasource.c<IMAGE> a(DraweeController draweeController, REQUEST request, Object obj, a aVar);

    @ReturnsOwnership
    protected abstract a a();

    public final /* synthetic */ DraweeController j() {
        return h();
    }

    protected b(Context context, Set<ControllerListener> boundControllerListeners) {
        this.c = context;
        this.d = boundControllerListeners;
        k();
    }

    private void k() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = true;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.q = null;
        this.p = null;
    }

    public final BUILDER b() {
        k();
        return this;
    }

    public final BUILDER a(Object callerContext) {
        this.e = callerContext;
        return this;
    }

    @Nullable
    public final Object c() {
        return this.e;
    }

    public final BUILDER b(REQUEST imageRequest) {
        this.f = imageRequest;
        return this;
    }

    @Nullable
    public final REQUEST d() {
        return this.f;
    }

    public final BUILDER c(REQUEST lowResImageRequest) {
        this.g = lowResImageRequest;
        return this;
    }

    public final BUILDER a(REQUEST[] firstAvailableImageRequests) {
        h.a(true, (Object) "No requests specified!");
        this.h = firstAvailableImageRequests;
        this.i = true;
        return this;
    }

    public final BUILDER e() {
        this.m = true;
        return this;
    }

    public final BUILDER f() {
        this.n = true;
        return this;
    }

    public final BUILDER a(ControllerListener<? super INFO> controllerListener) {
        this.k = controllerListener;
        return this;
    }

    public final BUILDER a(@Nullable DraweeController oldController) {
        this.q = oldController;
        return this;
    }

    @Nullable
    public final DraweeController g() {
        return this.q;
    }

    public final a h() {
        boolean z = false;
        boolean z2 = this.h == null || this.f == null;
        h.b(z2, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (this.j == null || (this.h == null && this.f == null && this.g == null)) {
            z = true;
        }
        h.b(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
        if (this.f == null && this.h == null && this.g != null) {
            this.f = this.g;
            this.g = null;
        }
        com.facebook.imagepipeline.l.b.a();
        a a = a();
        a.b(this.o);
        a.a(this.p);
        a.a(this.l);
        if (this.m) {
            a.f().a(this.m);
            if (a.g() == null) {
                a.a(new com.facebook.drawee.e.a(this.c));
            }
        }
        if (this.d != null) {
            for (ControllerListener a2 : this.d) {
                a.a(a2);
            }
        }
        if (this.k != null) {
            a.a(this.k);
        }
        if (this.n) {
            a.a(a);
        }
        com.facebook.imagepipeline.l.b.a();
        return a;
    }

    protected static String i() {
        return String.valueOf(r.getAndIncrement());
    }

    protected final j<com.facebook.datasource.c<IMAGE>> a(DraweeController controller, String controllerId) {
        int i = 0;
        if (this.j != null) {
            return this.j;
        }
        j<com.facebook.datasource.c<IMAGE>> supplier = null;
        if (this.f != null) {
            supplier = a(controller, controllerId, this.f);
        } else if (this.h != null) {
            Object[] objArr = this.h;
            boolean z = this.i;
            List arrayList = new ArrayList(objArr.length * 2);
            if (z) {
                for (Object a : objArr) {
                    arrayList.add(a(controller, controllerId, a, a.BITMAP_MEMORY_CACHE));
                }
            }
            while (i < objArr.length) {
                arrayList.add(a(controller, controllerId, objArr[i]));
                i++;
            }
            supplier = f.a(arrayList);
        }
        if (!(supplier == null || this.g == null)) {
            List suppliers = new ArrayList(2);
            suppliers.add(supplier);
            suppliers.add(a(controller, controllerId, this.g));
            supplier = g.a(suppliers);
        }
        if (supplier == null) {
            return d.b(b);
        }
        return supplier;
    }

    private j<com.facebook.datasource.c<IMAGE>> a(DraweeController controller, String controllerId, REQUEST imageRequest) {
        return a(controller, controllerId, (Object) imageRequest, a.FULL_FETCH);
    }

    private j<com.facebook.datasource.c<IMAGE>> a(DraweeController controller, String controllerId, REQUEST imageRequest, a cacheLevel) {
        final Object callerContext = this.e;
        final DraweeController draweeController = controller;
        final String str = controllerId;
        final REQUEST request = imageRequest;
        final a aVar = cacheLevel;
        return new j<com.facebook.datasource.c<IMAGE>>(this) {
            final /* synthetic */ b f;

            public final String toString() {
                return com.facebook.common.internal.g.a(this).a("request", request.toString()).toString();
            }

            public final /* bridge */ /* synthetic */ Object a() {
                return this.f.a(draweeController, request, callerContext, aVar);
            }
        };
    }
}
