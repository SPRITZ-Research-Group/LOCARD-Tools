package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import com.facebook.react.bridge.ao.b;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.d;
import com.facebook.react.common.c;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

public class ai extends ContextWrapper {
    private final ao<v> a = new ao();
    private final ao<a> b = new ao();
    private final a<v> c = new a<v>(this) {
        final /* synthetic */ ai a;

        {
            this.a = this$0;
        }

        public final /* synthetic */ void a(Object obj) {
            ((v) obj).onHostResume();
        }
    };
    private final a<v> d = new a<v>(this) {
        final /* synthetic */ ai a;

        {
            this.a = this$0;
        }

        public final /* synthetic */ void a(Object obj) {
            ((v) obj).onHostPause();
        }
    };
    private final a<v> e = new a<v>(this) {
        final /* synthetic */ ai a;

        {
            this.a = this$0;
        }

        public final /* synthetic */ void a(Object obj) {
            ((v) obj).onHostDestroy();
        }
    };
    private c f = c.BEFORE_CREATE;
    @Nullable
    private CatalystInstance g;
    @Nullable
    private LayoutInflater h;
    @Nullable
    private MessageQueueThread i;
    @Nullable
    private MessageQueueThread j;
    @Nullable
    private MessageQueueThread k;
    @Nullable
    private MessageQueueThread l;
    @Nullable
    private aa m;
    @Nullable
    private WeakReference<Activity> n;

    private abstract class a<T> implements b<T> {
        final /* synthetic */ ai f;

        public abstract void a(T t);

        private a(ai aiVar) {
            this.f = aiVar;
        }

        /* synthetic */ a(ai x0, byte b) {
            this(x0);
        }

        public final void b(T listener) {
            try {
                a(listener);
            } catch (RuntimeException e) {
                this.f.a(e);
            }
        }
    }

    public ai(Context base) {
        super(base);
    }

    public final void a(CatalystInstance catalystInstance) {
        if (catalystInstance == null) {
            throw new IllegalArgumentException("CatalystInstance cannot be null.");
        } else if (this.g != null) {
            throw new IllegalStateException("ReactContext has been already initialized");
        } else {
            this.g = catalystInstance;
            d queueConfig = catalystInstance.getReactQueueConfiguration();
            this.i = queueConfig.a();
            this.j = queueConfig.b();
            this.k = queueConfig.c();
            this.l = queueConfig.d();
        }
    }

    public final void a(@Nullable aa nativeModuleCallExceptionHandler) {
        this.m = nativeModuleCallExceptionHandler;
    }

    public Object getSystemService(String name) {
        if (!"layout_inflater".equals(name)) {
            return getBaseContext().getSystemService(name);
        }
        if (this.h == null) {
            this.h = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.h;
    }

    public final <T extends JavaScriptModule> T a(Class<T> jsInterface) {
        if (this.g != null) {
            return this.g.getJSModule(jsInterface);
        }
        throw new RuntimeException("Tried to access a JS module before the React instance was fully set up. Calls to ReactContext#getJSModule should only happen once initialize() has been called on your native module.");
    }

    public final <T extends NativeModule> T b(Class<T> nativeModuleInterface) {
        if (this.g != null) {
            return this.g.getNativeModule(nativeModuleInterface);
        }
        throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
    }

    public final CatalystInstance a() {
        return (CatalystInstance) com.facebook.infer.annotation.a.a(this.g);
    }

    public final boolean b() {
        return (this.g == null || this.g.isDestroyed()) ? false : true;
    }

    public void a(final v listener) {
        this.a.a((Object) listener);
        if (b()) {
            switch (this.f) {
                case BEFORE_CREATE:
                case BEFORE_RESUME:
                    return;
                case RESUMED:
                    a(new Runnable(this) {
                        final /* synthetic */ ai b;

                        public final void run() {
                            try {
                                listener.onHostResume();
                            } catch (RuntimeException e) {
                                this.b.a(e);
                            }
                        }
                    });
                    return;
                default:
                    throw new RuntimeException("Unhandled lifecycle state.");
            }
        }
    }

    public void b(v listener) {
        this.a.b(listener);
    }

    public final void a(a listener) {
        this.b.a((Object) listener);
    }

    public final void b(a listener) {
        this.b.b(listener);
    }

    public final void a(@Nullable Activity activity) {
        this.f = c.RESUMED;
        this.n = new WeakReference(activity);
        ReactMarker.logMarker(aj.ON_HOST_RESUME_START);
        this.a.a(this.c);
        ReactMarker.logMarker(aj.ON_HOST_RESUME_END);
    }

    public final void a(@Nullable Activity activity, final Intent intent) {
        ap.b();
        this.n = new WeakReference(activity);
        this.b.a(new a<a>(this) {
            final /* synthetic */ ai b;

            public final /* synthetic */ void a(Object obj) {
                ((a) obj).onNewIntent(intent);
            }
        });
    }

    public final void c() {
        this.f = c.BEFORE_RESUME;
        ReactMarker.logMarker(aj.ON_HOST_PAUSE_START);
        this.a.a(this.d);
        ReactMarker.logMarker(aj.ON_HOST_PAUSE_END);
    }

    public final void d() {
        ap.b();
        this.f = c.BEFORE_CREATE;
        this.a.a(this.e);
        this.n = null;
    }

    public final void e() {
        ap.b();
        if (this.g != null) {
            this.g.destroy();
        }
    }

    public final void a(Activity activity, int requestCode, int resultCode, Intent data) {
        final Activity activity2 = activity;
        final int i = requestCode;
        final int i2 = resultCode;
        final Intent intent = data;
        this.b.a(new a<a>(this) {
            final /* synthetic */ ai e;

            public final /* synthetic */ void a(Object obj) {
                ((a) obj).onActivityResult(activity2, i, i2, intent);
            }
        });
    }

    public final boolean f() {
        return ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.i)).isOnThread();
    }

    public final void a(Runnable runnable) {
        ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.i)).runOnQueue(runnable);
    }

    public final void b(Runnable runnable) {
        ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.j)).runOnQueue(runnable);
    }

    public final void g() {
        ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.k)).assertIsOnThread();
    }

    public final void a(String message) {
        ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.k)).assertIsOnThread(message);
    }

    public final void c(Runnable runnable) {
        ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.k)).runOnQueue(runnable);
    }

    public final void d(Runnable runnable) {
        ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.l)).runOnQueue(runnable);
    }

    public final void h() {
        if (this.j == null) {
            g();
        } else {
            ((MessageQueueThread) com.facebook.infer.annotation.a.a(this.j)).assertIsOnThread();
        }
    }

    public final void e(Runnable runnable) {
        if (this.j == null) {
            c(runnable);
        } else {
            b(runnable);
        }
    }

    public final void a(RuntimeException e) {
        if (this.g == null || this.g.isDestroyed() || this.m == null) {
            throw e;
        }
        this.m.a(e);
    }

    public boolean i() {
        return (this.n == null || this.n.get() == null) ? false : true;
    }

    @Nullable
    public Activity j() {
        if (this.n == null) {
            return null;
        }
        return (Activity) this.n.get();
    }
}
