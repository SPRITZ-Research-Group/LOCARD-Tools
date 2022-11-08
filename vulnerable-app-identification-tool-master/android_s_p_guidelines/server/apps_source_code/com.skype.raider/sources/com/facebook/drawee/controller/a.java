package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.a.b;
import com.facebook.drawee.a.c;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.infer.annotation.ReturnsOwnership;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class a<T, INFO> implements com.facebook.drawee.a.a.a, com.facebook.drawee.e.a.a, DraweeController {
    private static final Class<?> a = a.class;
    private final b b = b.a();
    private final com.facebook.drawee.a.a c;
    private final Executor d;
    @Nullable
    private c e;
    @Nullable
    private com.facebook.drawee.e.a f;
    @Nullable
    private ControllerListener<INFO> g;
    @Nullable
    private c h;
    @Nullable
    private com.facebook.drawee.interfaces.b i;
    @Nullable
    private Drawable j;
    private String k;
    private Object l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    @Nullable
    private String r;
    @Nullable
    private com.facebook.datasource.c<T> s;
    @Nullable
    private T t;
    @Nullable
    private Drawable u;
    private boolean v = true;

    private static class a<INFO> extends d<INFO> {
        private a() {
        }

        public static <INFO> a<INFO> a(ControllerListener<? super INFO> listener1, ControllerListener<? super INFO> listener2) {
            com.facebook.imagepipeline.l.b.a();
            a<INFO> forwarder = new a();
            forwarder.a(listener1);
            forwarder.a(listener2);
            com.facebook.imagepipeline.l.b.a();
            return forwarder;
        }
    }

    protected abstract void a(@Nullable Drawable drawable);

    protected abstract void a(@Nullable T t);

    protected abstract com.facebook.datasource.c<T> b();

    @Nullable
    protected abstract INFO c(T t);

    protected abstract Drawable d(T t);

    public a(com.facebook.drawee.a.a deferredReleaser, Executor uiThreadImmediateExecutor) {
        this.c = deferredReleaser;
        this.d = uiThreadImmediateExecutor;
        c(null, null);
    }

    protected final void b(String id, Object callerContext) {
        c(id, callerContext);
        this.v = false;
    }

    private synchronized void c(String id, Object callerContext) {
        com.facebook.imagepipeline.l.b.a();
        this.b.a(com.facebook.drawee.a.b.a.ON_INIT_CONTROLLER);
        if (!(this.v || this.c == null)) {
            this.c.b(this);
        }
        this.m = false;
        this.o = false;
        a();
        this.q = false;
        if (this.e != null) {
            this.e.a();
        }
        if (this.f != null) {
            this.f.a();
            this.f.a((com.facebook.drawee.e.a.a) this);
        }
        if (this.g instanceof a) {
            ((a) this.g).a();
        } else {
            this.g = null;
        }
        this.h = null;
        if (this.i != null) {
            this.i.b();
            this.i.a(null);
            this.i = null;
        }
        this.j = null;
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.k, (Object) id);
        }
        this.k = id;
        this.l = callerContext;
        com.facebook.imagepipeline.l.b.a();
    }

    public final void d() {
        this.b.a(com.facebook.drawee.a.b.a.ON_RELEASE_CONTROLLER);
        if (this.e != null) {
            this.e.b();
        }
        if (this.f != null) {
            this.f.b();
        }
        if (this.i != null) {
            this.i.b();
        }
        a();
    }

    private void a() {
        boolean wasRequestSubmitted = this.n;
        this.n = false;
        this.p = false;
        if (this.s != null) {
            this.s.h();
            this.s = null;
        }
        if (this.u != null) {
            a(this.u);
        }
        if (this.r != null) {
            this.r = null;
        }
        this.u = null;
        if (this.t != null) {
            d("release", this.t);
            a(this.t);
            this.t = null;
        }
        if (wasRequestSubmitted) {
            m().onRelease(this.k);
        }
    }

    public final String e() {
        return this.k;
    }

    @ReturnsOwnership
    protected final c f() {
        if (this.e == null) {
            this.e = new c();
        }
        return this.e;
    }

    @Nullable
    protected final com.facebook.drawee.e.a g() {
        return this.f;
    }

    protected final void a(@Nullable com.facebook.drawee.e.a gestureDetector) {
        this.f = gestureDetector;
        if (this.f != null) {
            this.f.a((com.facebook.drawee.e.a.a) this);
        }
    }

    protected final void b(boolean enabled) {
        this.q = enabled;
    }

    public final void a(@Nullable String contentDescription) {
        this.r = contentDescription;
    }

    public final void a(ControllerListener<? super INFO> controllerListener) {
        h.a((Object) controllerListener);
        if (this.g instanceof a) {
            ((a) this.g).a(controllerListener);
        } else if (this.g != null) {
            this.g = a.a(this.g, controllerListener);
        } else {
            this.g = controllerListener;
        }
    }

    public final void b(ControllerListener<? super INFO> controllerListener) {
        h.a((Object) controllerListener);
        if (this.g instanceof a) {
            ((a) this.g).b(controllerListener);
        } else if (this.g == controllerListener) {
            this.g = null;
        }
    }

    private ControllerListener<INFO> m() {
        if (this.g == null) {
            return BaseControllerListener.getNoOpListener();
        }
        return this.g;
    }

    public final void a(@Nullable c controllerViewportVisibilityListener) {
        this.h = controllerViewportVisibilityListener;
    }

    @Nullable
    public final com.facebook.drawee.interfaces.a h() {
        return this.i;
    }

    public void a(@Nullable com.facebook.drawee.interfaces.a hierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.k, (Object) hierarchy);
        }
        this.b.a(hierarchy != null ? com.facebook.drawee.a.b.a.ON_SET_HIERARCHY : com.facebook.drawee.a.b.a.ON_CLEAR_HIERARCHY);
        if (this.n) {
            this.c.b(this);
            d();
        }
        if (this.i != null) {
            this.i.a(null);
            this.i = null;
        }
        if (hierarchy != null) {
            h.a(hierarchy instanceof com.facebook.drawee.interfaces.b);
            this.i = (com.facebook.drawee.interfaces.b) hierarchy;
            this.i.a(this.j);
        }
    }

    protected final void b(@Nullable Drawable controllerOverlay) {
        this.j = controllerOverlay;
        if (this.i != null) {
            this.i.a(this.j);
        }
    }

    @Nullable
    protected final Drawable i() {
        return this.j;
    }

    public final void j() {
        com.facebook.imagepipeline.l.b.a();
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s: onAttach: %s", Integer.valueOf(System.identityHashCode(this)), this.k, this.n ? "request already submitted" : "request needs submit");
        }
        this.b.a(com.facebook.drawee.a.b.a.ON_ATTACH_CONTROLLER);
        h.a(this.i);
        this.c.b(this);
        this.m = true;
        if (!this.n) {
            o();
        }
        com.facebook.imagepipeline.l.b.a();
    }

    public final void k() {
        com.facebook.imagepipeline.l.b.a();
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.k);
        }
        this.b.a(com.facebook.drawee.a.b.a.ON_DETACH_CONTROLLER);
        this.m = false;
        this.c.a((com.facebook.drawee.a.a.a) this);
        com.facebook.imagepipeline.l.b.a();
    }

    public final boolean a(MotionEvent event) {
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.k, (Object) event);
        }
        if (this.f == null) {
            return false;
        }
        if (!this.f.c() && !n()) {
            return false;
        }
        this.f.a(event);
        return true;
    }

    private boolean n() {
        return this.p && this.e != null && this.e.c();
    }

    public final boolean l() {
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.k);
        }
        if (!n()) {
            return false;
        }
        this.e.d();
        this.i.b();
        o();
        return true;
    }

    private void o() {
        com.facebook.imagepipeline.l.b.a();
        Object closeableImage = c();
        if (closeableImage != null) {
            com.facebook.imagepipeline.l.b.a();
            this.s = null;
            this.n = true;
            this.p = false;
            this.b.a(com.facebook.drawee.a.b.a.ON_SUBMIT_CACHE_HIT);
            m().onSubmit(this.k, this.l);
            a(this.k, closeableImage);
            a(this.k, this.s, closeableImage, 1.0f, true, true, true);
            com.facebook.imagepipeline.l.b.a();
            com.facebook.imagepipeline.l.b.a();
            return;
        }
        this.b.a(com.facebook.drawee.a.b.a.ON_DATASOURCE_SUBMIT);
        m().onSubmit(this.k, this.l);
        this.i.a(0.0f, true);
        this.n = true;
        this.p = false;
        this.s = b();
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.k, Integer.valueOf(System.identityHashCode(this.s)));
        }
        final String id = this.k;
        final boolean wasImmediate = this.s.c();
        this.s.a(new com.facebook.datasource.b<T>(this) {
            final /* synthetic */ a c;

            public final void e(com.facebook.datasource.c<T> dataSource) {
                boolean isFinished = dataSource.b();
                float progress = dataSource.g();
                Object image = dataSource.d();
                if (image != null) {
                    this.c.a(id, (com.facebook.datasource.c) dataSource, image, progress, isFinished, wasImmediate, false);
                } else if (isFinished) {
                    this.c.a(id, (com.facebook.datasource.c) dataSource, new NullPointerException(), true);
                }
            }

            public final void f(com.facebook.datasource.c<T> dataSource) {
                this.c.a(id, (com.facebook.datasource.c) dataSource, dataSource.f(), true);
            }

            public final void d(com.facebook.datasource.c<T> dataSource) {
                boolean isFinished = dataSource.b();
                a.a(this.c, id, dataSource, dataSource.g(), isFinished);
            }
        }, this.d);
        com.facebook.imagepipeline.l.b.a();
    }

    private void a(String id, com.facebook.datasource.c<T> dataSource, @Nullable T image, float progress, boolean isFinished, boolean wasImmediate, boolean deliverTempResult) {
        Object previousImage;
        Drawable previousDrawable;
        try {
            com.facebook.imagepipeline.l.b.a();
            if (a(id, (com.facebook.datasource.c) dataSource)) {
                this.b.a(isFinished ? com.facebook.drawee.a.b.a.ON_DATASOURCE_RESULT : com.facebook.drawee.a.b.a.ON_DATASOURCE_RESULT_INT);
                Drawable drawable = d(image);
                previousImage = this.t;
                previousDrawable = this.u;
                this.t = image;
                this.u = drawable;
                if (isFinished) {
                    d("set_final_result @ onNewResult", image);
                    this.s = null;
                    this.i.a(drawable, 1.0f, wasImmediate);
                    m().onFinalImageSet(id, c(image), p());
                } else if (deliverTempResult) {
                    d("set_temporary_result @ onNewResult", image);
                    this.i.a(drawable, 1.0f, wasImmediate);
                    m().onFinalImageSet(id, c(image), p());
                } else {
                    d("set_intermediate_result @ onNewResult", image);
                    this.i.a(drawable, progress, wasImmediate);
                    m().onIntermediateImageSet(id, c(image));
                }
                if (!(previousDrawable == null || previousDrawable == drawable)) {
                    a(previousDrawable);
                }
                if (!(previousImage == null || previousImage == image)) {
                    d("release_previous_result @ onNewResult", previousImage);
                    a(previousImage);
                }
                com.facebook.imagepipeline.l.b.a();
                return;
            }
            d("ignore_old_datasource @ onNewResult", image);
            a((Object) image);
            dataSource.h();
            com.facebook.imagepipeline.l.b.a();
        } catch (Throwable exception) {
            d("drawable_failed @ onNewResult", image);
            a((Object) image);
            a(id, (com.facebook.datasource.c) dataSource, exception, isFinished);
            com.facebook.imagepipeline.l.b.a();
        } catch (Throwable th) {
            com.facebook.imagepipeline.l.b.a();
        }
    }

    private void a(String id, com.facebook.datasource.c<T> dataSource, Throwable throwable, boolean isFinished) {
        com.facebook.imagepipeline.l.b.a();
        if (a(id, (com.facebook.datasource.c) dataSource)) {
            this.b.a(isFinished ? com.facebook.drawee.a.b.a.ON_DATASOURCE_FAILURE : com.facebook.drawee.a.b.a.ON_DATASOURCE_FAILURE_INT);
            if (isFinished) {
                a("final_failed @ onFailure", throwable);
                this.s = null;
                this.p = true;
                if (this.q && this.u != null) {
                    this.i.a(this.u, 1.0f, true);
                } else if (n()) {
                    this.i.d();
                } else {
                    this.i.c();
                }
                m().onFailure(this.k, throwable);
            } else {
                a("intermediate_failed @ onFailure", throwable);
                m().onIntermediateImageFailed(this.k, throwable);
            }
            com.facebook.imagepipeline.l.b.a();
            return;
        }
        a("ignore_old_datasource @ onFailure", throwable);
        dataSource.h();
        com.facebook.imagepipeline.l.b.a();
    }

    private boolean a(String id, com.facebook.datasource.c<T> dataSource) {
        if (dataSource == null && this.s == null) {
            return true;
        }
        if (id.equals(this.k) && dataSource == this.s && this.n) {
            return true;
        }
        return false;
    }

    private void d(String messageAndMethod, T image) {
        if (FLog.isLoggable(2)) {
            String simpleName;
            Class cls = a;
            String str = "controller %x %s: %s: image: %s %x";
            Object[] objArr = new Object[5];
            objArr[0] = Integer.valueOf(System.identityHashCode(this));
            objArr[1] = this.k;
            objArr[2] = messageAndMethod;
            if (image != null) {
                simpleName = image.getClass().getSimpleName();
            } else {
                simpleName = "<null>";
            }
            objArr[3] = simpleName;
            objArr[4] = Integer.valueOf(b((Object) image));
            FLog.v(cls, str, objArr);
        }
    }

    private void a(String messageAndMethod, Throwable throwable) {
        if (FLog.isLoggable(2)) {
            FLog.v(a, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.k, (Object) messageAndMethod, (Object) throwable);
        }
    }

    @Nullable
    private Animatable p() {
        return this.u instanceof Animatable ? (Animatable) this.u : null;
    }

    protected int b(@Nullable T image) {
        return System.identityHashCode(image);
    }

    public String toString() {
        return g.a(this).a("isAttached", this.m).a("isRequestSubmitted", this.n).a("hasFetchFailed", this.p).a("fetchedImage", b(this.t)).a(EventsEntry.TABLE_NAME, this.b.toString()).toString();
    }

    protected T c() {
        return null;
    }

    protected void a(String id, T t) {
    }

    static /* synthetic */ void a(a x0, String x1, com.facebook.datasource.c x2, float x3, boolean x4) {
        if (!x0.a(x1, x2)) {
            x0.a("ignore_old_datasource @ onProgress", null);
            x2.h();
        } else if (!x4) {
            x0.i.a(x3, false);
        }
    }
}
