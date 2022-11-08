package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import com.facebook.cache.a.c;
import com.facebook.common.internal.k;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.d;
import com.facebook.imagepipeline.a.b.a;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.d.e;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.d.l;
import com.facebook.imagepipeline.d.m;
import com.facebook.imagepipeline.d.o;
import com.facebook.imagepipeline.l.b;
import com.facebook.imagepipeline.memory.ac;
import com.facebook.imagepipeline.producers.af;
import com.facebook.imagepipeline.producers.au;
import com.facebook.imagepipeline.transcoder.g;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class j {
    private static final Class<?> a = j.class;
    private static j b = null;
    private final au c;
    private final h d;
    private h<c, com.facebook.imagepipeline.image.c> e;
    private o<c, com.facebook.imagepipeline.image.c> f;
    private h<c, com.facebook.common.e.h> g;
    private o<c, com.facebook.common.e.h> h;
    private e i;
    private com.facebook.cache.disk.h j;
    private com.facebook.imagepipeline.f.c k;
    private g l;
    private com.facebook.imagepipeline.transcoder.c m;
    private l n;
    private m o;
    private e p;
    private com.facebook.cache.disk.h q;
    private f r;
    private com.facebook.imagepipeline.i.f s;
    private a t;

    public static j a() {
        return (j) com.facebook.common.internal.h.a(b, (Object) "ImagePipelineFactory was not initialized!");
    }

    public static synchronized void a(Context context) {
        synchronized (j.class) {
            b.a();
            a(h.a(context).a());
            b.a();
        }
    }

    public static synchronized void a(h imagePipelineConfig) {
        synchronized (j.class) {
            if (b != null) {
                FLog.w(a, "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
            }
            b = new j(imagePipelineConfig);
        }
    }

    private j(h config) {
        b.a();
        this.d = (h) com.facebook.common.internal.h.a((Object) config);
        this.c = new au(config.k().e());
        b.a();
    }

    @Nullable
    private a f() {
        if (this.t == null) {
            this.t = com.facebook.imagepipeline.a.b.b.a(d(), this.d.k(), b());
        }
        return this.t;
    }

    @Nullable
    public final com.facebook.imagepipeline.g.a b(Context context) {
        a animatedFactory = f();
        return animatedFactory == null ? null : animatedFactory.getAnimatedDrawableFactory(context);
    }

    public final h<c, com.facebook.imagepipeline.image.c> b() {
        if (this.e == null) {
            this.e = com.facebook.imagepipeline.d.a.a(this.d.b(), this.d.r(), this.d.c());
        }
        return this.e;
    }

    private o<c, com.facebook.imagepipeline.image.c> g() {
        if (this.f == null) {
            this.f = com.facebook.imagepipeline.d.b.a(b(), this.d.l());
        }
        return this.f;
    }

    private o<c, com.facebook.common.e.h> h() {
        if (this.h == null) {
            if (this.g == null) {
                this.g = l.a(this.d.j(), this.d.r());
            }
            this.h = m.a(this.g, this.d.l());
        }
        return this.h;
    }

    private e i() {
        if (this.i == null) {
            if (this.j == null) {
                this.j = this.d.g().a(this.d.q());
            }
            this.i = new e(this.j, this.d.u().a(this.d.s()), this.d.u().e(), this.d.k().a(), this.d.k().b(), this.d.l());
        }
        return this.i;
    }

    public final g c() {
        if (this.l == null) {
            boolean z;
            if (VERSION.SDK_INT < 24 || !this.d.A().e()) {
                z = false;
            } else {
                z = true;
            }
            if (this.o == null) {
                ContentResolver contentResolver = this.d.e().getApplicationContext().getContentResolver();
                if (this.n == null) {
                    i.c j = this.d.A().j();
                    Context e = this.d.e();
                    com.facebook.common.e.a f = this.d.u().f();
                    if (this.k == null) {
                        if (this.d.m() != null) {
                            this.k = this.d.m();
                        } else {
                            a f2 = f();
                            com.facebook.imagepipeline.f.c cVar = null;
                            com.facebook.imagepipeline.f.c cVar2 = null;
                            if (f2 != null) {
                                cVar = f2.getGifDecoder(this.d.a());
                                cVar2 = f2.getWebPDecoder(this.d.a());
                            }
                            if (this.d.z() == null) {
                                this.k = new com.facebook.imagepipeline.f.b(cVar, cVar2, e());
                            } else {
                                this.k = new com.facebook.imagepipeline.f.b(cVar, cVar2, e(), this.d.z().a());
                                d.a().a(this.d.z().b());
                            }
                        }
                    }
                    this.n = j.a(e, f, this.k, this.d.v(), this.d.h(), this.d.x(), this.d.A().c(), this.d.k(), this.d.u().a(this.d.s()), g(), h(), i(), j(), this.d.d(), d(), this.d.A().f(), this.d.A().g(), this.d.A().k(), this.d.A().l());
                }
                l lVar = this.n;
                af t = this.d.t();
                boolean x = this.d.x();
                boolean b = this.d.A().b();
                au auVar = this.c;
                boolean h = this.d.h();
                boolean i = this.d.A().i();
                boolean i2 = this.d.i();
                if (this.m == null) {
                    if (this.d.n() == null && this.d.o() == null && this.d.A().h()) {
                        this.m = new g(this.d.A().l());
                    } else {
                        this.m = new com.facebook.imagepipeline.transcoder.e(this.d.A().l(), this.d.A().a(), this.d.n(), this.d.o());
                    }
                }
                this.o = new m(contentResolver, lVar, t, x, b, auVar, h, z, i, i2, this.m);
            }
            this.l = new g(this.o, this.d.w(), this.d.p(), g(), h(), i(), j(), this.d.d(), this.c, k.a(Boolean.valueOf(false)), this.d.A().m());
        }
        return this.l;
    }

    public final f d() {
        if (this.r == null) {
            f aVar;
            ac u = this.d.u();
            com.facebook.imagepipeline.i.f e = e();
            if (VERSION.SDK_INT >= 21) {
                aVar = new com.facebook.imagepipeline.c.a(u.a());
            } else if (VERSION.SDK_INT >= 11) {
                aVar = new com.facebook.imagepipeline.c.e(new com.facebook.imagepipeline.c.b(u.d()), e);
            } else {
                aVar = new com.facebook.imagepipeline.c.c();
            }
            this.r = aVar;
        }
        return this.r;
    }

    public final com.facebook.imagepipeline.i.f e() {
        if (this.s == null) {
            com.facebook.imagepipeline.i.f eVar;
            ac u = this.d.u();
            boolean b = this.d.A().b();
            int c;
            if (VERSION.SDK_INT >= 26) {
                c = u.c();
                eVar = new com.facebook.imagepipeline.i.e(u.a(), c, new android.support.v4.util.j.c(c));
            } else if (VERSION.SDK_INT >= 21) {
                c = u.c();
                eVar = new com.facebook.imagepipeline.i.a(u.a(), c, new android.support.v4.util.j.c(c));
            } else if (!b || VERSION.SDK_INT >= 19) {
                eVar = new com.facebook.imagepipeline.i.d(u.b());
            } else {
                eVar = new com.facebook.imagepipeline.i.c();
            }
            this.s = eVar;
        }
        return this.s;
    }

    private e j() {
        if (this.p == null) {
            if (this.q == null) {
                this.q = this.d.g().a(this.d.y());
            }
            this.p = new e(this.q, this.d.u().a(this.d.s()), this.d.u().e(), this.d.k().a(), this.d.k().b(), this.d.l());
        }
        return this.p;
    }
}
