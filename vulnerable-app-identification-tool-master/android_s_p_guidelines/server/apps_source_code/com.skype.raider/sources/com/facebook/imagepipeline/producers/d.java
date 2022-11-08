package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.k.b;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class d implements ak {
    private final b a;
    private final String b;
    private final am c;
    private final Object d;
    private final b.b e;
    @GuardedBy("this")
    private boolean f;
    @GuardedBy("this")
    private com.facebook.imagepipeline.common.d g;
    @GuardedBy("this")
    private boolean h;
    @GuardedBy("this")
    private boolean i = false;
    @GuardedBy("this")
    private final List<al> j = new ArrayList();

    public d(b imageRequest, String id, am producerListener, Object callerContext, b.b lowestPermittedRequestLevel, boolean isPrefetch, boolean isIntermediateResultExpected, com.facebook.imagepipeline.common.d priority) {
        this.a = imageRequest;
        this.b = id;
        this.c = producerListener;
        this.d = callerContext;
        this.e = lowestPermittedRequestLevel;
        this.f = isPrefetch;
        this.g = priority;
        this.h = isIntermediateResultExpected;
    }

    public final b a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final am c() {
        return this.c;
    }

    public final Object d() {
        return this.d;
    }

    public final b.b e() {
        return this.e;
    }

    public final synchronized boolean f() {
        return this.f;
    }

    public final synchronized com.facebook.imagepipeline.common.d g() {
        return this.g;
    }

    public final synchronized boolean h() {
        return this.h;
    }

    public final void a(al callbacks) {
        boolean cancelImmediately = false;
        synchronized (this) {
            this.j.add(callbacks);
            if (this.i) {
                cancelImmediately = true;
            }
        }
        if (cancelImmediately) {
            callbacks.a();
        }
    }

    public final void i() {
        List<al> j = j();
        if (j != null) {
            for (al a : j) {
                a.a();
            }
        }
    }

    @Nullable
    public final synchronized List<al> a(boolean isPrefetch) {
        List<al> list;
        if (isPrefetch == this.f) {
            list = null;
        } else {
            this.f = isPrefetch;
            list = new ArrayList(this.j);
        }
        return list;
    }

    @Nullable
    public final synchronized List<al> a(com.facebook.imagepipeline.common.d priority) {
        List<al> list;
        if (priority == this.g) {
            list = null;
        } else {
            this.g = priority;
            list = new ArrayList(this.j);
        }
        return list;
    }

    @Nullable
    public final synchronized List<al> b(boolean isIntermediateResultExpected) {
        List<al> list;
        if (isIntermediateResultExpected == this.h) {
            list = null;
        } else {
            this.h = isIntermediateResultExpected;
            list = new ArrayList(this.j);
        }
        return list;
    }

    @Nullable
    private synchronized List<al> j() {
        List<al> list;
        if (this.i) {
            list = null;
        } else {
            this.i = true;
            list = new ArrayList(this.j);
        }
        return list;
    }

    public static void a(@Nullable List<al> callbacks) {
        if (callbacks != null) {
            for (al b : callbacks) {
                b.b();
            }
        }
    }

    public static void b(@Nullable List<al> callbacks) {
        if (callbacks != null) {
            for (al c : callbacks) {
                c.c();
            }
        }
    }

    public static void c(@Nullable List<al> callbacks) {
        if (callbacks != null) {
            for (al d : callbacks) {
                d.d();
            }
        }
    }
}
